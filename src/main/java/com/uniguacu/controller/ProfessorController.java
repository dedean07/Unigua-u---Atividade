package com.uniguacu.controller;

import com.uniguacu.model.Professor;
import com.uniguacu.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/professor")

@RequiredArgsConstructor

@Tag(name = "Professor", description = "Professor dos cursos da instituição")
public class ProfessorController {
    private final ProfessorService service;

    @GetMapping
    @Operation(summary = "Lista todos os professores")
    public List<Professor> listarporProfessor() {
        return service.listarTodos_Professores();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca o professor por ID")
    public ResponseEntity<Professor> buscarporProfessor (@PathVariable Long id) {
        return service.buscarporIdProfessores(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Adicionar professor")
    public ResponseEntity<Professor> incluir_Professor (@Valid @RequestBody Professor professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar_dados(professor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do professor")
    public ResponseEntity<Professor> atualizarDados_Professor (@PathVariable Long id, @Valid @RequestBody Professor professor) {
        return service.atualizar_dados (id, professor)
                .map(ResponseEntity::ok)

                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar o professor do banco de dados")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletar_dados(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
