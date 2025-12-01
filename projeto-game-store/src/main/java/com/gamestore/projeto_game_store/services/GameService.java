package com.gamestore.projeto_game_store.services;

import com.gamestore.projeto_game_store.dtos.GameRequestDto;
import com.gamestore.projeto_game_store.models.GameModel;
import com.gamestore.projeto_game_store.models.PlataformaModel;
import com.gamestore.projeto_game_store.models.ReviewModel;
import com.gamestore.projeto_game_store.models.StudioModel;
import com.gamestore.projeto_game_store.repositories.GameRepository;
import com.gamestore.projeto_game_store.repositories.PlataformaRepository;
import com.gamestore.projeto_game_store.repositories.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final PlataformaRepository plataformaRepository;
    private final StudioRepository studioRepository;


    @Transactional
    public GameModel criar(GameRequestDto gameRequestDto){

        GameModel game = new GameModel();

        game.setTitulo(gameRequestDto.titulo());

        UUID uuiStudio = gameRequestDto.studioId();
        StudioModel studioModel = studioRepository.findById(uuiStudio).get();
        game.setStudio(studioModel);

        Set<UUID> plataformasIds = gameRequestDto.plataformasIds();
        Set<PlataformaModel> listaIdsPlataformas = plataformaRepository.findAllById(plataformasIds).stream()
                .collect(Collectors.toSet());
        game.setPlataformas(listaIdsPlataformas);


        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComentario(gameRequestDto.reviewComentario());
        reviewModel.setGame(game);
        reviewModel.setNota(gameRequestDto.reviewNota());

        game.setReview(reviewModel);

        return gameRepository.save(game);
    }

    public List<GameModel> buscarPeloTitulo(String titulo){
        return gameRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<GameModel> listarTodos(){
        return gameRepository.findAll();
    }
}
