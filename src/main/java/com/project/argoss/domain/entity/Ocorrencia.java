package com.project.argoss.domain.entity;


import com.project.argoss.domain.enums.Gravidade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "data_ocorrencia")
    private LocalDate dataOcorrencia = LocalDate.now();

    @Column(name = "hora_ocorrencia")
    private LocalTime horaOcorrencia = LocalTime.now();

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Enumerated(EnumType.STRING)
    private Gravidade gravidade;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Ocorrencia(String observacao, Gravidade gravidade) {
        this.observacao = observacao;
        this.gravidade = gravidade;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", observacao='" + observacao + '\'' +
                ", aluno=" + (aluno != null ? "Aluno{id=" + aluno.getId() + ", nome='" + aluno.getNome() + "'}" : "null") +
                '}';
    }



}