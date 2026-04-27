import java.time.LocalDateTime;
public class Evento {

    String nome;
    String endereco;
    String categoria;
    String descricao;
    LocalDateTime horario;

    public Evento(String nome, String endereco, String categoria, String descricao, LocalDateTime horario) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.horario = horario;
    }

    public String toString() {
        return "Evento: " + nome +
                " | " + categoria +
                " | " + endereco +
                " | " + descricao +
                " | " + horario;
    }
}