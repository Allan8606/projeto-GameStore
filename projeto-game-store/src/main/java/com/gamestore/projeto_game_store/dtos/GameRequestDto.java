package com.gamestore.projeto_game_store.dtos;

import java.util.Set;
import java.util.UUID;

public record GameRequestDto(
        String titulo,
        UUID studioId,
        Set<UUID> plataformasIds,
        String reviewComentario,
        double reviewNota) {
}
