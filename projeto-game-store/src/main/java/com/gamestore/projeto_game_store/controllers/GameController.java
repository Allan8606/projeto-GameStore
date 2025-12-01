package com.gamestore.projeto_game_store.controllers;

import com.gamestore.projeto_game_store.dtos.GameRequestDto;
import com.gamestore.projeto_game_store.models.GameModel;
import com.gamestore.projeto_game_store.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/criar")
    public ResponseEntity<GameModel> criar(@RequestBody GameRequestDto game){
        GameModel criar = gameService.criar(game);
        return ResponseEntity.ok().body(criar);
    }

    @GetMapping("/buscarPeloNome")
    public List<GameModel> buscarPeloNome(@RequestParam String titulo){
        return gameService.buscarPeloTitulo(titulo);
    }

    @GetMapping("/listarTodos")
    public List<GameModel> listarTodos(){
        return gameService.listarTodos();
    }

}
