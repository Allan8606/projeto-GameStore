package com.gamestore.projeto_game_store.dtos.response;

import lombok.Builder;


import java.util.Set;
import java.util.UUID;

@Builder
public record StudioResponse(UUID id, String nome, Set<String> games) {
}
