package com.gamestore.projeto_game_store.controllers;

import com.gamestore.projeto_game_store.dtos.request.ReviewRequest;
import com.gamestore.projeto_game_store.dtos.response.ReviewResponse;
import com.gamestore.projeto_game_store.models.ReviewModel;
import com.gamestore.projeto_game_store.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("listarTodos")
    public ResponseEntity<List<ReviewResponse>> listarTodos(){
        List<ReviewResponse> reviewResponses = reviewService.listarTodos();
        return ResponseEntity.ok(reviewResponses);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ReviewResponse> criar(@RequestBody ReviewRequest reviewRequest, @PathVariable UUID id){
        ReviewResponse atualizar = reviewService.atualizar(reviewRequest, id);
        return ResponseEntity.ok(atualizar);

    }
}
