package com.example.playmysongsbackend.repository;

import com.example.playmysongsbackend.entity.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SongRepository {
    private List<Song> songs;

    public SongRepository() {
        songs = new ArrayList<>();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}