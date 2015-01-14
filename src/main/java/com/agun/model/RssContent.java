package com.agun.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by agun on 2015. 1. 12..
 */


@Data
@Entity
@Table(name="rssContent")
public class RssContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String link;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    private Date createDate;
    
}
