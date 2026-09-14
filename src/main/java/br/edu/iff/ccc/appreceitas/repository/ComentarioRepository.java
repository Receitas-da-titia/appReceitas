package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Comentario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByIdReceita(Long idReceita);
}