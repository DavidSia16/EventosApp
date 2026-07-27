package daviddev.eventos.repository;

import daviddev.eventos.model.Convidado;
import daviddev.eventos.model.Evento;
import daviddev.eventos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConvidadoRepository extends JpaRepository<Convidado,String> {

    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByRg(String rg);
    Optional<Convidado> findByNomeConvidadoIgnoreCase(String nomeConvidado);
}
