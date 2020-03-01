package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        //Schreibe ein Programm mit welchem man Wörter erraten kann.
        // Für dies erstellst du ein Array das du mit Wörtern befüllst.
        // Zufällig wird je Spiel ein Wort daraus ausgewählt.

        boolean isFinished = false;
        boolean lose = false;

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        char star = '*';
        int tries = 0;
        String userLevel = "";
        int chooseLevel = 10;


        char[] space = {' '};
        String wordOnDisplay = "";

        char[] usedLetters = new char[29];
        int usedLetterCounter = 0;

        String[] words = wordLibrary(); //contains some random words

        String wordInGame = getWord(words, random); //picks a random word from a String array
        char[] wordChar = wordInGame.toCharArray(); //casts the String into a CharArray

        wordOnDisplay = replaceLettersWithStars(wordInGame, wordChar, space, wordOnDisplay); //changes the word that gets printed into stars, while ignoring spaces (HELLO WORLD -> ***** *****)

        char[] hiddenWordChar = wordOnDisplay.toCharArray(); //casts the String into a CharArray

        chooseLevel = chooseLevel(input, chooseLevel); //Player get´s to chose the difficulty

        startGame(wordInGame, chooseLevel, hiddenWordChar, userLevel); //Game starts and waits for the first userinput

        game(isFinished, lose, input, wordInGame, star, tries, chooseLevel, wordChar, usedLetters, usedLetterCounter, hiddenWordChar);


    }

    private static String getWord(String[] words, Random random) {
        int word = random.nextInt(words.length);
        return words[word].toUpperCase();
    }

    private static String[] wordLibrary() {
        return new String[]{
                    "Hampelmann", "Elektroschrott", "Arbeiterunfallversicherungsgesetz", "Parteibuch", "Nulllohnrunde", "Herbst",
                    "Sonnenblume", "Teppichklopfer", "Sandmännchen", "Zutrittskontrollsystem", "Kinoticket", "Coding Campus Vorarlberg",
                    "Mercedes Benz", "Mineralwasser", "Bier", "Zentrum für digitale Berufe und Weiterbildung"};
    }

    private static void game(boolean isFinished, boolean lose, Scanner input, String wordInGame, char star, int tries, int remainingTries, char[] wordChar, char[] usedLetters, int usedLetterCounter, char[] hiddenWordChar) {
        while (!isFinished && !lose) {
            boolean newLetter = false;
            boolean guessCorrect = false;
            int counter = 0;
            char nextLetter = input.next().toUpperCase().charAt(0);

            while (!newLetter) {
                for (int i = 0; i < usedLetters.length; i++) {
                    if (nextLetter == usedLetters[i]) {
                        System.out.println("Du hast den Buchstaben " + nextLetter + " bereits versucht!\nBitte gib einen anderen Buchstaben ein.\n");
                        i = 0;
                        nextLetter = input.next().toUpperCase().charAt(0);
                    }
                }
                newLetter = true;
            }
            usedLetters[usedLetterCounter] = nextLetter;
            Arrays.sort(usedLetters);
            usedLetterCounter++;

                for (int j = 0; j < wordInGame.length(); j++) {
                    if (nextLetter == wordChar[j]) {
                        hiddenWordChar[j] = nextLetter;
                        guessCorrect = true;
                    }
                }
                for (int k = 0; k < wordInGame.length(); k++) {
                    if (hiddenWordChar[k] == star) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    isFinished = true;
                }
                tries++;
                if (!guessCorrect) {
                    remainingTries--;
                    System.out.println("\nFalsch! Der Buchstabe " + nextLetter + " ist leider nicht dabei. Versuche einen anderen.");
                } else {
                    System.out.println("\nRichtig! Der Buchstabe " + nextLetter + " ist dabei.");
                }

                if (remainingTries >= 0) {
                    System.out.println("Noch " + remainingTries + " übrige Fehlversuche.");
                    System.out.println();
                    System.out.println(hiddenWordChar);
                    System.out.print("Bereits verwendete Buchstaben:\t");
                    System.out.print(usedLetters);
                    System.out.println("\n");
                } else {
                    lose = true;
                }

            }

        if (lose) {
            System.out.println("Du hast leider verloren.\nDas gesuchte Wort war " + wordInGame + ".");
        } else {
            System.out.println("Gratulation, du hast das Wort " + wordInGame + " in " + tries + " Versuchen erraten");
        }
    }


    private static String replaceLettersWithStars(String wordInGame, char[] wordChar, char[] space, String
            wordOnDisplay) {
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

    private static void startGame(String wordInGame, int remainingTries, char[] hiddenWordChar, String userLevel) {
        switch (remainingTries) {
            case 10:
                userLevel = "Anfänger";
                break;
            case 5:
                userLevel = "Erfahren";
                break;
            case 1:
                userLevel = "Profi";
                break;
        }

        System.out.println("Willkommen bei Hangman.\n\nFinde das gesuchte Wort in dem du einen Buchstaben nach dem anderen richtig errätst." +
                "\nDu hast den Schwierigkeitsgrad " + userLevel + " gewählt und daher " + remainingTries + " Fehlversuch(e).\n\n" + "Das gesuchte Wort besteht aus " + wordInGame.length() + " Buchstaben\n");
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

