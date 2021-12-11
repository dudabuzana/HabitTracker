package com.example.habittracker.utils;

public class Utils {

    public String formataData(String inDate){
        String retorno = inDate.substring(0, 10);
        //2000-01-25
        String ano = retorno.substring(0, 4);
        String mes = retorno.substring(5, 7);
        String dia = retorno.substring(8,10);
        return dia + "/" + mes + "/" + ano;
    }
}
