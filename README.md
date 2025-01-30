## Projeto Java Básico: Desenvolvimento de uma Conta Banco

O projeto tinha como objetivo o desenvolvimento de um aplicação de banco, com objetivo final de criar uma nova funcionalidade nesse sistema, que no caso foi a possibilidade de realizar empréstimos. Com isso foram criadas e separadas as seguintes classes e pacotes:


 * ***Package: Com***
    * Cliente
    * ContaCorrente
    * Emprestimo
    * Parcela

* ***Package: Util***
    * Package de Excepetions
    * Conta
    * GerenciadorEmprestimo
    * *IConta* - Interface
    * *IEmprestimo* - Interface

> Foram separadas em 2 tipos de pacotes principais sendo eles **util** e **com**, onde o *com* armazena as classes usadas como objetos e o *util* para utilitarios e programas auxiliares para o desempenho do projeto.

Nesse projeto foram utilizadas duas interfaces que serveriam para auxiliar na padronização das classes, sendo elas: 

 * **`IConta`** Serve para a criação de quaisquer tipos de contas, ex: conta corrente, poupança, etc.

 * **`IEmprestimo`** Usado para a padronização das funções do empréstimo

 ``` Java
    // Alguns exemplos de padronização da Interface IConta

public interface IConta {

    public void depositar(double valor);

    public void sacar(double valor) throws ExceptionSaldoInsuficiente;

    public void transferir(double valor, Optional<Conta> conta) throws ExceptionSaldoInsuficiente;

    [...]
}
 ```

As funcionalidades desse projeto envolvem:
* Criação de Cliente
* Extrato, Depósito, Saques e Transferências entre contas
* Simular e solicitar um Empréstimo
* Gerar parcelas personaliadas do empréstimo
* Realizar consultas no empréstimo
* Efetuar o pagamento com registro de data

No inicio do sistema é solicitado a realização do seu registro criando um objeto `Cliente` onde recede os atributos `nome`, `CPF` e `data de nascimento` para que assim prossiga para a criação de sua conta.

Assim  então é criado dois tipos diferentes de contas com os seus dados com as variaveis `contaCorrente` e `contaPoupanca`, nas quais haverão interações, como depósito, saque, e transferências entre ambas. Com todas as transações sendo registradas na lista `extrato` retornando todas as movimentações.

> ***Impriminto Extrato...*** <br> *[Data e Horario] Transação : valor*


 



