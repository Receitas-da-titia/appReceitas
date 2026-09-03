package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Receita;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    Optional<Receita> findByNomeIgnoreCaseAndIdReceitaNot(String nome, Long idReceita);
}
