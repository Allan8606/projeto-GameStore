package com.gamestore.projeto_game_store.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_studio")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;


    /*
* O que é o fetch?
O fetch controla como e quando o JPA vai carregar os dados relacionados.
Existem dois tipos:
 FetchType.LAZY
 Só carrega a lista quando você realmente acessa ela.
É o “carregamento preguiçoso”.
 FetchType.EAGER
 Carrega tudo na mesma hora, junto com a entidade principal.
    * */
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)//mappedBy vai mapear nome da variavel que foi salvo na Entidade Game
    /*
    * A API aceita esse campo no JSON de entrada (POST/PUT)
    * A API não devolve esse campo no JSON de saída (GET)
    * */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<GameModel> games = new HashSet<>(); //Inicializar evita erros. já existe, então qualquer operação funciona.

}
