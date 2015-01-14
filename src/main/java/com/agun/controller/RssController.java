package com.agun.controller;

import com.agun.model.Rss;
import com.agun.model.RssContent;
import com.agun.model.RssData;
import com.agun.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by agun on 2015. 1. 12..
 */

@Controller
public class RssController {

    @Autowired
    private RssService rssService;
    
    @RequestMapping("/")
    public ModelAndView rss(){
        List<Rss> rssList = rssService.getRssList(1);
        return new ModelAndView("rss").addObject("rssList", rssList);
    }
    
    @RequestMapping("/rssData")
    public ModelAndView rssData(){
       List<RssData> rssDataList = rssService.getRssDataList(1);
        return new ModelAndView("rssData").addObject("rssDataList", rssDataList);
    }

    @RequestMapping("/rssContent")
    public ModelAndView rssContent(@RequestParam("link") String link){
        List<RssContent> rssContentList = rssService.findRssContentByLink(link);
        return new ModelAndView("rssContent").addObject("contentList", rssContentList);
    }

}
