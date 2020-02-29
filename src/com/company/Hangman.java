package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        //Schreibe ein Programm mit welchem man Wörter erraten kann.
        // Für dies erstellst du ein Array das du mit Wörtern befüllst.
        // Zufällig wird je Spiel ein Wort daraus ausgewählt.

        String[] words = {"Hampelmann", "Elektroschrott", "Arbeiterunfallversicherungsgesetz", "Parteibuch", "Nulllohnrunde", "Herbst",
                "Sonnenblume", "Teppichklopfer", "Sandmännchen", "Zutrittskontrollsystem", "Kinoticket", "Coding Campus Vorarlberg",
                "Mercedes Benz", "Mineralwasser", "Bier", "Zentrum für digitale Berufe und Weiterbildung"};

        boolean isFinished = false;
        boolean lose = false;

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int word = random.nextInt(words.length);

        String wordInGame = words[word].toLowerCase();
        char star = '*';
        int tries = 0;
        int remainingTries = 10;

        char[] wordChar = wordInGame.toCharArray();
        char[] space = {' '};
        String wordOnDisplay = "";

        char[] usedLetters = new char[26];
        int usedLetterCounter = 0;


        wordOnDisplay = replaceLettersWithStars(wordInGame, wordChar, space, wordOnDisplay);

        char[] hiddenWordChar = wordOnDisplay.toCharArray();

        remainingTries = chooseLevel(input, remainingTries);

        startGame(wordInGame, remainingTries, hiddenWordChar);


        while (!isFinished && !lose) {
            boolean newLetter = false;
            boolean guessCorrect = false;
            int counter = 0;
            char nextLetter = input.next().charAt(0);

            while (!newLetter) {
                for (int i = 0; i < 26; i++) {
                    if (nextLetter == usedLetters[i]) {
                        System.out.println("Du hast den Buchstaben " + nextLetter + " bereits versucht!\nBitte gib einen anderen Buchstaben ein.");
                        i = 0;
                        nextLetter = input.next().charAt(0);
                    }
                }
                newLetter = true;
            }
            usedLetters[usedLetterCounter] = nextLetter;
            Arrays.sort(usedLetters);
            usedLetterCounter++;

            for (int i = 0; i < wordInGame.length(); i++) {
                if (nextLetter == wordChar[i]) {
                    hiddenWordChar[i] = nextLetter;
                    guessCorrect = true;
                }
            }
            for (int j = 0; j < wordInGame.length(); j++) {
                if (hiddenWordChar[j] == star) {
                    counter++;
                }
            }
            if (counter == 0) {
                isFinished = true;
            }
            tries++;
            if (!guessCorrect)
                remainingTries--;

            if (remainingTries >= 0) {
                System.out.println("Noch " + remainingTries + " übrige Fehlversuche.");
                System.out.print("Bereits verwendete Buchstaben ");
                System.out.println(usedLetters);
                System.out.println(hiddenWordChar);
            } else {
                lose = true;
            }

        }
        if (lose) {
            System.out.println("Du hast leider verloren.\nDas gesuchte Wort war " + wordInGame + ".");
        } else
            System.out.println("Gratulation, du hast das Wort " + wordInGame + " in " + tries + " Versuchen erraten");
    }

    private static String replaceLettersWithStars(String wordInGame, char[] wordChar, char[] space, String wordOnDisplay) {
        StringBuilder wordOnDisplayBuilder = new StringBuilder(wordOnDisplay);
        for (int i = 0; i < wordInGame.length(); i++) {
            if (wordChar[i] == space[0]) {
                wordOnDisplayBuilder.append(" ");
            } else {
                wordOnDisplayBuilder.append("*");
            }
        }
        wordOnDisplay = wordOnDisplayBuilder.toString();
        return wordOnDisplay;
    }

    private static void startGame(String wordInGame, int remainingTries, char[] hiddenWordChar) {
        System.out.println("Willkommen bei Hangman.\nFinde das gesuchte Wort in dem du einen Buchstaben nach dem anderen richtig errätst." +
                "\nDu hast " + remainingTries + " Fehlversuch(e)." + "\n" +
                "Das gesuchte Wort besteht aus " + wordInGame.length() + " Buchstaben\n");
        System.out.println(hiddenWordChar);
    }

    private static int chooseLevel(Scanner input, int remainingTries) {
        System.out.println("Wähle den Schwierigkeitsgrad\n1 Anfänger\n2 Erfahren\n3 Profi");
        int level = input.nextInt();


        switch (level) {
            case 1:
                remainingTries = 10;
                break;
            case 2:
                remainingTries = 5;
                break;
            case 3:
                remainingTries = 1;
                break;
            default:
                System.out.println("Bitte wähle zwischen 1,2 oder 3");
                chooseLevel(input, remainingTries);

        }

        return remainingTries;
    }
}
