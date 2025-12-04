package com.gamestore.projeto_game_store.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_plataforma")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlataformaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String plataforma;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "plataformas", fetch = FetchType.LAZY)
    @Builder.Default //Não permite que a lista nunca seja vazia
    private Set<GameModel> games = new HashSet<>();


}
