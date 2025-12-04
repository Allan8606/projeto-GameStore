package com.gamestore.projeto_game_store.repositories;

import com.gamestore.projeto_game_store.models.PlataformaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlataformaRepository extends JpaRepository<PlataformaModel, UUID> {

    Optional<PlataformaModel> findByPlataformaIgnoreCase(String plataforma);

}
