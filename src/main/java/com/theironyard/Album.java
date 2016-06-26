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
    int rating;

    @Column(nullable = false)
    int releaseDate;

    @ManyToOne
    User user;

    public Album() {
    }

    public Album(String artist, String albumName, String genre, int rating, int releaseDate) {
        this.artist = artist;
        this.albumName = albumName;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
}
