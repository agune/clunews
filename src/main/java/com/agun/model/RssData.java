package com.agun.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by agun on 2015. 1. 7..
 */


@Data
@Entity
@Table(name="rssData")
public class RssData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long rssId;
    private String title;
    private String link;
    private Date pubDate;
    
    @Column(columnDefinition = "TEXT")
    private String description;
}
