package daviddev.eventos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "eventos_app") // Garante o nome exato da tabela no MySQL
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "local")
    private String local;

    @Column(name = "data")
    private String data;

    @Column(name = "horario")
    private String horario;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL) // 👈 O 'mappedBy' avisa ao Hibernate para NÃO criar colunas extras!
    private List<Convidado> convidados;

    // Construtor vazio obrigatório para o JPA
    public Evento() {
    }

    // Seu construtor com argumentos
    public Evento(String nome, String local, String data, String horario) {
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.horario = horario;
    }

    // Getters e Setters para TODOS os campos (incluindo o ID)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }


}
