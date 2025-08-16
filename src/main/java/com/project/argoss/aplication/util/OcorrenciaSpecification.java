package com.project.argoss.aplication.util;

import com.project.argoss.domain.entity.Aluno;
import com.project.argoss.domain.entity.Ocorrencia;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class OcorrenciaSpecification {

    public static Specification<Ocorrencia> comUsuario(String id){
        return ((root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("usuario").get("id"), id));


    }

    public static Specification<Ocorrencia> comAlunoId(Long id){
        return ((root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("aluno").get("id"), id));
    }

    public static Specification<Ocorrencia> comDataEntre(LocalDate dataInicial, LocalDate dataFinal) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicial == null && dataFinal == null) {
                return null;
            }
            if (dataInicial != null && dataFinal != null) {
                return criteriaBuilder.between(root.get("dataOcorrencia"), dataInicial, dataFinal);
            }
            if (dataInicial != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dataOcorrencia"), dataInicial);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("dataOcorrencia"), dataFinal);
        };
    }

   public static Specification<Ocorrencia> comNomeAluno(String nome){
       return ((root, query, criteriaBuilder) ->
               nome== null ? null : criteriaBuilder.like(root.get("aluno").get("nome"), "%" + nome + "%"));

   }

   public static  Specification<Ocorrencia> comMatriculaAluno(String matricula){
       return ((root, query, criteriaBuilder) ->
               matricula == null ? null : criteriaBuilder.equal(root.get("aluno").get("matricula"), matricula));
   }


    public static Specification<Ocorrencia> comTurmaId(Long id) {
        return ((root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("aluno").get("id"), id));
    }


}
