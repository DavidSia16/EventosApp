package daviddev.eventos.repository;

import daviddev.eventos.model.Evento;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findById(long id);
}
