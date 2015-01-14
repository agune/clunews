package com.agun.component;

import com.agun.crawler.LowCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by agun on 2015. 1. 12..
 * web crawler component
 */
@Component
public class Crawler {


    public void run(List<String> urlList){
        /**
         * TODO crawler work dir  
         * manual config dir
         */
        String crawlStorageFolder = "/Users/agun/crawl";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(0);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try {
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
            for(String url : urlList) {
                controller.addSeed(url);
            }
            controller.start(LowCrawler.class, numberOfCrawlers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
