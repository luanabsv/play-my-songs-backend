package com.example.playmysongsbackend.entity;

public class Song {
    private String nome;
    private String estilo;
    private String cantor;

    private String nomeArquivo;


    public Song(String nome, String estilo, String cantor, String nomeArquivo) {
        this.nome = nome;
        this.estilo = estilo;
        this.cantor = cantor;
        this.nomeArquivo = nomeArquivo;
    }

    public Song() {
        this.estilo = "";
        this.nome = "";
        this.cantor = "";
        this.nomeArquivo = "";
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public static String separateWords(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            // Verifica se o caractere atual é maiúsculo e não é o primeiro caractere
            if (Character.isUpperCase(currentChar) && i > 0) {
                // Adiciona um espaço antes da letra maiúscula
                builder.append(' ');
            }
            // Adiciona o caractere atual ao StringBuilder
            builder.append(currentChar);
        }
        // Retorna a string resultante
        return builder.toString();
    }

}
