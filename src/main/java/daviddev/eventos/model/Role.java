package daviddev.eventos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private String nomeRole;

    @ManyToMany
    private List<Usuario> usuarios;

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }


}
