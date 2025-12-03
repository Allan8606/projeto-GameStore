package com.gamestore.projeto_game_store.mapper;



import com.gamestore.projeto_game_store.dtos.request.GameRequest;
import com.gamestore.projeto_game_store.dtos.response.GameResponse;
import com.gamestore.projeto_game_store.models.GameModel;
import com.gamestore.projeto_game_store.models.PlataformaModel;
import com.gamestore.projeto_game_store.models.ReviewModel;
import com.gamestore.projeto_game_store.models.StudioModel;

import lombok.experimental.UtilityClass;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class GameMapper {


    public GameResponse paraResponse(GameModel gameModel){

        //Pegando apenas o nome das plataformas
        Set<String> listaNomes = gameModel.getPlataformas().stream()
                .map(p -> p.getPlataforma()).collect(Collectors.toSet());

        //Pegando apenas o comentario
        String comentario = gameModel.getReview().getComentario();

//      Pegando apenas o valor da nota
        double nota = gameModel.getReview().getNota();

        return GameResponse.builder()
                .id(gameModel.getId())
                .titulo(gameModel.getTitulo())
                .plataformas(listaNomes)
                .reviewComentario(comentario)
                .reviewNota(nota)
                .build();
    }


    public GameModel paraModel(GameRequest gameRequestDto,
                               StudioModel studio,
                               Set<PlataformaModel> plataformas,
                               ReviewModel review){


        return GameModel.builder()
                .titulo(gameRequestDto.titulo())
                .studio(studio)
                .plataformas(plataformas)
                .review(review)
                .build();
    }




}
