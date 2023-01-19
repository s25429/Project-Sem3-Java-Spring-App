package com.test.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.tables.Theme;

public class ThemeDto implements DtoObject<Theme> {
    @JsonProperty("name")
    public String name;

    @JsonProperty("url")
    public String source;

    @JsonProperty("href")
    public String query;

    @JsonProperty("slug")
    public String slug;


    @Override
    public Theme toEntity() {
        var o = new Theme();
        o.name = name;
        o.source = source;
        o.query = query;
        o.slug = slug;
        return o;
    }
}
