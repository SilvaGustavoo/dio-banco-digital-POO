package util;


import java.util.Optional;

import util.exceptions.ExceptionSaldoInsuficiente;


public interface IConta {

    public void depositar(double valor);

    public void sacar(double valor) throws ExceptionSaldoInsuficiente;

    public void transferir(double valor, Optional<Conta> conta) throws ExceptionSaldoInsuficiente;

    public void simularEmprestimo(double valor, int numParcelas);

    public void imprimirExtrato();
}