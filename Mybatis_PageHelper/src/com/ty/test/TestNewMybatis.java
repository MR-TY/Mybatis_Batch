package com.ty.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.dao.EmployeeMapper;
import com.ty.entity.Department;
import com.ty.entity.Employee;

public class TestNewMybatis {
	InputStream inputStream = null;
	SqlSession session = null;
	SqlSession session1 = null;
	SqlSessionFactory sessionFactory = null;
	 {
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		session1 = sessionFactory.openSession();
	}
	 @Test
	 public void testAllEmployee(){
		 //---------------设置参数为批量生产---------
		session = sessionFactory.openSession(ExecutorType.BATCH);
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 20; i++) {
			employeeMapper.addOneEmployee(new Employee("tt", "1", "456"));
		}
		session.commit();
		session.close();
	 }
}
