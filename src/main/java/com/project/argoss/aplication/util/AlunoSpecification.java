package com.project.argoss.aplication.util;


import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Turma;
import com.project.argoss.domain.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AlunoSpecification {

    public static Specification<Aluno> comNome(String nome) {
        return (root, query, criteriaBuilder) ->
                nome == null ? null : criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Aluno> comStatus(Status status){
        return ((root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status));
    }

    public static Specification<Aluno> comTurma(Long turmaId){
        return  ((root, query, criteriaBuilder) ->
                turmaId == null? null : criteriaBuilder.equal(root.get("turma").get("id"), turmaId));
    }


    public static Specification<Aluno> comDataEntre(LocalDate dataInicial, LocalDate dataFinal) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicial == null && dataFinal == null) {
                return null;
            }
            if (dataInicial != null && dataFinal != null) {
                return criteriaBuilder.between(root.get("dataAluno"), dataInicial, dataFinal);
            }
            if (dataInicial != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dataAluno"), dataInicial);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("dataAluno"), dataFinal);
        };
    }
}
