package com.company;

import java.util.Random;

public class Hangman {

    public static void main(String[] args){

        //Schreibe ein Programm mit welchem man Wörter erraten kann.
        // Für dies erstellst du ein Array das du mit Wörtern befüllst.
        // Zufällig wird je Spiel ein Wort daraus ausgewählt.

        String[] words = {"Hampelmann","Elektroschrott","Arbeiterunfallversicherungsgesetz","Parteibuch","Nulllohnrunde","Herbst","Sonnenblume","Teppichklopfer","Sandmännchen","Zutrittskontrollsystemfehlermeldung"};
        Random random = new Random();
        int word = random.nextInt(words.length);

        System.out.println(words[word]);


    }


}
