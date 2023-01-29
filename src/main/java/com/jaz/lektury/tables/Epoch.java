package com.jaz.lektury.tables;

import com.jaz.lektury.tables.interfaces.Overwrite;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class Epoch implements Overwrite<Epoch> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String source;
    public String query;
    public String slug;


    @Override
    public void overwrite(Epoch obj) {
        name = obj.name;
        source = obj.source;
        slug = obj.slug;
        query = obj.query;
    }


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

        var o = (Epoch) obj;
        return (id.equals(o.id)
                && name.equals(o.name)
                && source.equals(o.source)
                && query.equals(o.query)
                && slug.equals(o.slug)
        );
    }
}
