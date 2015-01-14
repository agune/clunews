package com.agun.component;

import com.agun.model.RssContent;
import com.agun.model.RssData;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by agun on 2015. 1. 7..
 * request rss component
 */
@Component
public class RssRequester {
    
    private SyndFeedInput input = new SyndFeedInput();
    
    public List<RssData> request(String url){
        URL feedUrl = null;
        try {
            feedUrl = new URL(url);
            SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
            if(syndFeed == null || syndFeed.getEntries().size() == 0)
                return Collections.emptyList();
            
            List<RssData> resultList =  new ArrayList<RssData>();
            for(Object feed : syndFeed.getEntries()){
                RssData rssData = new RssData();
                rssData.setLink(((SyndEntryImpl)feed).getLink());
                rssData.setDescription(((SyndEntryImpl)feed).getDescription().getValue());
                rssData.setTitle(((SyndEntryImpl)feed).getTitle());
                rssData.setPubDate(((SyndEntryImpl)feed).getPublishedDate());
                resultList.add(rssData);
            }
            return resultList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public RssContent requestContent(String link){
        RssContent rssContent = new RssContent();
        return rssContent;
    }
    
}
