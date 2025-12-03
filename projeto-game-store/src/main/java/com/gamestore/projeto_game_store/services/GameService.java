package com.gamestore.projeto_game_store.services;


import com.gamestore.projeto_game_store.dtos.request.GameRequest;
import com.gamestore.projeto_game_store.dtos.response.GameResponse;
import com.gamestore.projeto_game_store.mapper.GameMapper;
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
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final PlataformaRepository plataformaRepository;
    private final StudioRepository studioRepository;

    private final StudioService studioService;


    @Transactional
    public GameResponse criar(GameRequest gameRequestDto){

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

        GameModel save = gameRepository.save(game);
        GameResponse gameResponse = GameMapper.paraResponse(save);
        return gameResponse;
    }

    public List<GameResponse> buscarPeloTitulo(String titulo){
        List<GameModel> listaNomes = gameRepository.findByTituloContainingIgnoreCase(titulo);

       return listaNomes.stream()
                .map(game -> GameMapper.paraResponse(game))
                .toList();
    }

    public GameResponse buscarPorId(UUID id) {
        GameModel game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado"));

        return GameMapper.paraResponse(game);
    }


    public List<GameResponse> listarTodos(){

        List<GameModel> todosGames = gameRepository.findAll();
        return todosGames.stream()
                .map(GameMapper::paraResponse).toList();
    }

    public GameResponse atualizarGame(UUID id, GameRequest gameRequest) {


        GameModel gameEncontrado = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado"));


        gameEncontrado.setTitulo(gameRequest.titulo());



        StudioModel studio = studioRepository.findById(gameRequest.studioId())
                .orElseThrow(() -> new RuntimeException("Studio não encontrado"));
        gameEncontrado.setStudio(studio);


        Set<PlataformaModel> plataformas = plataformaRepository.findAllById(gameRequest.plataformasIds())
                .stream()
                .collect(Collectors.toSet());
        gameEncontrado.setPlataformas(plataformas);

        ReviewModel review = gameEncontrado.getReview();
        if (review == null) {
            review = new ReviewModel();
            review.setGame(gameEncontrado); // importante no OneToOne
        }

        review.setComentario(gameRequest.reviewComentario());
        review.setNota(gameRequest.reviewNota());
        gameEncontrado.setReview(review);

        GameModel atualizado = gameRepository.save(gameEncontrado);

        return GameMapper.paraResponse(atualizado);
    }


    public void deletar(UUID id) {
        GameModel game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado"));
        gameRepository.delete(game);
    }

}
