package com.gamestore.projeto_game_store.mapper;


import com.gamestore.projeto_game_store.dtos.request.ReviewRequest;
import com.gamestore.projeto_game_store.dtos.response.ReviewResponse;
import com.gamestore.projeto_game_store.models.ReviewModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewMapper {

    public static ReviewResponse paraReviewResponse(ReviewModel model){

        String titulo = model.getGame().getTitulo();

        return ReviewResponse.builder()
                .id(model.getId())
                .comentario(model.getComentario())
                .nota(model.getNota())
                .titulo(titulo)
                .build();
    }



    public static ReviewModel paraReviewModel(ReviewRequest request){
        return ReviewModel.builder()
                .comentario(request.comentario())
                .nota(request.nota())
                .build();
    }



}
