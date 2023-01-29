package com.jaz.lektury.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaz.lektury.dtos.interfaces.DtoObject;
import com.jaz.lektury.tables.Kind;

public class KindDto implements DtoObject<Kind> {
    @JsonProperty("name")
    public String name;

    @JsonProperty("url")
    public String source;

    @JsonProperty("href")
    public String query;

    @JsonProperty("slug")
    public String slug;


    @Override
    public Kind toEntity() {
        var o = new Kind();
        o.name = name;
        o.source = source;
        o.query = query;
        o.slug = slug;
        return o;
    }
}
