package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Categoria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
