package com.atguigu.crud.controller;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//处理部门有关的请求
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @ResponseBody
    @RequestMapping("/depts")
    public Msg getDepts(){
        //这是查出的所有信息
        List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts",list);
    }
}
