package com.test.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // General Info
    private String title;
    private String author;

    // Special Info
    private String kind;
    private String genre;
    private String epoch;

    // Other Info
    private String source; // url
    private boolean has_audiobook; // has_audio
//    private String liked;

    // Backend Info
    private String fullSortKey;
    private String slug;
    private String query; // href

    // Thumbnail Info
    private String coverThumb;
    private String simpleThumb;
    private String coverColor;
}
