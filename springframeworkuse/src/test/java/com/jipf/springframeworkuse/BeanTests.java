package com.jipf.springframeworkuse;

import com.jipf.springframeworkuse.beans.School;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTests {

    @Test
    public void testStudent01() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        School school = context.getBean("school", School.class);

        System.out.println("=======>>>>>>" + school.getName());
    }
}
