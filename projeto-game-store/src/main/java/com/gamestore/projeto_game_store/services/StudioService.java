package com.gamestore.projeto_game_store.services;

import com.gamestore.projeto_game_store.dtos.request.StudioRequest;
import com.gamestore.projeto_game_store.dtos.response.StudioResponse;
import com.gamestore.projeto_game_store.mapper.StudioMapper;
import com.gamestore.projeto_game_store.models.StudioModel;
import com.gamestore.projeto_game_store.repositories.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;

    public StudioResponse criar(StudioRequest studioRequest){

        StudioModel studioModel = StudioMapper.paraStudioModel(studioRequest);
        StudioModel save = studioRepository.save(studioModel);

        return StudioMapper.paraStudioResponse(save);
    }

    public List<StudioResponse> listarTodos(){
        List<StudioModel> todosStudios = studioRepository.findAll();
        List<StudioResponse> list = todosStudios.stream()
                .map(StudioMapper::paraStudioResponse)
                .toList();

        return list;
    }

    public StudioResponse listarPorID(UUID id){
        StudioModel studioModel = studioRepository.findById(id).orElseThrow(() -> new RuntimeException("Studio não encontrado"));

        return StudioMapper.paraStudioResponse(studioModel);
    }

    public StudioResponse atualizar(StudioRequest studioRequest, UUID id){
        StudioModel studioModel = studioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studio não encontrado"));

        studioModel.setNome(studioRequest.nome());
        StudioModel save = studioRepository.save(studioModel);

        return StudioMapper.paraStudioResponse(save);
    }

    public void deletar(UUID id){

        StudioModel studioModel = studioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studio não encontrado"));

        studioRepository.delete(studioModel);

    }
}
