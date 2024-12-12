package com.projetosoundaura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetosoundaura.entities.Musica;
import com.projetosoundaura.services.MusicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
@CrossOrigin(origins = "*")
public class MusicaController {
    
    private final MusicaService MusicaService; 
    
    @Autowired
    public MusicaController(MusicaService MusicaService) {
        this.MusicaService = MusicaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> getMusicaById(@PathVariable Long id) {
        Musica Musica = MusicaService.getMusicaById(id);
        if (Musica != null) {
            return ResponseEntity.ok(Musica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Musica>> getAllMusicas() {
        List<Musica> Musicas = MusicaService.getAllMusicas();
        return ResponseEntity.ok(Musicas);
    }

    @PostMapping("/")
    public ResponseEntity<Musica> criarMusica(@RequestBody @Valid Musica Musica) {
        Musica criarMusica = MusicaService.salvarMusica(Musica);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarMusica);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Musica> updateMusica(@PathVariable Long id, @RequestBody @Valid Musica Musica) {
        Musica updatedMusica = MusicaService.updateMusica(id, Musica);
        if (updatedMusica != null) {
            return ResponseEntity.ok(Musica); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusica(@PathVariable Long id) {
        boolean deleted = MusicaService.deleteMusica(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
