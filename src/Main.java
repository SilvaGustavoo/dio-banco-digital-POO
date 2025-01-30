import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.Cliente;
import com.ContaCorrente;
import com.Parcela;

import util.Conta;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner leitor = new Scanner(System.in);

        System.out.println("BEM VINDO AO BANCO DIGITAL DIO");
        System.out.println("==================================");
        System.out.println("Primeiramente vamos criar o seu cadastro");
        System.out.print("Digite o seu nome: ");
        String nome = leitor.nextLine();
        System.out.print("Digite o seu CPF: ");
        String cpf = leitor.nextLine();

        Cliente cliente = null;
        boolean Correct = false;
        do {
            try {
                // Corrigir caso o usuario digite diferente do pedido
                System.out.print("Digite a sua data de nascimento (DD/MM/YYYY): ");
                String dataNascimento = leitor.nextLine();
    
                cliente = new Cliente(nome, cpf, dataNascimento);
                Correct = true;
            } catch (Exception msg) {
                System.out.println("\n Não foi válido, digite de acordo com o solicitado!!");
             }
        } while (!Correct);
        


        //Cliente cliente = new Cliente("Gustavo S", "12345678950", "31/12/2005");

        ContaCorrente contaCorrente = new ContaCorrente(cliente);
        Optional<Conta> contaPoupanca = Optional.of(new Conta(cliente));

        // Deposito na conta corrente
        System.out.print("Vamos Fazer um depósito, digite o valor: ");
        contaCorrente.depositar(leitor.nextInt());

        System.out.println();

        System.out.println("Saldo da Conta Corrente: " + contaCorrente.getSaldo());

        System.out.println("\n");




        // Transferir
        System.out.println("Criamos pra você uma conta poupança !! Vamos tranferir um dinheiro para lá ");
        System.out.print("Digite o valor da transferência: ");
        contaCorrente.transferir(leitor.nextDouble(), contaPoupanca);

        System.out.println("Saldo da Conta Corrente após transferência: " + contaCorrente.getSaldo());


        // Saldo da Conta Poupança
        System.out.println("Saldo da Conta Poupança após a transferência: " + contaPoupanca.get().getSaldo());

        System.out.println("\n");

        contaCorrente.imprimirExtrato();




        // Sacar
        System.out.println("Vamos fazer um saque, digite o valor: ");
        System.out.print("Digite o valor do saque: ");
        contaCorrente.sacar(leitor.nextDouble());

        System.out.println("Saldo da Conta Corrente após saque: " + contaCorrente.getSaldo());

        contaCorrente.getMeusEmprestimos().exibirMeusEmprestimos();

        System.out.println("\n");


        

        // Gerando o Empréstimo
        System.out.println("Vamos gerar um empréstimo");
        System.out.print("Digite o valor do empréstimo: ");
        double valorEmprestimo = leitor.nextDouble();
        System.out.print("Digite o número de parcelas: ");
        int numParcelas = leitor.nextInt();
        contaCorrente.simularEmprestimo(valorEmprestimo, numParcelas);
        System.out.println("Saldo da Conta Corrente após empréstimo: " + contaCorrente.getSaldo());

        System.out.println("\n");


        // Mostra o extrato do Empréstimos, e todas as suas Parcelas
        contaCorrente.getMeusEmprestimos().acessarEmprestimo().exibirExtratoEmprestimo();


        // Pagando algumas parcelas geradas
        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).pagarParcela(1);
        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).pagarParcela(3);
        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).pagarParcela(5);

        // mostra as parcelas pagas
        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).exibirParcelasPagas();

        
        // Código para mostrar as parcelas atrasadas, alterando a data de vencimento de algumas parcelas geradas

        // coleta a lista de parcelas do emprestimo gerado
        Set<Parcela> parcelasAleatorias = contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).getListParcelas();

        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth() - 1);

        // Ordenar lista de parcelas atrasadas
        Set<Parcela> parcelas = new TreeSet<>(new ParcelaOrdenarPorNumero());
        parcelas.addAll(parcelasAleatorias);

        int i = 0;
        for (Parcela parcela : parcelas) {
            i++;
            if(i > 20 && i < 25) {
                parcela.setDtVencimento(calendar.getTime());

            }
        }


        // Mostra as Parcelas atrasadas
        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).exibirParcelasAtrasadas();


        // Mostra as parcelas restantes, ou seja, as não pagas
        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).exibirParcelasRestantes();

        contaCorrente.getMeusEmprestimos().acessarEmprestimo(0).exibirParcela(5);


        System.out.println("\n");
        System.out.println("\n");

        contaCorrente.simularEmprestimo(12000, 48);

        contaCorrente.getMeusEmprestimos().acessarEmprestimo().exibirExtratoEmprestimo();
    

        contaCorrente.imprimirExtrato();
        contaPoupanca.get().imprimirExtrato();
        
    }
}


class ParcelaOrdenarPorNumero implements Comparator<Parcela> {

    @Override
    public int compare(Parcela p1, Parcela p2) {
        return Integer.compare(p1.getNumParcela(), p2.getNumParcela());
    }
}