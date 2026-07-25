package daviddev.eventos.repository;

import daviddev.eventos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByLogin(String login);
}
