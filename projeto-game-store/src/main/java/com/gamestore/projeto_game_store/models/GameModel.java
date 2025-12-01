package com.gamestore.projeto_game_store.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Table(name = "tb_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //nuble, diz se pode ser null ou não.
    //unique quer dizer que é algo unico
    @Column(nullable = false, unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "fk_studio_id")//define o nome dessa coluna
    private StudioModel studio;

    @ManyToMany
    /*@JoinTable(...)
No banco de dados, um relacionamento ManyToMany sempre precisa de uma tabela intermediária.
Essa anotação define essa tabela intermediária.

joinColumns = @JoinColumn(name = "game_id")
Essa parte define a coluna da FK que aponta para Game.
Ou seja:
game_id será a coluna que guarda o ID do Game
Essa coluna SEMPRE representa a entidade onde você declarou o ManyToMany

inverseJoinColumns = @JoinColumn(name = "plataforma_id")
Essa parte define a coluna da FK que aponta para Platform.
Ou seja:
plataforma_id será a coluna que guarda o ID da plataforma
Essa é a “coluna do outro lado” do relacionamento
*/
    @JoinTable(
            name = "tb_game_plataforma", //nome da tabela auxiliar

            joinColumns = @JoinColumn(name = "game_id"),//definimos a coluna que vai receber id do Game

            inverseJoinColumns = @JoinColumn(name = "plataforma_id")//definimos a coluna que vai receber id da plataforma
    )
    private Set<PlataformaModel> plataformas = new HashSet<>();

/*
* Por que cascade = CascadeType.ALL?
Isso controla o comportamento quando você salva, altera ou remove um Game.
Com Cascade.ALL:
Salvar Game → salva Review junto
Atualizar Game → atualiza Review junto
Deletar Game → deleta Review junto
* */
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private ReviewModel review;



}
