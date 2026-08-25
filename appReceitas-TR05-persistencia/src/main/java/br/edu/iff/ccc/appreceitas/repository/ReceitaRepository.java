package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Receita;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
