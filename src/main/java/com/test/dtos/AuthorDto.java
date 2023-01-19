package com.test.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDto {
    @JsonProperty("url")
    private String source;

    @JsonProperty("href")
    private String query;

    @JsonProperty("name")
    private String fullname;

    @JsonProperty("slug")
    private String slug;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
