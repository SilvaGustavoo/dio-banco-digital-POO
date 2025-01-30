package util;



public interface IEmpretimo {

    public void pagarParcela(int numParcela);
    
    // exibi a parcela por completo, ou seja todos os seus dados
    public void exibirParcela(int numParcela);
    

    // Exibir todas as parcelas pagas e não pagas do emprestimo
    public void exibirExtratoEmprestimo();
        

    // Exibir todas as parcelas pagas do emprestimo
    public void exibirParcelasPagas();
        

    // Exibir todas as parcelas atrasadas do emprestimo
    public void exibirParcelasAtrasadas();
        

    // Exibir todas as parcelas não pagas do emprestimo
    public void exibirParcelasRestantes();
        

}
