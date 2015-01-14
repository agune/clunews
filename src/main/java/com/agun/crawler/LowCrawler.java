package com.agun.crawler;

import com.agun.model.RssContent;
import com.agun.model.RssHistory;
import com.agun.service.RssService;
import com.agun.spring.SpringContext;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by agun on 2015. 1. 12..
 */
public class LowCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
   
    private RssService rssService;
    
    public LowCrawler(){
        rssService = (RssService) SpringContext.getBean("rssService");
    }
    
    @Override
    public boolean shouldVisit(Page page, WebURL url){
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }
    
    @Override
    public void visit(Page page){


        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            RssContent rssContent = new RssContent();
            rssContent.setContent(htmlParseData.getText());
            rssContent.setLink(url);
            rssContent.setCreateDate(new Date());
            rssService.saveRssContent(rssContent);
        }
    }
        
}
