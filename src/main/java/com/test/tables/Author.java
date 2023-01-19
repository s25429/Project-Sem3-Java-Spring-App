package com.test.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String fullname; // e.x. Anatole Le Braz
    public String source; // url
    public String query; // href
    public String slug; // e.x. anatole-le-braz


    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                fullname,
                source,
                query,
                slug
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        var o = (Author) obj;
        return (id.equals(o.id)
                && fullname.equals(o.fullname)
                && source.equals(o.source)
                && query.equals(o.query)
                && slug.equals(o.slug)
        );
    }
}
