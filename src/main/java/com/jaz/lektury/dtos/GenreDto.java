package com.jaz.lektury.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaz.lektury.dtos.interfaces.DtoObject;
import com.jaz.lektury.tables.Genre;

public class GenreDto implements DtoObject<Genre> {
    @JsonProperty("name")
    public String name;

    @JsonProperty("url")
    public String source;

    @JsonProperty("href")
    public String query;

    @JsonProperty("slug")
    public String slug;


    @Override
    public Genre toEntity() {
        var o = new Genre();
        o.name = name;
        o.source = source;
        o.query = query;
        o.slug = slug;
        return o;
    }
}
