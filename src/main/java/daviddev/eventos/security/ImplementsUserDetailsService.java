package daviddev.eventos.security;

import daviddev.eventos.model.Convidado;
import daviddev.eventos.model.Usuario;
import daviddev.eventos.repository.ConvidadoRepository;
import daviddev.eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @Override
    public UserDetails loadUserByUsername(String loginOuNome) throws UsernameNotFoundException {
        System.out.println(">>> 1. Buscando no banco por: " + loginOuNome);

        Usuario usuario = usuarioRepository.findByLogin(loginOuNome);

        if (usuario != null) {
            return new User(
                    usuario.getUsername(),
                    usuario.getPassword(),
                    true, true, true, true,
                    usuario.getAuthorities()
            );
        }


        Optional<Convidado> convidadoOpt = convidadoRepository.findByNomeConvidadoIgnoreCase(loginOuNome);
        // DICA: Se criou o método ignoreCase:
        // Optional<Convidado> convidadoOpt = convidadoRepository.findByNomeConvidadoIgnoreCase(loginOuNome);

        if (convidadoOpt.isPresent()) {
            Convidado convidado = convidadoOpt.get();

            return new User(
                    convidado.getNomeConvidado(),
                    convidado.getRg(),
                    true, true, true, true,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }

        // 3. Se não encontrou em nenhum dos dois
        throw new UsernameNotFoundException("Usuário ou Convidado não encontrado: " + loginOuNome);
    }
}