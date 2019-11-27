package com.jipf.springframeworkuse;

import com.jipf.springframeworkuse.beans.School;
import com.jipf.springframeworkuse.beans.Teacher;
import com.jipf.springframeworkuse.beans.qf.QualifierTest;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 默认标签测试
 */
public class DefaultTagTests {

    /**
     * constructor-arg 标签测试
     */
    @Test
    public void constructorArgTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
//        School school = context.getBean("school_3", School.class);
//        School school = context.getBean("school_5", School.class);
//        School school = context.getBean("school_6", School.class);
//        School school = context.getBean("school_7", School.class);

//        System.out.println("=======>>>>>>" + school.getName());
//        System.out.println("=======>>>>>>" + school.getAddress());
//        System.out.println("=======>>>>>>" + school.getIndex());
//        System.out.println(school.getTeacher().hashCode());
//        System.out.println("=======>>>>>>" + school.getTeacher().getName());

        // 测试meta标签
//        BeanDefinition beanDefinition = ((ClassPathXmlApplicationContext) context).getBeanFactory().getBeanDefinition("school");
//        System.out.println(beanDefinition.getAttribute("aaa"));

        // 测试scop
        // school 对象引用的teacher实例不会改变
        School school = context.getBean("school_7", School.class);
        school.getTeacher().setName("xiaoming");
        System.out.println(school.getTeacher().getName());
        School school1 = context.getBean("school_7", School.class);
        System.out.println(school1.getTeacher().getName());
//        Teacher teacher = context.getBean("teacher",Teacher.class);
//        teacher.setName("xiao ming");
//        System.out.println(teacher.getName());
//        Teacher teacher1 =  context.getBean("teacher",Teacher.class);
//        System.out.println(teacher1.getName());
    }

    /**
     * qualifier 标签测试
     */
    @Test
    public void qualifierTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        QualifierTest qualifierTest = context.getBean("qualifierTest", QualifierTest.class);
//        qualifierTest.getQualifier().print();
    }

    /**
     * lookup-method 标签测试
     */
    @Test
    public void lookupMethodTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        QualifierTest qualifierTest = context.getBean("qualifierTest", QualifierTest.class);
        qualifierTest.test();
    }
}
