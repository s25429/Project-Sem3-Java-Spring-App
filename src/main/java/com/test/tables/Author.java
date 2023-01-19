package com.test.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname; // e.x. Anatole Le Braz
    private String source; // url
    private String query; // href
    private String slug; // e.x. anatole-le-braz

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

        Author author = (Author) obj;
        return (id.equals(author.id)
                && fullname.equals(author.fullname)
                && source.equals(author.source)
                && query.equals(author.query)
                && slug.equals(author.slug)
        );
    }
}
