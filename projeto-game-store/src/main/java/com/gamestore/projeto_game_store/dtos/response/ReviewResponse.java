package com.gamestore.projeto_game_store.dtos.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReviewResponse(UUID id, String comentario, double nota, String titulo) {
}
