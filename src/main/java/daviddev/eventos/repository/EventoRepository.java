package daviddev.eventos.repository;

import daviddev.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, Long> {

    Evento findById(long codigo);

}
