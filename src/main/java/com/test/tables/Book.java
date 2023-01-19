package com.test.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    // General Info
    public String title;
    public String author;

    // Special Info
    public String kind;
    public String genre;
    public String epoch;

    // Other Info
    public String source; // url
    public boolean hasAudiobook; // has_audio
//    private String liked;

    // Backend Info
    public String fullSortKey;
    public String slug;
    public String query; // href

    // Thumbnail Info
    public String coverThumb;
    public String simpleThumb;
    public String coverColor;


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
