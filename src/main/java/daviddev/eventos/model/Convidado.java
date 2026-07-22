package daviddev.eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "eventos_app_convidados")
public class Convidado {

    @Id
    @Column(name = "rg")
    @NotBlank(message = "O RG é obrigatório")
    private String rg;

    @NotBlank(message = "O Nome do convidado é obrigatório")
    @Column(name = "nomeConvidado")
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getNomeConvidado() {
        return nomeConvidado;
    }
    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }

    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
