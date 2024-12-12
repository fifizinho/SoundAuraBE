package com.projetosoundaura.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetosoundaura.entities.Musica;
import com.projetosoundaura.repository.MusicaRepository;

@Service
public class MusicaService {
    private final MusicaRepository MusicaRepository;
    
    @Autowired
    public MusicaService(MusicaRepository MusicaRepository) {
        this.MusicaRepository = MusicaRepository;
    }

    public List<Musica> getAllMusicas() {
        return MusicaRepository.findAll();
    }

    public Musica getMusicaById(Long id) {
        Optional<Musica> Musica = MusicaRepository.findById(id);
        return Musica.orElse(null);
    }

    public Musica salvarMusica(Musica Musica) {
        return MusicaRepository.save(Musica);
    }

    public Musica updateMusica(Long id, Musica updatedMusica) {
        Optional<Musica> existingMusica = MusicaRepository.findById(id);
        if (existingMusica.isPresent()) {
            updatedMusica.setId(id);
            return MusicaRepository.save(updatedMusica);
        }
        return null;
    }

    public boolean deleteMusica(Long id) {
        Optional<Musica> existingMusica = MusicaRepository.findById(id);
        if (existingMusica.isPresent()) {
            MusicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
