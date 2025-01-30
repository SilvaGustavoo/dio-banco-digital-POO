package util.exceptions;

public class ExceptionEmprestimoInexistente extends Exception{
    
    private static final String MENSAGEM_PADRAO = "Nenhum empréstimo foi encontrado.\n";

    // Construtor que já define a mensagem
    public ExceptionEmprestimoInexistente() {
        super(MENSAGEM_PADRAO);
    }

    // Construtor opcional para permitir mensagens personalizadas
    public ExceptionEmprestimoInexistente(String mensagem) {
        super(mensagem);
    }
}
