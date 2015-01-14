package com.agun.component;

import com.agun.spring.AppConfig;
import com.agun.spring.JpaConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agun on 2015. 1. 12..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        AppConfig.class, JpaConfig.class })
public class CrawlerTest {
    @Autowired
    Crawler crawler;
    
    @Ignore
    @Test
    public void runTest(){
        
        /**
         * You have to set test url on system env.
         * I respect newspaper company who is my tester.
         *
          **/
        String testUrl = System.getenv("RSS_TEST_URL");
        List<String> urlList = new ArrayList<String>();
        urlList.add(testUrl);
        crawler.run(urlList);
    }
    
}
