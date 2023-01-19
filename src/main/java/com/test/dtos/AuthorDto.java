package com.test.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.tables.Author;

public class AuthorDto implements DtoObject<Author> {
    @JsonProperty("url")
    public String source;

    @JsonProperty("href")
    public String query;

    @JsonProperty("name")
    public String fullname;

    @JsonProperty("slug")
    public String slug;


    @Override
    public Author toEntity() {
        var o = new Author();
        o.fullname = fullname;
        o.source = source;
        o.query = query;
        o.slug = slug;
        return o;
    }
}
