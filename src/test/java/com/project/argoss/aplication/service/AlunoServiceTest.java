package com.project.argoss.aplication.service;

import com.project.argoss.domain.entity.Turma;
import com.project.argoss.domain.mapper.AlunoMapper;
import com.project.argoss.domain.repository.AlunoRepository;
import com.project.argoss.domain.repository.TurmaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    AlunoRepository alunoRepository;

    @Mock
    AlunoMapper alunoMapper;

    @Mock
    TurmaRepository turmaRepository;


    @InjectMocks
    AlunoService alunoService;


    @Test
    void criar() {



    }

    @Test
    void buscarPorId() {
    }

    @Test
    void buscarPorNome() {
    }

    @Test
    void listarAlunosPorTurma() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }
}