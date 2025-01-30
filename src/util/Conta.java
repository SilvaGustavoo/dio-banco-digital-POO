package util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.Cliente;
import com.Emprestimo;

import util.exceptions.ExceptionSaldoInsuficiente;

public class Conta implements IConta{

    private static final int AGENCIA_PADRAO = 4742;
    private static int CONTADOR_CONTAS = 1;
    Double taxaMensal = 0.03;

    // Data das transações na lista Extrato
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected Cliente cliente;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected List<String> extrato;
    protected GerenciadorEmprestimo meusEmprestimos;
    

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = CONTADOR_CONTAS++;
        this.cliente = cliente;
        this.meusEmprestimos = new GerenciadorEmprestimo();
        this.extrato = new ArrayList<>();
    }

    public void depositar(double valor) {
        this.saldo += valor;
        extrato.add(sdf.format(new Date()) + "  Deposito: + R$ " + valor);
    }

    public void sacar(double valor) throws ExceptionSaldoInsuficiente {
        try {
            if(this.saldo < valor) {
                throw new ExceptionSaldoInsuficiente();
            } else {
                this.saldo -= valor;
                extrato.add(sdf.format(new Date()) + "  Saque: - R$ " + valor);
            }
        } catch (ExceptionSaldoInsuficiente e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void transferir(double valor, Optional<Conta> conta) throws ExceptionSaldoInsuficiente {
        try {
            if(this.saldo < valor && !conta.isPresent()) {
                throw new ExceptionSaldoInsuficiente();
            } else {
                sacar(valor);
                // Pega o ultimo elemento da lista Extrato e o altera 
                extrato.set(extrato.size() - 1, (sdf.format(new Date()) + "  Transferência para ' " + conta.get().cliente.getNomeCompleto() + "' : - R$ " + valor));

                conta.get().depositar(valor);
                conta.get().extrato.set(conta.get().extrato.size() - 1, (sdf.format(new Date()) + "  Transferência recebida de '" + cliente.getNomeCompleto() + "' : + R$ " + valor));
            }
        } catch (ExceptionSaldoInsuficiente e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * @description cria uma simulação de emprestimo demonstrando o valor final de acordo com os paramentros informados, por fim pergunta ao usuario se deseja ou não solicitar o emprestimo
     * @param valor valor do emprestimo
     * @param numParcelas numero de parcelas
     * 
     * @return não possui retorno
     * 
     */
    public void simularEmprestimo(double valor, int numParcelas) {
        
        Double totalEmprestimo = valor * Math.pow((1 + taxaMensal),numParcelas);
        double valorParcela = totalEmprestimo / numParcelas;

        Scanner leitor = new Scanner(System.in);

        System.out.printf("Emprestimo solicitado: %.2f", valor);
        System.out.println();

        System.out.printf("Emprestimo final: %.2f", totalEmprestimo);
        System.out.println();

        System.out.printf("Valor parcelas: %.2f", valorParcela);
        System.out.println();
        
        System.out.print("Deseja Solicitar esse Emprestimo? [S/N]");
        String solicitou = leitor.next();
        solicitou.toLowerCase();

        if(solicitou.equals("s") || solicitou.equals("sim)") ) {

            System.out.print("Digite o dia de vencimento da primeira parcela: ");
            int diaVencimento = leitor.nextInt();

            // gerar emprestimo
            meusEmprestimos.gerarEmprestimo(new Emprestimo(valor ,numParcelas, valorParcela, diaVencimento));

            depositar(valor);
            extrato.set(extrato.size() - 1, (sdf.format(new Date()) + "  Empréstimo: + R$ " + valor));

            Date dt = new Date();
            
            
        } else {
            System.out.println("Empréstimo cancelado.");
        }

    }


    public void imprimirExtrato() {
        System.out.println("Imprimindo Extrato...");
        for (String string : extrato) {
            System.out.println(string);
        }
        System.out.println("Saldo atual: " + saldo + "\n");
    }


    public List<String> getExtrato() {
        return extrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public GerenciadorEmprestimo getMeusEmprestimos() {
        return meusEmprestimos;
    }

    
    
}
