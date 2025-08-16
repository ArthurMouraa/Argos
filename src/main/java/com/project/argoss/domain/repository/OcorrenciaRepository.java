package com.project.argoss.domain.repository;

import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Ocorrencia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>, JpaSpecificationExecutor<Ocorrencia> {
//    @Query("SELECT o FROM Ocorrencia o WHERE o.aluno.id = :id ORDER BY o.dataOcorrencia DESC")
//    List<Ocorrencia> findByAlunoId(Long id, Pageable pageable);
}