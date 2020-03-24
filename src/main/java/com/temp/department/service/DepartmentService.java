package com.temp.department.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temp.admin.dto.ConfigureDTO;
import com.temp.common.model.entity.Configure;
import com.temp.department.mapper.DepartmentMapper;
import com.temp.department.model.Department;
import com.temp.department.model.DepartmentDTO;
import com.temp.department.model.DepartmentListDTO;
import com.temp.permission.consts.BackendConst;
import com.temp.permission.model.response.MenuResponse;
import com.temp.permission.service.MenuService;
import com.temp.permission.util.ConsoleUtil;

import java.io.IOException;
import java.util.*;
@Service
public class DepartmentService {

	@Autowired
    private DepartmentMapper mapper;
	
	@Autowired
    private Mapper mapperTrans;
	
	@Autowired
    private MenuService menuService;
	
	
	/**
	 *根据查询条件查询出部门列表
	 *创建日期 2019-10-29
	 *liangbenjian 
	 */
	public List<DepartmentListDTO> getPageList(DepartmentDTO container){
		Department departmentSearch= mapperTrans.map(container,Department.class);	//封装数据到Department对象里
		Map<String, Object> map = new HashMap<>();
		map.put("DeptCode", departmentSearch.getDeptCode());         //map中的key值与映射文件中的select语句#{}占位符中的值需要一一对应
		map.put("CName", departmentSearch.getCName());				
		map.put("state", departmentSearch.getState());				
        map.put("page", container.getCurrentPage());
        map.put("size", container.getLimit());
        //System.out.println("---------------------------------"+departmentSearch.toString());
        List<Department> departmentList= mapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
        List<DepartmentListDTO> list = new ArrayList<>();                     //放入list
        for (Department department : departmentList) {
        	DepartmentListDTO dto = mapperTrans.map(department, DepartmentListDTO.class);
            list.add(dto);
        }
		return list;
		
	}
	
	/**
	 *不分页查找所有数据---用于下拉框
	 *创建日期 2019-10-29
	 *liangbenjian 
	 */
	public List<DepartmentListDTO> getList(){
		Department d=new Department();
        List<Department> departmentList= mapper.queryList(d);            //将条件全部给方法得到查询出的数据
        List<DepartmentListDTO> list = new ArrayList<>();                     //放入list
        for (Department department : departmentList) {
        	DepartmentListDTO dto = mapperTrans.map(department, DepartmentListDTO.class);
            list.add(dto);
        }
		return list;
		
	}
	
	/**
	 *列表用total方法
	 *创建日期 2019-10-29
	 *liangbenjian 
	 */
	public Integer getTotal(DepartmentDTO container) {
		Department departmentSearch = mapperTrans.map(container, Department.class);
        return mapper.queryCount(departmentSearch);
    }
	
	/**
	 *部门保存方法
	 *创建日期 2019-10-29
	 *liangbenjian 
	 */
	public Integer save(DepartmentDTO container) {
		Department department;      
        department = mapper.queryOne1(container.getDeptCode());
        //通过deptcode判断部门是否存在，存在则更新，不存在则创建
            if (department == null) {
            	department = new Department();
            	department.setDeptCode(container.getDeptCode());
            	department.setCName(container.getCName());
                department.setState(container.getState());
                //数字为数据库里菜单的id，根据数据库变化
                department.setResource("19,20,26,32,30,31,33,34");
                return mapper.add(department);
            }else {    
            	if(container.getResource_list()!=null) {
            		//从前台传过来的的数据用逗号进行拼接成字符串
            		String arr[]=container.getResource_list();
            		String str="";
            		System.out.println("-----------------------");
            		for(int i=0;i<arr.length;i++) {
            			System.out.println(arr[i]);
            			str=str+arr[i]+",";
            		}
            		//去掉结尾的逗号
            		 str=str.substring(0, str.length()-1);
            		System.out.println("-----------------------");
            		department.setResource(str);
            	}
            	
            	department.setCName(container.getCName());
                department.setState(container.getState());
                return mapper.update(department);
            }      
    }
	
	/**
	 *部门删除方法
	 *创建日期 2019-10-29
	 *liangbenjian 
	 */
	public Integer delete1(String id) {
        return mapper.delete1(id);
    }
	
	
    /**
     * 获取部门对应菜单
     * @param roleIds 角色ID
     * @return 角色的菜单
     * @throws IOException -
     */
    public Map getMenuByRoleList(DepartmentDTO container) throws IOException {
    	String str="";
    	Map<String, Object> map = new HashMap<>();
    	Department d=mapper.queryOne1(container.getDeptCode());
        List<MenuResponse> list = menuService.getMenuByParentId(BackendConst.PARENT_ID_DEFAULT);
        List<Integer> listCheckedKeys = new ArrayList<>(), listExpandedKeys = new ArrayList<>();
/*        for (MenuResponse menu : list) {
            if (menuService.isChecked(menu)) {
                listCheckedKeys.add(menu.getId());
            }
            if (menuService.isDisplay(menu)) {
                listExpandedKeys.add(menu.getId());
            }
            for (MenuResponse cMenu : menu.getChildren()) {
                if (cMenu.getChecked()) {
                    listCheckedKeys.add(cMenu.getId());
                }
            }
        }*/
        //先将所有的菜单的checked变为false
        for(int i=0;i<list.size();i++) {
        	list.get(i).setChecked(false);
        	 List<MenuResponse> a=menuService.getMenuByParentId(list.get(i).getId());
        	 for(int j=0;j<a.size();j++) {
        		 a.get(j).setChecked(false);
        	 }
        	 list.get(i).setChildren(a);
        }
        String resource=d.getResource();
        String[] newStr=resource.split(",");
        //循环将组织对应的resource和菜单比较，正确的改为true
        for(int i=0;i<list.size();i++) {
        	for(int j=0;j<newStr.length;j++) {
        		if(list.get(i).getId().toString().equals(newStr[j])){
        			list.get(i).setChecked(true);
        		}
        	}      
        	List<MenuResponse> child=list.get(i).getChildren();
        	for(int x=0;x<child.size();x++) {
        		for(int y=0;y<newStr.length;y++) {
            		if(child.get(x).getId().toString().equals(newStr[y])){
            			child.get(x).setChecked(true);
            		}
            	} 
        	}
        	list.get(i).setChildren(child);
        	
        }
        //向两个list里添加选中和展开的id
        for(int i=0;i<list.size();i++){        	
        	if(list.get(i).getChecked()==true) {
        		 listExpandedKeys.add(list.get(i).getId());
        		 str=str+list.get(i).getId()+",";
        	}
        	List<MenuResponse> child=list.get(i).getChildren();
        	for(int j=0;j<child.size();j++) {
        		if(child.get(j).getChecked()==true) {
        			  listCheckedKeys.add(child.get(j).getId());
        			  str=str+child.get(j).getId()+",";
        			  if(!listExpandedKeys.contains(child.get(j).getParentId())) {
        				  listExpandedKeys.add(child.get(j).getParentId());  
        				  str=str+child.get(j).getParentId()+",";
        			  }
        			 
        			  
        		}
        	}
        }
        
        
        for(int s=0;s<listCheckedKeys.size();s++) {
        	System.out.println("listCheckedKeys遍历打印数据："+listCheckedKeys.get(s));
        }
        for(int x=0;x<listExpandedKeys.size();x++) {
        	System.out.println("listExpandedKeys遍历打印数据："+listExpandedKeys.get(x));
        }        
        str=str.substring(0, str.length()-1);
        System.out.println("----------------str:"+str);
        map.put("list", list);
        map.put("checkedKeys", listCheckedKeys);
        map.put("expandedKeys", listExpandedKeys);
	    map.put("str", str);
	    
	     return map;
    }
    


}
