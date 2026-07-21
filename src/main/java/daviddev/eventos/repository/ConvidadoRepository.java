package daviddev.eventos.repository;

import daviddev.eventos.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadoRepository extends JpaRepository<Convidado,String> {

}
