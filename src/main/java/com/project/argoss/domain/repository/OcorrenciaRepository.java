package com.project.argoss.domain.repository;

import com.project.argoss.domain.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    @Query("SELECT o FROM Ocorrencia o WHERE o.aluno.id = :id ORDER BY o.dataOcorrencia DESC")
    List<Ocorrencia> findByAlunoId(Long id);
}