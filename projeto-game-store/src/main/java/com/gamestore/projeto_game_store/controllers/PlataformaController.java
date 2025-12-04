package com.gamestore.projeto_game_store.controllers;

import com.gamestore.projeto_game_store.dtos.request.PlataformaRequest;
import com.gamestore.projeto_game_store.dtos.response.PlataformaResponse;
import com.gamestore.projeto_game_store.models.PlataformaModel;
import com.gamestore.projeto_game_store.repositories.PlataformaRepository;
import com.gamestore.projeto_game_store.services.PlataformaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plataforma")
@RequiredArgsConstructor
public class PlataformaController {

    private final PlataformaService plataformaService;

    @PostMapping("/criar")
    public ResponseEntity<PlataformaResponse>  criar(@RequestBody PlataformaRequest plataformaRequest){
        return ResponseEntity.ok(plataformaService.criar(plataformaRequest));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<PlataformaResponse>> listarTodos(){
        return ResponseEntity.ok(plataformaService.listarTodos());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        plataformaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<PlataformaResponse> atualizar(@RequestBody PlataformaRequest plataformaRequest, @PathVariable UUID id){
        return ResponseEntity.ok(plataformaService.atualizar(plataformaRequest, id));
    }

}
