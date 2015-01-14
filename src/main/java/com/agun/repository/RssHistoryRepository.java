package com.agun.repository;

import com.agun.model.RssHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by agun on 2015. 1. 13..
 */
public interface RssHistoryRepository extends JpaRepository<RssHistory, Long> {
    public List<RssHistory> findByLink(String link);
}
