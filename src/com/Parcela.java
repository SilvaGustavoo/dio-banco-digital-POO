package com;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

public class Parcela {
    
    Date dtVencimento;
    Optional<Date> dtPagamento;
    Double valor;
    int numParcela;
    boolean IsPago;

    public Parcela(Date dtVencimento, Double valor, int numParcela) {
        this.dtVencimento = dtVencimento;
        this.valor = valor;
        this.numParcela = numParcela;
        this.IsPago = false;
        this.dtPagamento = Optional.empty();
    }

    

    public Date getDtVencimento() {
        return dtVencimento;
    }
    public Date getDtPagamento() {
        return dtPagamento.orElse(null);
    }

    public Double getValor() {
        return valor;
    }

    public int getNumParcela() {
        return numParcela;
    }

    public boolean getIsPago() {
        return IsPago;
    }

    

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }



    public void setValor(Double valor) {
        this.valor = valor;
    }



    public void setIsPago(boolean isPago) {
        IsPago = isPago;
    }


    


    public void pagar() {
        this.dtPagamento = Optional.of(new Date());
        this.IsPago = true;
    }



    @Override
    public String toString() {
        return "\n\nParcela " + numParcela + " : " + new DecimalFormat(".##").format(valor)
        + "\nData de Vencimento: "+ dtVencimento + "\nData de Pagamento: " + (dtPagamento.isPresent() ? dtPagamento.get() : false) + "\nSituação: " + (IsPago ? "Pagp" : "Não Pago");
    }



}



class OrdenarPorNumero implements Comparator<Parcela> {

    @Override
    public int compare(Parcela p1, Parcela p2) {
        return Integer.compare(p1.getNumParcela(), p2.getNumParcela());
    }


}
