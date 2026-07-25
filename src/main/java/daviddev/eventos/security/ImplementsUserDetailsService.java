package daviddev.eventos.security;

import daviddev.eventos.model.Usuario;
import daviddev.eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario =  usuarioRepository.findByLogin(login);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado !");
        }
        return usuario;
    }
}
