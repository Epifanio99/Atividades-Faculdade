import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.io.*;

public class Main {

    static List<Evento> eventos = new ArrayList<>();

    public static void main(String[] args) {
        carregarEventos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1 - Criar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                criarEvento(scanner);
            } else if (opcao == 2) {
                listarEventos();
            } else {
                break;
            }
        }
    }
    static void criarEvento(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Data e hora (yyyy-MM-dd HH:mm): ");
        String dataTexto = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime horario = LocalDateTime.parse(dataTexto, formatter);
        Evento evento = new Evento(nome, endereco, categoria, descricao, horario);
        eventos.add(evento);

        System.out.println("Evento criado!");
        salvarEventos();
    }
    static void salvarEventos() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("events.data"));

            for (Evento e : eventos) {
                writer.write(
                        e.nome + ";" +
                                e.endereco + ";" +
                                e.categoria + ";" +
                                e.descricao + ";" +
                                e.horario
                );
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }
    static void carregarEventos() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("events.data"));
            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] partes = linha.split(";");

                String nome = partes[0];
                String endereco = partes[1];
                String categoria = partes[2];
                String descricao = partes[3];
                LocalDateTime horario = LocalDateTime.parse(partes[4]);

                Evento evento = new Evento(nome, endereco, categoria, descricao, horario);
                eventos.add(evento);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Arquivo ainda não existe.");
        }
    }
    static void listarEventos() {

        eventos.sort(Comparator.comparing(e -> e.horario));

        LocalDateTime agora = LocalDateTime.now();

        for (Evento e : eventos) {

            String status;

            if (e.horario.isBefore(agora)) {
                status = "🔴 Já passou";
            } else if (e.horario.isAfter(agora)) {
                status = "🔵 Futuro";
            } else {
                status = "🟢 Acontecendo";
            }

            System.out.println(e + " | " + status);
        }
    }
}