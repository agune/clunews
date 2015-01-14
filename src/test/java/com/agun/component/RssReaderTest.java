package com.agun.component;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by agun on 2015. 1. 7..
 */

public class RssReaderTest {


    
    @Ignore
    @Test
    public void SyndFeedTest(){
       
        /**
         * You have to set test url on system env.
         * I respect newspaper company who is my tester.
         *
         **/
        
        
        try {
            String testUrl = System.getenv("RSS_TEST_URL");
            URL feedUrl = new URL(testUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
                       
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
