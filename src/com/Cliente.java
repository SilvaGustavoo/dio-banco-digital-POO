package com;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class Cliente {
    
    private String nomeCompleto;
    private String cpf;
    private Date dataNascimento;
    
    public Cliente(String nomeCompleto, String cpf, String dataNascimento) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;

        this.dataNascimento = sdf.parse(dataNascimento);
        
    }
    
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }


    

    
}
