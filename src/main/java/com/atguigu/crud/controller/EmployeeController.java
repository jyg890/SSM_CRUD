package com.atguigu.crud.controller;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理crud请求
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //删除  单个  批量  二合一
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids){
        //批量删除
        if(ids.contains("-")){
            System.out.println("zou l ");
            List<Integer> del_ids = new ArrayList<Integer>();
            String[] str_ids = ids.split("-");
            //组装集合
            for (String empId : str_ids) {
               del_ids.add(Integer.parseInt(empId));
            }
                    employeeService.daleteBacth(del_ids);
        }else {
            Integer empId = Integer.parseInt(ids);
            employeeService.daleteEmp(empId);
        }

        return Msg.success();
    }
    //员工更新
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    public Msg saveEmp(Employee employee){
          employeeService.updateEmp(employee);
        return  Msg.success();
    }
    //根据ID查询
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }
    //检查用户名是否可用

    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("empName") String empName){
        //先看用户名是不是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg","用户名不对哦");
        }
        System.out.println("zou 了吗");
        //数据库用户名重复校验
        boolean b = employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名不可用");
        }

    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<String, Object>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //引入分页插件
        //在查询之前只需要调用  传入页码 以及分页大小
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);//连续显示的页数
        model.addAttribute("pageInfo",page);
        return "list";
    }
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        //引入分页插件
        //在查询之前只需要调用  传入页码 以及分页大小
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);//连续显示的页数

        return Msg.success().add("pageInfo",page);//携带数据
    }
}
