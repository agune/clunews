package com.agun.repository;

import com.agun.model.RssContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by agun on 2015. 1. 12..
 */
public interface RssContentRepository extends JpaRepository<RssContent,Long>{
    List<RssContent> findByLink(String link);
}
