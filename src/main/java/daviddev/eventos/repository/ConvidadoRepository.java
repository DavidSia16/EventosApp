package daviddev.eventos.repository;

import daviddev.eventos.model.Convidado;
import daviddev.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadoRepository extends JpaRepository<Convidado,String> {

    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByRg(String rg);
}
