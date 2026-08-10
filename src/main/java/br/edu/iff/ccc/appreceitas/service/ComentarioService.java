package br.edu.iff.ccc.appreceitas.service;

import br.edu.iff.ccc.appreceitas.dto.ComentarioDTO;
import br.edu.iff.ccc.appreceitas.model.Comentario;
import br.edu.iff.ccc.appreceitas.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario adicionar(Long idUsuario, Long idReceita, ComentarioDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setIdUsuario(idUsuario);
        comentario.setIdReceita(idReceita);
        comentario.setNota(dto.getNota());
        comentario.setComentario(dto.getComentario());
        comentario.setDataAvaliacao(LocalDateTime.now());
        return comentarioRepository.salvar(comentario);
    }

    public void excluir(Long id) {
        comentarioRepository.excluir(id);
    }

    public List<Comentario> listarPorReceita(Long idReceita) {
        return comentarioRepository.listarTodos().stream()
                .filter(c -> c.getIdReceita().equals(idReceita))
                .collect(Collectors.toList());
    }
}
