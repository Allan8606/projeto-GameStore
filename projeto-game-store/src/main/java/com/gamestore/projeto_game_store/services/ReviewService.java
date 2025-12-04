package com.gamestore.projeto_game_store.services;

import com.gamestore.projeto_game_store.dtos.request.ReviewRequest;
import com.gamestore.projeto_game_store.dtos.response.ReviewResponse;
import com.gamestore.projeto_game_store.mapper.ReviewMapper;
import com.gamestore.projeto_game_store.models.GameModel;
import com.gamestore.projeto_game_store.models.ReviewModel;
import com.gamestore.projeto_game_store.repositories.GameRepository;
import com.gamestore.projeto_game_store.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GameRepository gameRepository;


    public List<ReviewResponse> listarTodos(){
        List<ReviewResponse> list = reviewRepository.findAll().stream()
                .map(ReviewMapper::paraReviewResponse).toList();

        return list;
    }


    public ReviewResponse atualizar(ReviewRequest reviewRequest, UUID idGame){

        GameModel buscarGame = gameRepository.findById(idGame).orElseThrow(() -> new RuntimeException("Game não encontrado"));

        ReviewModel reviewAtual = buscarGame.getReview();
        reviewAtual.setComentario(reviewRequest.comentario());
        reviewAtual.setNota(reviewRequest.nota());

        gameRepository.save(buscarGame);

        return ReviewMapper.paraReviewResponse(reviewAtual);
    }
}
