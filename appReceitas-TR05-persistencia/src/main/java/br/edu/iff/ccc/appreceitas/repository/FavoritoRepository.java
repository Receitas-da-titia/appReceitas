package br.edu.iff.ccc.appreceitas.repository;

import br.edu.iff.ccc.appreceitas.model.Favorito;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByIdUsuario(Long idUsuario);

    Optional<Favorito> findByIdUsuarioAndIdReceita(Long idUsuario, Long idReceita);
}
