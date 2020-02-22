package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Verschlüssle diese Nachricht: "DASISTEINEGEHEIMENACHRICHT"
        // Nutze die Cäsar Verschlüsselung zb. A -> B/ X -> Y

        Scanner userInput = new Scanner(System.in);

        System.out.println("Geben Sie Ihre zu verschlüsselnde Nachricht ein.");
        String message = userInput.nextLine();

        char[] messageEncryption = message.toCharArray();//Der String message wird in ein Character Array gespeichert
        int lengthMessage = message.length();
        String encryptedMessage = "";
        String decryptedMessage = "";


        System.out.println("\n");

        System.out.print("Verschlüsselt: ");

        for (int i = 0; i < lengthMessage; i++) {
            char encryptedChar;
            if (((messageEncryption[i] == ('!')) || (messageEncryption[i] == ('?')) ||
                    (messageEncryption[i] == ('.')) || (messageEncryption[i] == (',')) ||
                    (messageEncryption[i] == (';')) || (messageEncryption[i] == (':')) || (messageEncryption[i] == (' ')))) {
                encryptedChar = messageEncryption[i];
                encryptedMessage = encryptedMessage + Character.toString(encryptedChar);
            } else {
                encryptedChar = messageEncryption[i] += 7;
                encryptedMessage = encryptedMessage + Character.toString(encryptedChar);
            }
        }


        char[] messageDecryption = encryptedMessage.toCharArray(); //Die verschlüsselte Nachricht wird in ein neues Array gespeichert
        System.out.println(encryptedMessage + "\n");


        System.out.print("Entschlüsselt: ");

        int lengthEncryptedMessage = encryptedMessage.length();

        for (int j = 0; j < lengthEncryptedMessage; j++) {
            char decryptedChar;
            if (((messageDecryption[j] == ('!')) || (messageDecryption[j] == ('?')) ||
                    (messageDecryption[j] == ('.')) || (messageDecryption[j] == (',')) ||
                    (messageDecryption[j] == (';')) || (messageDecryption[j] == (':')) || (messageDecryption[j] == (' ')))) {
                decryptedChar = messageDecryption[j];
                decryptedMessage = decryptedMessage + Character.toString(decryptedChar);
            } else {
                decryptedChar = messageDecryption[j] -= 7;
                decryptedMessage = decryptedMessage + Character.toString(decryptedChar);
            }
        }
        System.out.println(decryptedMessage);
    }
}



