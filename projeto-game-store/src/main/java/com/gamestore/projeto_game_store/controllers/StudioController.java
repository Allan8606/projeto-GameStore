package com.gamestore.projeto_game_store.controllers;

import com.gamestore.projeto_game_store.dtos.request.StudioRequest;
import com.gamestore.projeto_game_store.dtos.response.StudioResponse;
import com.gamestore.projeto_game_store.models.ReviewModel;
import com.gamestore.projeto_game_store.models.StudioModel;
import com.gamestore.projeto_game_store.services.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/studio")
@RequiredArgsConstructor
public class StudioController {

    private final StudioService studioService;


    @PostMapping("/criar")
    public ResponseEntity<StudioResponse> criar(@RequestBody StudioRequest studioRequest){
        StudioResponse criar = studioService.criar(studioRequest);

        return ResponseEntity.ok(criar);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<StudioResponse>> listarTodos(){
        return ResponseEntity.ok(studioService.listarTodos());
    }

    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<StudioResponse> listarPorId(@PathVariable UUID id){
        StudioResponse studioResponse = studioService.listarPorID(id);

        return ResponseEntity.ok(studioResponse);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<StudioResponse> atualizar(@RequestBody StudioRequest studioRequest, @PathVariable UUID id){
        StudioResponse atualizar = studioService.atualizar(studioRequest, id);

        return ResponseEntity.ok(atualizar);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        studioService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }




}
