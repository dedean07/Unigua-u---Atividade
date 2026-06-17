package com.uniguacu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Data
@NoArgsConstructor

@AllArgsConstructor
public class Professor {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do professor é obrigatório")
    @Column(name = "nome_professor", nullable = false)
    private String nomeProfessor;

    @Column(name = "email_professor", nullable = false)
    private String emailProfessor;
}