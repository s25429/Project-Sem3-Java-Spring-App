package com.test.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.tables.Collection;

public class CollectionDto implements DtoObject<Collection> {
    @JsonProperty("title")
    public String title;

    @JsonProperty("url")
    public String source;

    @JsonProperty("href")
    public String query;


    @Override
    public Collection toEntity() {
        var o = new Collection();
        o.title = title;
        o.source = source;
        o.query = query;
        return o;
    }
}
