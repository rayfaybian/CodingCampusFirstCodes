package com.company;

public class Main {

    public static void main(String[] args) {

        // Verschlüssle diese Nachricht: "DASISTEINEGEHEIMENACHRICHT"
        // Nutze die Cäsar Verschlüsselung zb. A -> B/ X -> Y


        String message = "DASISTEINEGEHEIMENACHRICHT";
        char[] messageEncryption = message.toCharArray();
        int lengthMessage = message.length();
        String encryptedMessage = "";
        String decryptedMessage = "";


        System.out.println("\n");

        System.out.print("Verschlüsselt: ");

        for (int i = 0; i < lengthMessage; i++) {
            char encryptedChar = messageEncryption[i] += 1;
            encryptedMessage = encryptedMessage + Character.toString(encryptedChar);

        }
        System.out.println(encryptedMessage + "\n");

        char[] messageDecryption = encryptedMessage.toCharArray();

        System.out.print("Entschlüsselt: ");

        int lengthEncryptedMessage = encryptedMessage.length();

        for (int i = 0; i < lengthEncryptedMessage; i++) {
            char decryptedChar = messageDecryption[i] -= 1;
            decryptedMessage = decryptedMessage + Character.toString(decryptedChar);
        }

        System.out.println(decryptedMessage);

    }
}
