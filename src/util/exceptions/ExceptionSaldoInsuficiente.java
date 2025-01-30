package util.exceptions;

public class ExceptionSaldoInsuficiente extends Exception {
    
    private static final String MENSAGEM_PADRAO = "O saldo não é suficiente para essa operação";

    // Construtor que já define a mensagem
    public ExceptionSaldoInsuficiente() {
        super(MENSAGEM_PADRAO);
    }

    // Construtor opcional para permitir mensagens personalizadas
    public ExceptionSaldoInsuficiente(String mensagem) {
        super(mensagem);
    }
}
