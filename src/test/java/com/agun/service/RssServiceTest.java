package com.agun.service;

import com.agun.model.Rss;
import com.agun.model.RssData;
import com.agun.spring.AppConfig;
import com.agun.spring.JpaConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by agun on 2015. 1. 7..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        AppConfig.class, JpaConfig.class })
public class RssServiceTest {
    
    @Autowired
    private RssService rssService;

    private String testUrl;
    
    @Before
    public void setUp(){

        testUrl = System.getenv("RSS_TEST_URL");
    }
    @Ignore
    @Test
    public void saveRssData(){
        RssData rssData = new RssData();
        rssData.setPubDate(new Date());
        rssData.setTitle("test title");
        rssData.setLink("link test");
        rssData.setDescription("desc test");
        rssData.setRssId(1L);
        rssService.saveRssData(rssData);
    }
    
    @Ignore
    @Test
    public void saveRss(){
        Rss rss = new Rss();
        rss.setName("test");
        rss.setUrl(testUrl);
        Rss saveRss = rssService.saveRss(rss);
    }

    @Ignore
    @Test
    public void requestTest(){
        Rss rss = new Rss();
        rss.setUrl(testUrl);
        rss.setId(1L);
        rssService.requestRss(rss);
    }
    
    @Ignore
    @Test
    public void facadeRequest(){
        Rss rss = new Rss();
        rss.setUrl(testUrl);
        rss.setName("news rss");
        rss.setId(5L);
        rssService.facadeRequest(rss);
    }
    @Ignore
    @Test
    public void getRssDataList(){
        List<RssData> rssDataList = rssService.getRssDataList(1);     
        assertTrue("fail getRssDataList", rssDataList.size() > 0);
    }
    @Ignore
    @Test
    public void getRssList(){
        List<Rss> rssList = rssService.getRssList(1);
        assertTrue("fail getRssList", rssList.size() > 0);   
    }
    @Ignore
    @Test
    public void rssDataCount(){
        Long count = rssService.rssDataCountByRssId(5L);
        assertTrue("not count", count >0);
    }

}
