package com.temp.department.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.temp.department.model.DepartmentDTO;
import com.temp.department.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "部门信息模块")
@RestController("DepartmentController")
@RequestMapping("/department")
public class DepartmentController {

	
	@Autowired
    private DepartmentService service;

    @ApiOperation(value = "部门信息列表", notes = "根据参数查询部门信息")
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResponseData list(@RequestBody DepartmentDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", service.getPageList(container));
        map.put("total", service.getTotal(container));
        
        return FormatUtil.success(map);
    }


    @ApiOperation(value = "新增或修改信息", notes = "根据ID确定修改还是新增配置信息")
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public ResponseData save(@RequestBody DepartmentDTO container) {
        return FormatUtil.success(service.save(container));
    }

    @ApiOperation(value = "删除信息", notes = "根据ID删除信息")
    @RequestMapping(value = "/del", method = { RequestMethod.DELETE })
    public ResponseData delete(@RequestBody DepartmentDTO container) {
        if (service.delete1(container.getDeptCode()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
    
    @ApiOperation(value = "部门信息列表---用于下拉框", notes = "根据参数查询部门信息")
    @RequestMapping(value = "/list1", method = { RequestMethod.POST })
    public ResponseData list1() {
        Map<String, Object> map = new HashMap<>();
        map.put("list", service.getList());
        return FormatUtil.success(map);
    }
    
    @ApiOperation(value = "部门对应菜单权限", notes = "部门对应菜单权限")
    @RequestMapping(value = "/listmenu", method = { RequestMethod.POST })
    public ResponseData listmenu(@RequestBody DepartmentDTO container) throws IOException {
    	 return FormatUtil.success(service.getMenuByRoleList(container));
    }
}
