package com.theironyard;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String artist;

    @Column(nullable = false)
    String albumName;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    String releaseDate;

    @Column(nullable = false)
    int rating;

    @ManyToOne
    User user;

    public Album() {
    }

    public Album(String artist, String albumName, String genre, String releaseDate, int rating) {
        this.artist = artist;
        this.albumName = albumName;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }
}
