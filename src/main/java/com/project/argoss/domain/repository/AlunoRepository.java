package com.project.argoss.domain.repository;

import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))  ORDER BY a.nome ASC")
    List<Aluno> buscarPorNome(@Param("nome") String nome);

    List<Aluno> findByTurma(Turma turma);


}