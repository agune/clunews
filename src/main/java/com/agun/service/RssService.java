package com.agun.service;

import com.agun.component.Crawler;
import com.agun.component.RssRequester;
import com.agun.model.Rss;
import com.agun.model.RssContent;
import com.agun.model.RssData;
import com.agun.model.RssHistory;
import com.agun.repository.RssContentRepository;
import com.agun.repository.RssDataRepository;
import com.agun.repository.RssHistoryRepository;
import com.agun.repository.RssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by agun on 2015. 1. 7..
 */

@Service
public class RssService {
    
    @Autowired
    private RssRepository rssRepository;

    @Autowired
    private RssDataRepository rssDataRepository;
    
    @Autowired
    private RssRequester rssRequester;
    
    @Autowired
    private RssContentRepository rssContentRepository;

    @Autowired
    private RssHistoryRepository rssHistoryRepository;
    
    @Autowired
    private Crawler crawler;

    public List<RssData> requestRss(Rss rss){
        
        
        List<RssData> rssDataList =  rssRequester.request(rss.getUrl());
        List<RssData> resultDataList =  new ArrayList<RssData>();
        
        for(RssData rssData : rssDataList){


            List<RssHistory> rssHistoryList = rssHistoryRepository.findByLink(rssData.getLink());

            if(rssHistoryList != null && rssHistoryList.size() > 0)
                continue;

            /**
             * save
             */
            rssData.setRssId(rss.getId());
            saveRssData(rssData);
            
            /**
             * save request history 
             */
            RssHistory rssHistory = new RssHistory();
            rssHistory.setRegDate(new Date());
            rssHistory.setLink(rssData.getLink());
            saveRssHistory(rssHistory);
            resultDataList.add(rssData);
        }
        return resultDataList;
    }
    

    
    public void requestRssContent(List<RssData> rssDataList){
        List<String> urlList = new ArrayList<String>();
        for(RssData rssData : rssDataList){
            urlList.add(rssData.getLink());
        }
        crawler.run(urlList);
    }
    
    public RssData saveRssData(RssData rssData){
        return rssDataRepository.save(rssData);
    }
    
    public Rss saveRss(Rss rss){
        return rssRepository.save(rss);
    }
    
    public  void facadeRequest(Rss rss){
        if(rss != null){
            List<RssData> rssDataList = requestRss(rss);
            if(rssDataList.size() > 0){
                requestRssContent(rssDataList);
            }
        }
    }
    
    public RssContent saveRssContent(RssContent rssContent){
        return rssContentRepository.save(rssContent);
    }
    
    public List<RssData> getRssDataList(int page){
        PageRequest pageRequest = new PageRequest(page -1, 20, Sort.Direction.DESC, "id");
        
        Page<RssData> rssDataPage =  rssDataRepository.findAll(pageRequest);
        
        if(rssDataPage == null)
            return Collections.emptyList();
        
        return rssDataPage.getContent();
    }

    public List<Rss> getRssList(int page){
        PageRequest pageRequest = new PageRequest(page -1, 20, Sort.Direction.DESC, "id");
        Page<Rss> rssPage = rssRepository.findAll(pageRequest);
        if(rssPage == null)
            return Collections.emptyList();
        return rssPage.getContent();
    }
    
    public List<RssContent> findRssContentByLink(String link){
        List<RssContent> rssContentList = rssContentRepository.findByLink(link);
        if(rssContentList == null)
          return Collections.emptyList();
        return rssContentList;
    }
    
    public Long rssDataCountByRssId(Long rssId){
        return rssDataRepository.countByRssId(rssId);
    }

    public RssHistory saveRssHistory(RssHistory rssHistory){
        return rssHistoryRepository.save(rssHistory);
    }
}
