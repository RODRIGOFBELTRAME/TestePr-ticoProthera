import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Main {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static void inserirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.addAll(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    static void removerFuncionario(List<Funcionario> funcionarios) {
        funcionarios.removeIf(f -> f.nome.equals("João"));
    }

    static void imprimirInformacoes(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.nome);
            System.out.println("Data de Nascimento: " + f.dataNascimento.format(formatter));
            System.out.println("Salário: " + String.format("%,.2f", f.getSalario()));
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        }
    }

    static void darAumento(List<Funcionario> funcionarios) {
        System.out.println("---AUMENTOS---");
        BigDecimal aumento = new BigDecimal("1.10");  // 10% de aumento
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(aumento));
            System.out.println("Nome: " + f.nome);
            System.out.println("Ganhou aumento ") ;
            System.out.println("Novo Salário: " + String.format("%,.2f", f.getSalario()));
            System.out.println() ;


        }
    }

    static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            if (!funcionariosPorFuncao.containsKey(f.getFuncao())) {
                funcionariosPorFuncao.put(f.getFuncao(), new ArrayList<>());
            }
            funcionariosPorFuncao.get(f.getFuncao()).add(f);
        }
        return funcionariosPorFuncao;
    }

    static void imprimirPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("---IMPRIMIR POR FUNÇÃO---");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("Nome: " + f.nome);
                System.out.println("Data de Nascimento: " + f.dataNascimento.format(formatter));
                System.out.println("Salário: " + String.format("%,.2f", f.getSalario()));
                System.out.println();
            }
        }
    }

    static void imprimirAniversariantes(List<Funcionario> funcionarios, int... meses) {
        System.out.println("---ANIVERSARIANTES DO MÊS 10 e 12---");
        List<Integer> mesesList = Arrays.stream(meses).boxed().toList();
        for (Funcionario f : funcionarios) {
            if (mesesList.contains(f.dataNascimento.getMonthValue())) {
                System.out.println();
                System.out.println("Nome: " + f.nome);
                System.out.println("Data de Nascimento: " + f.dataNascimento.format(formatter));
                System.out.println("Salário: " + String.format("%,.2f", f.getSalario()));
                System.out.println("Função: " + f.getFuncao());
                System.out.println();
            }
        }
    }

    static void imprimirMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = funcionarios.getFirst();
        for (Funcionario f : funcionarios) {
            if (f.dataNascimento.isBefore(maisVelho.dataNascimento)) {
                maisVelho = f;
            }
        }
        System.out.println("Funcionário mais velho: " + maisVelho.nome);
        System.out.println("Idade: " + (LocalDate.now().getYear() - maisVelho.dataNascimento.getYear()));
        System.out.println();
    }

    static void imprimirEmOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("---ORDEM ALFABÉTICA---");

        funcionarios.sort(Comparator.comparing(f -> f.nome));
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.nome);
            System.out.println("Data de Nascimento: " + f.dataNascimento.format(formatter));
            System.out.println("Salário: " + String.format("%,.2f", f.getSalario()));
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        }
    }

    static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.getSalario());
        }
        System.out.println("Total de salários: " + String.format("%,.2f", totalSalarios));
    }

    static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2);
            System.out.println("Nome: " + f.nome);
            System.out.println("Salários mínimos: " + String.format("%,.2f", salariosMinimos));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        inserirFuncionarios(funcionarios);
        removerFuncionario(funcionarios);
        imprimirInformacoes(funcionarios);
        darAumento(funcionarios);
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);
        imprimirPorFuncao(funcionariosPorFuncao);
        imprimirAniversariantes(funcionarios, 10, 12);
        imprimirMaisVelho(funcionarios);
        imprimirEmOrdemAlfabetica(funcionarios);
        imprimirSalariosMinimos(funcionarios);
        imprimirTotalSalarios(funcionarios);

    }
}







