package com.test.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.tables.Book;

public class BookDto implements DtoObject<Book> {
    @JsonProperty("title")
    public String title;

    @JsonProperty("author")
    public String author;

    @JsonProperty("kind")
    public String kind;

    @JsonProperty("genre")
    public String genre;

    @JsonProperty("epoch")
    public String epoch;

    @JsonProperty("url")
    public String source;

    @JsonProperty("has_audio")
    public boolean hasAudiobook;

    @JsonProperty("full_sort_key")
    public String fullSortKey;

    @JsonProperty("slug")
    public String slug;

    @JsonProperty("href")
    public String query;

    @JsonProperty("cover_thumb")
    public String coverThumb;

    @JsonProperty("simple_thumb")
    public String simpleThumb;

    @JsonProperty("cover_color")
    public String coverColor;


    @Override
    public Book toEntity() {
        var o = new Book();
        o.title = title;
        o.author = author;
        o.kind = kind;
        o.genre = genre;
        o.epoch = epoch;
        o.source = source;
        o.hasAudiobook = hasAudiobook;
        o.fullSortKey = fullSortKey;
        o.slug = slug;
        o.query = query;
        o.coverThumb = coverThumb;
        o.simpleThumb = simpleThumb;
        o.coverColor = coverColor;
        return o;
    }
}
