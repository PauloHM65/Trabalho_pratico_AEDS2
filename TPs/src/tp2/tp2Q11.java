package tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

class Data {
    private int ano;
    private int mes;
    private int dia;

    public Data(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public static Data parseData(String s) {
        String[] partes = s.split("-");
        return new Data(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
    }

    public String formatar() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}

class Hora {
    private int hora;
    private int minuto;

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public static Hora parseHora(String s) {
        String[] partes = s.split(":");
        return new Hora(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]));
    }

    public String formatar() {
        return String.format("%02d:%02d", hora, minuto);
    }
}

class Restaurante {
    private int id;
    private String nome;
    private String cidade;
    private int capacidade;
    private double avaliacao;
    private String[] tiposCozinha;
    private int faixaPreco;
    private Hora horarioAbertura;
    private Hora horarioFechamento;
    private Data dataAbertura;
    private boolean aberto;

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public String[] getTiposCozinha() {
        return tiposCozinha;
    }

    public int getFaixaPreco() {
        return faixaPreco;
    }

    public Hora getHorarioAbertura() {
        return horarioAbertura;
    }

    public Hora getHorarioFechamento() {
        return horarioFechamento;
    }

    public Data getDataAbertura() {
        return dataAbertura;
    }

    public boolean isAberto() {
        return aberto;
    }

    // Construtor completo para encapsulamento
    public Restaurante(int id, String nome, String cidade, int capacidade, double avaliacao, String[] tiposCozinha,
            int faixaPreco, Hora horarioAbertura, Hora horarioFechamento, Data dataAbertura, boolean aberto) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.capacidade = capacidade;
        this.avaliacao = avaliacao;
        this.tiposCozinha = tiposCozinha;
        this.faixaPreco = faixaPreco;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.dataAbertura = dataAbertura;
        this.aberto = aberto;
    }

    public static Restaurante parseRestaurante(String s) {
        String[] dados = s.split(",");
        int id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String cidade = dados[2];
        int capacidade = Integer.parseInt(dados[3]);
        double avaliacao = Double.parseDouble(dados[4]);

        String[] tiposCozinha = dados[5].split(";");
        int faixaPreco = dados[6].length(); // Baseado na quantidade de '$'

        String[] horarios = dados[7].split("-");
        Hora horarioAbertura = Hora.parseHora(horarios[0]);
        Hora horarioFechamento = Hora.parseHora(horarios[1]);

        Data dataAbertura = Data.parseData(dados[8]);
        boolean aberto = dados[9].trim().equalsIgnoreCase("true");

        return new Restaurante(id, nome, cidade, capacidade, avaliacao, tiposCozinha, faixaPreco,
                horarioAbertura, horarioFechamento, dataAbertura, aberto);
    }

    public String formatar() {
        StringBuilder precoStr = new StringBuilder();
        for (int i = 0; i < faixaPreco; i++)
            precoStr.append("$");

        String cozinhaStr = "[" + String.join(",", tiposCozinha) + "]";

        return String.format(Locale.US, "[%d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %s-%s ## %s ## %b]",
                id, nome, cidade, capacidade, avaliacao, cozinhaStr, precoStr.toString(),
                horarioAbertura.formatar(), horarioFechamento.formatar(),
                dataAbertura.formatar(), aberto);
    }
}

class ColecaoRestaurantes {
    private int tamanho;
    private Restaurante[] restaurantes;

    public ColecaoRestaurantes(int capacidadeMaxima) {
        this.tamanho = 0;
        this.restaurantes = new Restaurante[capacidadeMaxima];
    }

    public int getTamanho() {
        return tamanho;
    }

    public Restaurante[] getRestaurantes() {
        return restaurantes;
    }

    public void lerCsv(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine(); // Pular cabeçalho
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    restaurantes[tamanho++] = Restaurante.parseRestaurante(linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ColecaoRestaurantes lerCsv() {
        ColecaoRestaurantes colecao = new ColecaoRestaurantes(1000);
        colecao.lerCsv("/tmp/restaurantes.csv");
        return colecao;
    }
}
class Lista {
    private Restaurante[] array;
    private int n;

    public Lista(int tamanho) {
        array = new Restaurante[tamanho];
        n = 0;
    }

    public void inserirInicio(Restaurante restaurante) {
        if (n >= array.length) return;
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = restaurante;
        n++;
    }

    public void inserir(Restaurante restaurante, int posicao) {
        if (n >= array.length || posicao < 0 || posicao > n) return;
        for (int i = n; i > posicao; i--) {
            array[i] = array[i - 1];
        }
        array[posicao] = restaurante;
        n++;
    }

    public void inserirFim(Restaurante restaurante) {
        if (n >= array.length) return;
        array[n] = restaurante;
        n++;
    }

    public Restaurante removerInicio() {
        if (n == 0) return null;
        Restaurante resp = array[0];
        n--;
        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public Restaurante remover(int posicao) {
        if (n == 0 || posicao < 0 || posicao >= n) return null;
        Restaurante resp = array[posicao];
        n--;
        for (int i = posicao; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public Restaurante removerFim() {
        if (n == 0) return null;
        return array[--n];
    }

    public void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.println(array[i].formatar());
        }
    }
}
public class tp2Q11 {
    public static Restaurante buscarPorId(ColecaoRestaurantes colecao, int id) {
        for (int i = 0; i < colecao.getTamanho(); i++) {
            if (colecao.getRestaurantes()[i].getId() == id) {
                return colecao.getRestaurantes()[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ColecaoRestaurantes colecao = ColecaoRestaurantes.lerCsv();
        Scanner sc = new Scanner(System.in);
        Lista lista = new Lista(1000); 
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("-1")) break;
            int id = Integer.parseInt(input);
            Restaurante r = buscarPorId(colecao, id);
            if (r != null) lista.inserirFim(r);
        }

        if (sc.hasNextInt()) {
            int nComandos = sc.nextInt();
            for (int i = 0; i < nComandos; i++) {
                String comando = sc.next();
                if (comando.equals("II")) {
                    int id = sc.nextInt();
                    lista.inserirInicio(buscarPorId(colecao, id));
                } else if (comando.equals("I*")) {
                    int pos = sc.nextInt();
                    int id = sc.nextInt();
                    lista.inserir(buscarPorId(colecao, id), pos);
                } else if (comando.equals("IF")) {
                    int id = sc.nextInt();
                    lista.inserirFim(buscarPorId(colecao, id));
                } else if (comando.equals("RI")) {
                    Restaurante r = lista.removerInicio();
                    if (r != null) System.out.println("(R)" + r.getNome());
                } else if (comando.equals("R*")) {
                    int pos = sc.nextInt();
                    Restaurante r = lista.remover(pos);
                    if (r != null) System.out.println("(R)" + r.getNome());
                } else if (comando.equals("RF")) {
                    Restaurante r = lista.removerFim();
                    if (r != null) System.out.println("(R)" + r.getNome());
                }
            }
        }

        lista.mostrar();
        sc.close();
    }
}