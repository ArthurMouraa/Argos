package com.project.argoss.domain.entity;


import com.project.argoss.domain.enums.Saude;
import com.project.argoss.domain.enums.Sexo;
import com.project.argoss.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "tdh", length = 100)
    @Enumerated(EnumType.STRING)
    private Saude tdh;

    @Column(name = "tea", length = 100)
    @Enumerated(EnumType.STRING)
    private Saude tea;

    @Column(name = "foto")
    private String foto;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ON;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;


    @ManyToMany
    @JoinTable(
            name = "aluno_usuario",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Aluno(String nome, String matricula, Saude tdh, Saude tea, String foto, Sexo sexo, Turma turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.tdh = tdh;
        this.tea = tea;
        this.foto = foto;
        this.sexo = sexo;
        this.turma = turma;
    }
}
