package com.atguigu.crud.test;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:WEB-INF/dispatcherServlet-servlet.xml"})//make  as  de
public class MvcTest {
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;
    @Before
    public void initMokcMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        //请求成功  请求域中会有pageinfo  取出pageinfo
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");

        System.out.println("当前页码" + pi.getPageNum());
        System.out.println("总页码" + pi.getPages());
        System.out.println("总记录数" + pi.getTotal());
        System.out.println("在页面要显示的连续页码");
        int [] nums = pi.getNavigatepageNums();
        for (int i : nums) {
            System.out.println(i);            
        }
        //获取员工数据
        List<Employee> list = pi.getList();
        for (Employee employee : list) {
            System.out.println("id" + employee.getEmpId() + "name" + employee.getEmpName());
        }

    }
}
