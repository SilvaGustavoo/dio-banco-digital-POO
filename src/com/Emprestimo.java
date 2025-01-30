package com;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import util.IEmpretimo;

public class Emprestimo implements IEmpretimo{

    private static int CONTADOR_EMPRESTIMO = 0;

    protected int numEmprestimo;
    protected double valorEmprestimo;
    protected double numParcelas;
    protected double valorParcela;
    protected double totalDevido;
    protected int diaVencimento;
    protected Set<Parcela> listParcelas;
    protected Date inicioEmprestimo;
    
    
    public Emprestimo(double valorEmprestimo, int numParcelas, double valorParcela, int diaVencimento) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, diaVencimento);

        this.valorEmprestimo = valorEmprestimo;
        this.numParcelas = numParcelas;
        this.valorParcela = valorParcela;
        this.totalDevido = numParcelas * valorParcela;
        this.inicioEmprestimo = new Date();
        this.listParcelas = new HashSet<>();
        this.diaVencimento = diaVencimento;
        this.numEmprestimo = CONTADOR_EMPRESTIMO++;

    }

    public void pagarParcela(int numParcela) {
       for (Parcela parcela : listParcelas) {
            if(parcela.getNumParcela() == numParcela){
                if(!parcela.getIsPago()) {
                    parcela.pagar();
                    this.totalDevido -= parcela.getValor();
                    break;
                } else {
                    System.out.println("Parcela já está paga.");
                }
            }
       }
       
    }

    public void exibirParcela(int numParcela) {
        boolean foiEncontrada = false;
        for (Parcela parcela : listParcelas) {
            if(parcela.getNumParcela() == numParcela){
                foiEncontrada = true; 
                System.out.println(parcela);
                break;
            }
        }

        if(!foiEncontrada) {
            System.out.println("Parcela não encontrada.");
        }
    }

    public void exibirExtratoEmprestimo() {
        // Exibir todas as parcelas pagas e não pagas do emprestimo
        Set listaOrdenadaNum = new TreeSet<>(new OrdenarPorNumero());
        listaOrdenadaNum.addAll(listParcelas);
        System.out.println("\nListando as Parcelas do empréstimo:");
        System.out.println(listaOrdenadaNum);
    }

    public void exibirParcelasPagas() {
        Set<Parcela> parcelasOrdenadasPorNum = new TreeSet<>(new ParcelaOrdenarPorNumero());
        parcelasOrdenadasPorNum.addAll(listParcelas);

        System.out.println("\n\n Parcelas Pagas \n================================");

        // Exibir todas as parcelas pagas do emprestimo
        for (Parcela parcela : parcelasOrdenadasPorNum) {
            if(parcela.IsPago) {
                System.out.println(parcela);
            }
        }

        System.out.println("\n ================= ACABOU ===================== \n");
    }

    public void exibirParcelasAtrasadas() {
        // Lista ordenada pelo numero da parcela
        Set<Parcela> parcelasOrdenadasPorNum = new TreeSet<>(new ParcelaOrdenarPorNumero()); 
        parcelasOrdenadasPorNum.addAll(listParcelas);


        System.out.println("\n\n Parcelas Atrasadas \n================================");

        // Exibir todas as parcelas atrasadas do emprestimo
        for (Parcela parcela : parcelasOrdenadasPorNum) {
            if(parcela.getDtVencimento().before(new Date())) {
                System.out.println(parcela);
            }
        }

        System.out.println("\n ================= ACABOU ===================== \n");
    }

    public void exibirParcelasRestantes() {
        Set<Parcela> parcelasOrdenadasPorNum = new TreeSet<>(new ParcelaOrdenarPorNumero());
        parcelasOrdenadasPorNum.addAll(listParcelas);

        System.out.println("\n\nParcelas Restantes \n================================");

        // Exibir todas as parcelas não pagas do emprestimo
        for (Parcela parcela : parcelasOrdenadasPorNum) {
            if(!parcela.IsPago) {
                System.out.println(parcela);
            }
        }

        System.out.println("\n ================= ACABOU ===================== \n");
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public double getValorParecela() {
        return valorParcela;
    }


    public double getNumParcelas() {
        return numParcelas;
    }

    public double getTotalDevido() {
        return totalDevido;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public Set<Parcela> getListParcelas() {
        return listParcelas;
    }

    public Date getInicioEmprestimo() {
        return inicioEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }


    public void setNumParcelas(double numParcelas) {
        this.numParcelas = numParcelas;
    }

    public void setTotalDevido(double totalDevido) {
        this.totalDevido = totalDevido;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public void setListParcelas(Set<Parcela> listParcelas) {
        this.listParcelas = listParcelas;
    }

    public void setInicioEmprestimo(Date inicioEmprestimo) {
        this.inicioEmprestimo = inicioEmprestimo;
    }


    public int getNumEmprestimo() {
        return numEmprestimo;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat(".##");

        return "   \nEmpréstimo n° " + numEmprestimo +"\nValor Solicitado: " + valorEmprestimo + ", \nNumero de Parcelas: " + numParcelas + ", \nValor das Parcelas: "
                + df.format(valorParcela) + ", \nTotal Devido: " + df.format(totalDevido) + ", \nDia de Vencimento: " + diaVencimento
                + ", \ninicio do Emprestimo: " + inicioEmprestimo + "\n";
    }

    
    
}


class ParcelaOrdenarPorNumero implements Comparator<Parcela> {

    @Override
    public int compare(Parcela p1, Parcela p2) {
        return Integer.compare(p1.getNumParcela(), p2.getNumParcela());
    }
}