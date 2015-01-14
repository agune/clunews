package com.agun.repository;

import com.agun.model.RssData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by agun on 2015. 1. 7..
 */
public interface RssDataRepository extends JpaRepository<RssData, Long> {
    public Long countByRssId(Long rssId);
}
