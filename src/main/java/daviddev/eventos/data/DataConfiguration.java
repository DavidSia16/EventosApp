package daviddev.eventos.data;

import daviddev.eventos.model.Usuario;
import daviddev.eventos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            // Verifica se o admin já existe para não duplicar
            if (usuarioRepository.findByLogin("admin") == null) {
                Usuario admin = new Usuario();
                admin.setLogin("admin");
                admin.setNomeCompleto("Administrador");
                admin.setSenha("123456"); // Senha que você usará no login

                usuarioRepository.save(admin);
                System.out.println(">>> Usuário ADMIN criado com sucesso! Login: admin | Senha: 123456");
            }
        };
    }
}
