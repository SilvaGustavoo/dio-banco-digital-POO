## Projeto Java Básico: Desenvolvimento de uma Conta Banco com POO

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
    // Alguns exemplos de padronização da Interface IConta que obrigatoriamente todas contas devem fazer

    public void depositar(double valor);

    public void sacar(double valor) throws ExceptionSaldoInsuficiente;

    public void transferir(double valor, Optional<Conta> conta) throws ExceptionSaldoInsuficiente;

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

Por fim o usuario pode realizar a simulação do seu empréstimo, informando o ``valor`` e o numero de ``parcelas``, assim mostrando as informações do empréstimo e questionado se recusa ou aceita o emprestimo. Após aceito, o emprestimo é creditado na conta do usuario e é solicitado o `dia de vencimento`e gerado as parcelas.

> *Emprestimo solicitado: 12000,00 <br>Emprestimo final: 49587,02 <br>Valor parcelas: 1033,06*

Na classe Emprestimo é realizado o registro dos dados mais importantes e ações obrigatórias, como pagar e pesquisar parcelas ou o registro atualizado do valor devido. Ele possui como atributos `numEmprestimo`, `valorEmprestimo`, `numParcelas`, `valorParcelas`, `diaVencimento` e `listaParcelas`. 

Porém para realizações de funções auxiliares, como geração e busca dos emprestimos, é utilizado a classe `GerenciadorEmprestimo` o qual recebe como atributo uma ***lista de Emprestimos***. Essa classe foi criada intuito de criar um código separado e simples de ler, colocando funções que não serão diretamente utilizadas pelo usuario. Sendo assim, as principais funções criadas para essa classe são:

``` java

// reorna um emprestimo utilizando ou não um parametro para a busca do mesmo
public Emprestimo acessarEmprestimo(int numeroEmprestimo) throws ExceptionEmprestimoInexistente { ... }

// Gera as parcelas de acordo com o dia de vencimento, com cada uma programada para vencimento no mes seguinte.
protected void gerarEmprestimo(Emprestimo emprestimo) { ... }

```

> *Parcela 5 : 309,55 <br>Data de Vencimento: Mon Jul 14 17:20:36 BRT 2025 <br>Data de Pagamento: Thu Jan 30 17:20:38 BRT 2025 <br>Situação: Pagp*
 
### *Exceptions em uso*

Para esse projeto foram criadas duas Exceptions personalizadas sendo elas `ExceptionSaldoInsuficiente` para caso o usuario tente movimentar um valor insuficiente em sua conta. E a `ExceptionEmprestimoInexistente` para quando for realizado quaisquer tipos de ações de emprestimos, sem que exista algum. Além disso foi realizado também o tratamento da Exceção `ParseException` para entradas de datas.

```java
// Exceções personalizadas 

public class ExceptionEmprestimoInexistente extends Exception{ ... }

public class ExceptionSaldoInsuficiente extends Exception{ ...}

```
### *Bibliotecas Utilizadas* 

Concluindo, foram utilizadas as demais bibliotecas ao longo do coódigo:
* **Scanner** para leitura de dados, 
* **List e Set** para armazenamento de dados, 
* **Calendar, LocalDate e Date** para manipulação de datas
* **Comparator** para ordenação das parcelas
* **Optional** para manipular dados que podem crashar o código
* **Stream API** para encurtar códigos e ordenar listas

```java

// Leitura de dados
Scanner leitor = new Scanner(System.in);


// Manipular e Armazenar dados
private Set<Emprestimo> emprestimos; // Set - Armazenar dados não repetitivos 
protected List<String> extrato; // List - Auxiliar na manipulação

// Manipulação de Datas
Calendar calendar = Calendar.getInstance(); // Datas personalizadas
LocalDate.now().getYear(); // Ano, mes e dia atuais
Date dt = new Date(); // Manipulação e comparação de datas


// Usado na Ordenação de parcelas e emprestimos
class ParcelaOrdenarPorNumero implements Comparator<Parcela> { ... } 

Comparator.comparing(Emprestimo::getNumEmprestimo);

// Utilizado para evitar a ocorrência de erros
public void transferir(double valor, Optional<Conta> conta) ... // evitar transferencia em contas inexistentes

// Stream
emprestimosList.stream().sorted({Comparator})

```





