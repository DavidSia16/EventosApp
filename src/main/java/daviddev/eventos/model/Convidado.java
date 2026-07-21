package daviddev.eventos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "eventos_app_convidados")
public class Convidado {

    @Id
    @Column(name = "rg")
    private String rg;
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
