package com.example.playmysongsbackend.controller;

import com.example.playmysongsbackend.entity.Song;
import com.example.playmysongsbackend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class SongsRestController {
    
    @GetMapping(value = "/test")
    public ResponseEntity<Object> testarConexao() {
        return ResponseEntity.ok("Bem vindo a API de músicas");
    }

    @GetMapping(value = "/get-all-songs")
    public ResponseEntity<Object> getAllSongs() {
        File uploadsFolder = new File("./src/main/resources/static/songs");
        List<Song> songs = new ArrayList<>();

        for (File file : uploadsFolder.listFiles()) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.endsWith(".mp3")) {
                    songs.add(nameArqToObject(fileName));
                }
            }
        }

        return ResponseEntity.ok(songs);
    }

    @GetMapping(value = "/get-musicas/{nome}")
    public ResponseEntity<Object> getMusicas(@PathVariable("nome") String nome){
        List<Song> resultados = new ArrayList<>();
        nome = nome.replaceAll("\\s+", "");
        File pasta = new File("src/main/resources/static/songs");
        for (File file : pasta.listFiles()) {
            if (file.isFile()) {
                String fileName = file.getName();
                Pattern pattern = Pattern.compile(Pattern.quote(nome), Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.find() && fileName.endsWith(".mp3")) {
                    resultados.add(nameArqToObject(fileName));
                }
            }
        }
        return ResponseEntity.ok(resultados);
    }


    @PostMapping(value = "/add-song-audio")
    public ResponseEntity<Object> addSongAudio(@RequestParam("estilo") String estilo, @RequestParam("nome") String nome, @RequestParam("cantor") String cantor, @RequestParam("audio") MultipartFile audio) {
        nome = nome.replaceAll("\\s+", "");
        cantor = cantor.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcherNome = pattern.matcher(nome);
        Matcher matcherArtista = pattern.matcher(cantor);

        if(matcherNome.matches() && matcherArtista.matches()) {
            String nomeArquivo = nome + "_" + estilo + "_" + cantor + ".mp3";

            Path root = Paths.get("./src/main/resources/static/songs");

            try {
                Files.copy(audio.getInputStream(), root.resolve(nomeArquivo));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
            }
        }
    
        return ResponseEntity.ok("ok, música adicionada");
    }


    private Song nameArqToObject(String fileName) {
        String[] palavras = fileName.split("_");

        String[] removeMp3 = palavras[2].split(".mp3");
        Song song = new Song(palavras[0], palavras[1], removeMp3[0], fileName);

        song.setNome(song.separateWords(song.getNome()));
        song.setEstilo(song.separateWords(song.getEstilo()));
        song.setCantor(song.separateWords(song.getCantor()));

        return song;
    }
}
