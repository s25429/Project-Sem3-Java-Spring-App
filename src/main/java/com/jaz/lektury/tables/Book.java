package com.jaz.lektury.tables;

import com.jaz.lektury.tables.interfaces.Overwrite;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class Book implements Overwrite<Book> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Basic info
    public String title;
    public String source;
    public boolean hasAudiobook;

    // Relational info
    @ManyToOne
    public Author author;
    @ManyToOne
    public Kind kind;
    @ManyToOne
    public Genre genre;
    @ManyToOne
    public Epoch epoch;

    // Backend Info
    public String fullSortKey;
    public String slug;
    public String query;

    // Thumbnail Info
    public String coverThumb;
    public String simpleThumb;
    public String coverColor;


    @Override
    public void overwrite(Book obj) {
        title = obj.title;
        author = obj.author;
        kind = obj.kind;
        genre = obj.genre;
        epoch = obj.epoch;
        source = obj.source;
        hasAudiobook = obj.hasAudiobook;
        fullSortKey = obj.fullSortKey;
        slug = obj.slug;
        query = obj.query;
        coverThumb = obj.coverThumb;
        simpleThumb = obj.simpleThumb;
        coverColor = obj.coverColor;
    }


    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                title,
                author,
                kind,
                genre,
                epoch,
                source,
                hasAudiobook,
                fullSortKey,
                slug,
                query,
                coverThumb,
                simpleThumb,
                coverColor
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        var o = (Book) obj;
        return (id.equals(o.id)
                && title.equals(o.title)
                && author.equals(o.author)
                && kind.equals(o.kind)
                && genre.equals(o.genre)
                && epoch.equals(o.epoch)
                && source.equals(o.source)
                && hasAudiobook == o.hasAudiobook
                && fullSortKey.equals(o.fullSortKey)
                && slug.equals(o.slug)
                && query.equals(o.query)
                && coverThumb.equals(o.coverThumb)
                && simpleThumb.equals(o.simpleThumb)
                && coverColor.equals(o.coverColor)
        );
    }
}
