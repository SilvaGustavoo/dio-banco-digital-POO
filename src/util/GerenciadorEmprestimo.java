package util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import util.exceptions.ExceptionEmprestimoInexistente;

import com.Emprestimo;
import com.Parcela;

public class GerenciadorEmprestimo {

    private Set<Emprestimo> emprestimos;

    public GerenciadorEmprestimo() {
        this.emprestimos = new HashSet<>();
    }

    /**
     * 
     * @param emprestimo é recebido um empréstimo junto de seus paramentros, como Valor, numero de parcelas, etc.
     * @return não retorna nada, porém cria dentro da classe emprestimo sua lista de parcelas.
     */
    protected void gerarEmprestimo(Emprestimo emprestimo) {
        
        Calendar calendar = Calendar.getInstance();
        emprestimos.add(emprestimo);

        // gerar lista de parcelas
        for (int i = 1; i <= emprestimo.getNumParcelas(); i++) {

            // calcular data de vencimento da parcela
            calendar.set(LocalDate.now().getYear(), (LocalDate.now().getMonthValue() + i), emprestimo.getDiaVencimento());

            // adicionar parcela ao emprestimo
            emprestimo.getListParcelas().add(new Parcela(calendar.getTime(), emprestimo.getValorParecela(), i));
        }
        
        
        
    }

    /**
     * @return retorna no console a lista de emprestimos do cliente
     */
    public void exibirMeusEmprestimos() {
        System.out.println("\nListando Empréstimos");
        System.out.println(emprestimos);
    }


    /**
     * @return retorna um emprestimo escolhido pelo usuario e organizado de acordo com a ordem de criação
     * @exception ExceptionEmprestimoInexistente exeção caso não haja nenhum elemento na lista de emprestimos
     */
    public Emprestimo acessarEmprestimo() throws ExceptionEmprestimoInexistente {
        exibirMeusEmprestimos();
        Scanner leitor = new Scanner(System.in);
        int numeroEmprestimo = 0;

        if(emprestimos.isEmpty()) {
            System.out.println("Não foi realizado nenhum emprestimo, simule um agora mesmo !!");
            throw new ExceptionEmprestimoInexistente();
        } else {

            do {

                System.out.print("Digite o número do empréstimo: ");
                numeroEmprestimo = leitor.nextInt();

            } while((numeroEmprestimo < 0 && numeroEmprestimo != 0) || numeroEmprestimo > emprestimos.size() - 1);

        }

        List<Emprestimo> emprestimosList = new ArrayList<>(emprestimos);
        emprestimosList.stream().sorted(Comparator.comparing(Emprestimo::getNumEmprestimo));
        return emprestimosList.get(numeroEmprestimo);

    }

    /**@description uma função que busca e retorna um emprestimo da lista de emprestimos
     * @param numeroEmprestimo o codigo do emprestimo
     * @return retorna um objeto Emprestimo
     */
    public Emprestimo acessarEmprestimo(int numeroEmprestimo) throws ExceptionEmprestimoInexistente {

        if(emprestimos.isEmpty()) {
            System.out.println("Não foi realizado nenhum emprestimo, simule um agora mesmo !!");
            throw new ExceptionEmprestimoInexistente();
        } else
        
        if((numeroEmprestimo < 0 && numeroEmprestimo!= 0) || numeroEmprestimo > emprestimos.size() - 1) {
            System.out.println("Número de empréstimo inválido");
            acessarEmprestimo();
        }


        List<Emprestimo> emprestimosList = new ArrayList<>(emprestimos);
        emprestimosList.stream().sorted(Comparator.comparing(Emprestimo::getNumEmprestimo)).toList();

        return emprestimosList.get(numeroEmprestimo);
    }
    

    public Set<Emprestimo> getEmprestimos() {
        return emprestimos;
    }


    public void setEmprestimos(Set<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }



    
}


