package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Verschlüssle eine Nachricht.
        // Nutze die Cäsar Verschlüsselung zb. A -> B/ X -> Y

        Scanner userInput = new Scanner(System.in);

        System.out.println("Geben Sie Ihre zu verschlüsselnde Nachricht ein.");
        String message = userInput.nextLine();


        String encryptedMessage = "";
        String decryptedMessage = "";
        String originalMessage = message;



        System.out.println("\n");

        System.out.print("Ihre Nachricht: ");
        System.out.println(message+"\n");

        message = adaptMessage(message); //Umlaute werden ersetzt (ä = ae etc.)
        int lengthMessage = message.length();
        char[] messageEncryption = message.toCharArray();//Der String message wird in ein Character Array gespeichert

        System.out.print("Ihre Nachricht ohne Umlaute: ");
        System.out.println(message+"\n");


        for (int i = 0; i < lengthMessage; i++) {
            char encryptedChar;
            if (((messageEncryption[i] == ('!')) || (messageEncryption[i] == ('?')) ||
                    (messageEncryption[i] == ('.')) || (messageEncryption[i] == (',')) ||
                    (messageEncryption[i] == (';')) || (messageEncryption[i] == (':')) || (messageEncryption[i] == (' ')))) {
                encryptedChar = messageEncryption[i];
                encryptedMessage = encryptedMessage + Character.toString(encryptedChar);
            }
            else {
                encryptedChar = messageEncryption[i] += 1;
                encryptedMessage = encryptedMessage + Character.toString(encryptedChar);
            }
        }

        System.out.print("Ihre Nachricht verschlüsselt: ");
        System.out.print(encryptedMessage+"\n");

        char[] messageDecryption = encryptedMessage.toCharArray(); //Die verschlüsselte Nachricht wird in ein neues Array gespeichert




        int lengthEncryptedMessage = encryptedMessage.length();

        for (int j = 0; j < lengthEncryptedMessage; j++) {
            char decryptedChar;
            if (((messageDecryption[j] == ('!')) || (messageDecryption[j] == ('?')) ||
                    (messageDecryption[j] == ('.')) || (messageDecryption[j] == (',')) ||
                    (messageDecryption[j] == (';')) || (messageDecryption[j] == (':')) || (messageDecryption[j] == (' ')))) {
                decryptedChar = messageDecryption[j];
                decryptedMessage = decryptedMessage + Character.toString(decryptedChar);

            } else {
                decryptedChar = messageDecryption[j] -= 1;
                decryptedMessage = decryptedMessage + Character.toString(decryptedChar);
            }
        }
        System.out.print("Ihre Nachricht Entschlüsselt: ");
        System.out.println(decryptedMessage+"\n");

        System.out.print("Ihre originale Nachricht: ");
        System.out.print(originalMessage+"\n");}

    private static String adaptMessage(String message) {
        message = message.replace("ä", "ae");
        message = message.replace("Ä", "Ae");
        message = message.replace("ü", "ue");
        message = message.replace("Ü", "Ue");
        message = message.replace("ö", "oe");
        message = message.replace("Ö", "Oe");
        return message;
    }
}






