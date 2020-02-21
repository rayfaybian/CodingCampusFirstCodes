package com.company;

public class Main {

    public static void main(String[] args) {

        // Verschlüssle diese Nachricht: "DASISTEINEGEHEIMENACHRICHT"
        // Nutze die Cäsar Verschlüsselung zb. A -> B/ X -> Y


        String message = "DASISTEINEGEHEIMENACHRICHT";

        char[] messageEncryption = message.toCharArray();
        int length = message.length();
        int counter = 0;

        while (counter < length){

        System.out.print(messageEncryption[counter] += 1);
        counter++;}

    }
}
