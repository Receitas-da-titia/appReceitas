package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.model.Favorito;
import br.edu.iff.ccc.appreceitas.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    public Favorito adicionar(Long idUsuario, Long idReceita) {
        boolean jaFavoritado = favoritoRepository.listarTodos().stream()
                .anyMatch(f -> f.getIdUsuario().equals(idUsuario) && f.getIdReceita().equals(idReceita));
        if (jaFavoritado) {
            return favoritoRepository.listarTodos().stream()
                    .filter(f -> f.getIdUsuario().equals(idUsuario) && f.getIdReceita().equals(idReceita))
                    .findFirst()
                    .get();
        }
        Favorito favorito = new Favorito();
        favorito.setIdUsuario(idUsuario);
        favorito.setIdReceita(idReceita);
        favorito.setDataFavorito(LocalDateTime.now());
        return favoritoRepository.salvar(favorito);
    }

    public void remover(Long idFavorito) {
        favoritoRepository.excluir(idFavorito);
    }

    public List<Favorito> listarPorUsuario(Long idUsuario) {
        return favoritoRepository.listarTodos().stream()
                .filter(f -> f.getIdUsuario().equals(idUsuario))
                .collect(Collectors.toList());
    }
}
