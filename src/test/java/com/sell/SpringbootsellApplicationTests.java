package com.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootsellApplicationTests {


	int i = 1;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		StringBuilder a = new StringBuilder();
		System.out.println(a);
		for (int i=0;i<3;i++){
			a.append(i);
		}
		System.out.println(a.toString());
	}

	@Test
	public void test2(){
		StringBuilder a =new StringBuilder();
		for (int i=0;i<3;i++){
			a = new StringBuilder();
			a.append(i);
		}
		System.out.println(a.toString());

		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(ClassLoader.getSystemClassLoader());
	}

}
