package com.atguigu.crud.service;


import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    //查询所有员工
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }
//员工保存方法
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }
//检验用户名是否可用
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
        //true可用
    }
        //按照员工id查询
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }
    //根据员工id完成修改保存
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
            //员工删除
    public void daleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void daleteBacth(List<Integer> empId) {
        System.out.println("zoumrixou");
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //delete  from xxx where empId in (1.2.3)
        criteria.andEmpIdIn(empId);
        employeeMapper.deleteByExample(example);

    }
}
