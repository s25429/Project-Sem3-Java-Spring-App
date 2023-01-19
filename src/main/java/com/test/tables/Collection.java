package com.test.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String title;
    public String source; // url
    public String query; // href


    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                title,
                source,
                query
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        var o = (Collection) obj;
        return (id.equals(o.id)
                && title.equals(o.title)
                && source.equals(o.source)
                && query.equals(o.query)
        );
    }
}
