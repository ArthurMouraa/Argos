package com.project.argoss.domain.entity;


import com.project.argoss.domain.enums.UsuarioRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "foto")
    private String foto;


    @Enumerated(EnumType.STRING)
    UsuarioRole role;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Aluno> alunos;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Turma> turmas;

    @OneToMany(mappedBy = "usuario")
    private Set<Ocorrencia> ocorrencias;

    public Usuario(String matricula, String email, String nome, String senha, UsuarioRole role) {
        this.matricula = matricula;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }


}