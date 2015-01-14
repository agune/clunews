package com.agun;

import com.agun.model.Rss;
import com.agun.service.RssService;
import com.agun.spring.AppConfig;
import com.agun.spring.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by agun on 2015. 1. 12..
 */


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    public static void main(String[] args){
        if(args.length > 0 && args[0].equals("batch")){
            AnnotationConfigApplicationContext ctx =
                    new AnnotationConfigApplicationContext();

            ctx.register(AppConfig.class);
            ctx.register(JpaConfig.class);
            ctx.refresh();
            runBatchRss(ctx);
        }else {
            ApplicationContext ctx = SpringApplication.run(Application.class, args);
        }
    }

    private static void runBatchRss(ApplicationContext ctx){
        RssService rssService = (RssService) ctx.getBean("rssService");
        List<Rss> rssList = rssService.getRssList(1);
        for(Rss rss : rssList){
            rssService.facadeRequest(rss);
        }
    }
}
