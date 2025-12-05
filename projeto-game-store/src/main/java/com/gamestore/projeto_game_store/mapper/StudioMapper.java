package com.gamestore.projeto_game_store.mapper;


import com.gamestore.projeto_game_store.dtos.request.StudioRequest;
import com.gamestore.projeto_game_store.dtos.response.StudioResponse;
import com.gamestore.projeto_game_store.models.GameModel;
import com.gamestore.projeto_game_store.models.StudioModel;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class StudioMapper {

    public static StudioModel paraStudioModel(StudioRequest studioRequest){
        return StudioModel.builder()
                .nome(studioRequest.nome())
                .build();

    }


    public static StudioResponse paraStudioResponse(StudioModel studioModel){



        Set<String> collect = studioModel.getGames() == null
                ? Set.of()
                : studioModel.getGames()
                .stream()
                .map(GameModel::getTitulo)
                .collect(Collectors.toSet());



        return StudioResponse.builder()
                .id(studioModel.getId())
                .nome(studioModel.getNome())
                .games(collect)
                .build();
    }







}





