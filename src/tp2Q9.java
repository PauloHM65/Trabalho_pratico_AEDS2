import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

public class tp2Q9 {
    static int comparacoes = 0;
    static int movimentacoes = 0;

    private static int compararDatas(Data d1, Data d2) {
        if (d1.getAno() != d2.getAno()) return Integer.compare(d1.getAno(), d2.getAno());
        if (d1.getMes() != d2.getMes()) return Integer.compare(d1.getMes(), d2.getMes());
        return Integer.compare(d1.getDia(), d2.getDia());
    }

    private static int comparar(Restaurante r1, Restaurante r2) {
        comparacoes++;
        int cmp = compararDatas(r1.getDataAbertura(), r2.getDataAbertura());
        if (cmp == 0) {
            comparacoes++;
            return r1.getNome().compareTo(r2.getNome());
        }
        return cmp;
    }

    private static void swap(Restaurante[] arr, int i, int j) {
        Restaurante temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        movimentacoes += 3;
    }

    private static void construir(Restaurante[] arr, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && comparar(arr[esq], arr[maior]) > 0) maior = esq;
        if (dir < n && comparar(arr[dir], arr[maior]) > 0) maior = dir;

        if (maior != i) {
            swap(arr, i, maior);
            construir(arr, n, maior);
        }
    }

    public static void heapSort(Restaurante[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) construir(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            construir(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        ColecaoRestaurantes colecao = ColecaoRestaurantes.lerCsv();
        Scanner sc = new Scanner(System.in);
        Restaurante[] subColecao = new Restaurante[1000];
        int n = 0;
        
        while (sc.hasNextLine()) {
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) continue;
            if (linha.equals("-1")) break;
            int id = Integer.parseInt(linha);
            for (int i = 0; i < colecao.getTamanho(); i++) {
                if (colecao.getRestaurantes()[i].getId() == id) {
                    subColecao[n++] = colecao.getRestaurantes()[i];
                    break;
                }
            }
        }
        
        long inicioTempo = System.currentTimeMillis();
        heapSort(subColecao, n);
        long fimTempo = System.currentTimeMillis();
        
        for (int i = 0; i < n; i++) System.out.println(subColecao[i].formatar());
        
        try (FileWriter log = new FileWriter("matricula_heapsort.txt")) {
            log.write("1234567\t" + comparacoes + "\t" + movimentacoes + "\t" + (fimTempo - inicioTempo) + "\n");
        } catch (IOException e) { e.printStackTrace(); }
        sc.close();
    }
    
}
