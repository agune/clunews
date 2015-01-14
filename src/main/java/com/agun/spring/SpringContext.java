package com.agun.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by agun on 2015. 1. 12..
 */
public class SpringContext {
    static ApplicationContext context;
    
    public static ApplicationContext getContext(){
        if(context == null){
            context = new AnnotationConfigApplicationContext(AppConfig.class);
        }
        return context;
    }

    public static Object getBean(String name){
        ApplicationContext springContext = getContext();
        return springContext.getBean(name);
    }

}
