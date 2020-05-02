package com.atguigu.crud.test;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 推荐spring项目就可以使用spring的单元测试  可以自动注入我们需要的组件
 * 1.导入 springTest模块
 * 2.@ContextConfiguration指定spring配置文件的位置
 * 3.直接autowrite
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:applicationContext.xml"))
public class MapperTest {
    /**
     * 测试De  mapper
     *
     */
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){
        System.out.println(departmentMapper);
        //departmentMapper.insertSelective(new Department(null,"销售部"));
        //employeeMapper.insertSelective(new Employee(null,"jerry","M","jerry@163.com",1));

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i = 0; i< 20;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,uid,"M",uid + "@qq.com",2));
        }
        System.out.println("success批量");

    }


}
