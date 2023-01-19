package com.test.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Genre implements Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;
    public String source; // url
    public String query; // href
    public String slug;


    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                source,
                query,
                slug
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        var o = (Genre) obj;
        return (id.equals(o.id)
                && name.equals(o.name)
                && source.equals(o.source)
                && query.equals(o.query)
                && slug.equals(o.slug)
        );
    }
}
