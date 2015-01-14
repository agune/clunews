package com.agun.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by agun on 2015. 1. 7..
 */
@Entity
@Table(name="rss")
@Data
public class Rss {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;
}
