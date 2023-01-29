package com.jaz.lektury.tables;

import com.jaz.lektury.tables.interfaces.Overwrite;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class Author implements Overwrite<Author> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String fullname; // e.x. Anatole Le Braz
    public String source; // url
    public String query; // href
    public String slug; // e.x. anatole-le-braz


    @Override
    public void overwrite(Author obj) {
        fullname = obj.fullname;
        source = obj.source;
        slug = obj.slug;
        query = obj.query;
    }


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
