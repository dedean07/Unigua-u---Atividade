package com.uniguacu.service;

import com.uniguacu.model.Professor;
import com.uniguacu.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    public List<Professor> listarTodos_Professores() {
        return repository.findAll();
    }

    public Optional<Professor> buscarporIdProfessores (Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Professor salvar_dados (Professor professor) {
        return repository.save(professor);
    }

    @Transactional
    public Optional <Professor> atualizar_dados (Long id, Professor detalhes_professor) {
        return repository.findById(id)
                .map(professor -> {
                    professor.setNomeProfessor(detalhes_professor.getNomeProfessor());
                    professor.setEmailProfessor(detalhes_professor.getEmailProfessor());

                    return repository.save(professor);
                });
    }

    @Transactional
    public boolean deletar_dados (Long id) {
        return repository.findById(id)
                .map(professor -> {
                    repository.delete(professor);
                    return true;
                })
                .orElse(false);
    }
}
