package com.jipf.ioc.context;

import com.jipf.aop.AopBeanContext;
import com.jipf.ioc.annotation.JAutowired;
import com.jipf.ioc.annotation.JComponent;
import com.jipf.ioc.annotation.JController;
import com.jipf.ioc.annotation.JService;
import com.jipf.ioc.common.IOCConst;
import com.jipf.ioc.exception.InitBeanContextException;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public abstract class AbsBeanContext implements IBeanContext {

    //ioc容器
    private final Map<String, Object> beans = new HashMap<String, Object>();
    //包扫描路径集合
    private final List<String> scanPaths = new ArrayList<String>();

    private ResourceBundle resourceBundle = null;

    protected String scan_package = "";

    public Object getBean(String beanName) {
        return beans.get(beanName);
    }

    protected void loadProperties(String properties) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(properties);
        }
        scan_package = resourceBundle.getString(IOCConst.SCAN_PACKAGE);
        if (scan_package.isEmpty()) {
            throw new InitBeanContextException(IOCConst.SCAN_PACKAGE + "not set!");
        }

    }

    protected void scanPackage(String scanPackage) {
        //获取包路径
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        File fileDir = new File(url.getFile());
        for (File file : fileDir.listFiles()) {
            if (file.isDirectory()) {
                scanPackage(scanPackage + "." + file.getName());
            } else {
                scanPaths.add(scanPackage + "." + file.getName().replace(".class", ""));
            }
        }
    }

    protected void initIoc() {
        this.scanPaths.stream().forEach(path -> {
            try {
                Class<?> clazz = Class.forName(path);
                if (clazz.isAnnotationPresent(JController.class) || clazz.isAnnotationPresent(JComponent.class)) {
                    JController jController = clazz.getAnnotation(JController.class);
                    JComponent jComponent = clazz.getAnnotation(JComponent.class);
                    String beanName = "";
                    if (jController != null) {
                        beanName = jController.value();
                    }
                    if (jComponent != null) {
                        beanName = jComponent.value();
                    }
                    if (beanName.isEmpty()) {
                        beanName = this.lowerFirst(clazz.getSimpleName());
                    }
                    beans.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(JService.class)) {
                    JService jService = clazz.getAnnotation(JService.class);
                    String beanName = jService.value();
                    if (beanName.isEmpty()) {
                        for (Class<?> cl : clazz.getInterfaces()) {
                            beans.put(this.lowerFirst(cl.getSimpleName()), clazz.newInstance());
                        }
                        beanName = this.lowerFirst(clazz.getSimpleName());
                    }
                    beans.put(beanName, clazz.newInstance());
                }else {
                    return;
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new InitBeanContextException(e);
            }
        });
    }

    protected void injection() {
        try {
            Iterator<Map.Entry<String, Object>> iterator = beans.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                for (Field field : fields){
                    if (field.isAnnotationPresent(JAutowired.class)){
                        //强制赋值
                        field.setAccessible(true);
                        JAutowired jAutowired = field.getAnnotation(JAutowired.class);
                        String beanName = jAutowired.value();
                        if (beanName.isEmpty()){
                            beanName = this.lowerFirst(field.getType().getSimpleName());
                        }
                        field.set(entry.getValue(),this.beans.get(beanName));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new InitBeanContextException(e);
        }
    }

    protected void doAop(){
        String open = this.resourceBundle.getString(IOCConst.AOP_OPEN);
        if (Boolean.valueOf(open)){//开启aop功能
            AopBeanContext.createProxyBeanContext(this.beans);
        }
    }

    /**
     * 第一个字符小写转换
     *
     * @param str
     * @return
     */
    private String lowerFirst(String str) {
        char[] c = str.toCharArray();
        c[0] += 32;
        return String.valueOf(c);
    }
}
