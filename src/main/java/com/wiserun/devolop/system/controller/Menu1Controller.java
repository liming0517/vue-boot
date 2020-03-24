package com.wiserun.devolop.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.controller.SearchController;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.devolop.system.entity.SysFunction;
import com.wiserun.devolop.system.entity.SysRes;
import com.wiserun.devolop.system.entity.SysResTab;
import com.wiserun.devolop.system.service.SysFunctionService;
import com.wiserun.devolop.system.service.SysResService;
import com.wiserun.devolop.system.service.SysResTabService;
import com.wiserun.permission.common.RMSContext;
import com.wiserun.permission.common.UserMenu;


@Controller
public class Menu1Controller extends SearchController<SysRes, SysResService>{
	
	@Autowired
	private SysFunctionService sysFunctionService;
	@Autowired
	private SysResTabService sysResTabService;
	
	/**
	 * 加载用户菜单
	 * @return
	 */
	@RequestMapping("listUserMenu_backuser.do")
	public @ResponseBody List<UserMenu> listUserMenu(){
		List<UserMenu> menuList = RMSContext.getInstance().getUserMenu();
		return menuList;
	}
	
    // 获取所有菜单
	@RequestMapping("/listAllMenu.do")
    public @ResponseBody List<SysRes> listAllMenu() {
    	List<SysRes> rows = this.getService().getAllRSysRes();
    	List<SysRes> treeData = buildTreeData(rows);
		return treeData;
    }
	
	 // 获取所有菜单
	@RequestMapping("/listRoleMenu.do")
    public @ResponseBody List<SysRes> listRoleMenu(String parentId) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		if(parentId !="0") {
			query.add(new ValueExpression("parent_id", parentId));
		}
    	List<SysRes> rows = this.getService().find(query);
    	
    	for (SysRes res : rows) {
    		List<SysFunction> sysFuns = sysFunctionService.getBySySResId(res.getId());
			res.setSysFuns(sysFuns);
		}
    	
    	List<SysRes> treeData = buildTreeData(rows,parentId);
    	
    	return treeData;
    }
	
	// 根据tab_id获取菜单
	@RequestMapping("/listMenuByTabId.do")
	public @ResponseBody List<SysRes> listMenuByTabId(String tabId) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		query.add(new ValueExpression("t.tab_id", tabId));
    	List<SysRes> rows = this.getService().find(query);
    	
    	for (SysRes res : rows) {
    		List<SysFunction> sysFuns = sysFunctionService.getBySySResId(res.getId());
			res.setSysFuns(sysFuns);
		}
    	
    	List<SysRes> treeData = buildTreeData(rows);
    	
    	return treeData;
	}
	
	// 获取顶级菜单
	@RequestMapping("/listTopMenu.do")
	public @ResponseBody List<SysResTab> listTopMenu() {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		query.addSort("id");
		
		List<SysResTab> rows = sysResTabService.find(query);
		
    	return rows;
	}
	
	@RequestMapping("/addTopMenu.do")
	public @ResponseBody Object addTopMenu(String tabName) {
		if(StringUtils.isNotBlank(tabName)) {
			SysResTab tab = new SysResTab();
			tab.setTabName(tabName);
			sysResTabService.save(tab);
			return tab;
		}
		return this.error("请输入正确选项");
	}
	
	
    
    /**
	 * 构建树形菜单
	 * @param list
	 * @return
	 */
	public static List<SysRes> buildTreeData(List<SysRes> list,String parentId) {

		List<SysRes> menu = new ArrayList<SysRes>();

		resolveMenuTree(list, parentId, menu);

		return menu;
	}
	
	public static List<SysRes> buildTreeData(List<SysRes> list) {
		return buildTreeData(list, "0");
	}
	
	public static int resolveMenuTree(List<SysRes> menus, String parentMenuId,
			List<SysRes> nodes) {

		int count = 0;
		for (SysRes menu : menus) {
			if (menu.getParentId() == parentMenuId) {
				SysRes node = new SysRes();

				nodes.add(node);
				//node.setSrId(menu.getSrId());
				node.setId(menu.getId());
				node.setResName(menu.getText());
				node.setResName(menu.getResName());
				node.setUrl(menu.getUrl());
				node.setParentId(menu.getParentId());
				node.setChildren(new ArrayList<SysRes>());
				node.setSysFuns(menu.getSysFuns());

				resolveMenuTree(menus, menu.getId(), node.getChildren());
				count++;
			}
		}
		return count;
	}
	

	
}
