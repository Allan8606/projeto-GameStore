package com.gamestore.projeto_game_store.dtos.response;

import com.gamestore.projeto_game_store.models.GameModel;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record PlataformaResponse(UUID id, String plataforma, Set<String> games) {
}
