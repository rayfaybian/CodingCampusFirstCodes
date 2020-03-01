package com.company;

import java.util.Scanner;

public class StringHelper {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a word or a sentence.");
        String word = input.nextLine();
        word = word.toLowerCase();
        System.out.println("Choose an option\n1 Palindrome Check\n2 Count a letter in your Word/Sentence\n3 Reverse Your Word/Sentence\n4 Find out how many times each letter in your Word/Sentence occurs\n");
        int option = input.nextInt();


        switch (option) {

            case 1:
                System.out.println(isPalindrome(word));
                break;
            case 2:
                System.out.println("Enter the letter you want to count.");
                char letter = input.next().charAt(0);
                countLetters(word, letter);
                break;
            case 3:
                reverseString(word);
                break;
            case 4:
                printAmountOfLetters(word);
                break;
            default:
                System.out.println("Invalid input");


        }
    }

    public static boolean isPalindrome(String word) {
        return word.equalsIgnoreCase(reverseString(word));
    }

    public static int countLetters(String word, char letter) {
        int count = 0;


        char[] textArray = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (textArray[i] == letter)
                count++;
        }
        System.out.println("Lettercounter:\n\nYou picked the letter " + letter + "\nIt was found " + count + " times in your word/sentence.");

        return count;
    }


    public static String reverseString(String word) {
        String reverse = "";


        for (int i = word.length() - 1; i >= 0; i--) {
            reverse = reverse + word.charAt(i);
        }

        System.out.println("Word backwards is:\n");
        System.out.println(reverse);

        return reverse;


    }

    public static void printAmountOfLetters(String word) {


        char[] wordArray = word.toCharArray();
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int[] counter = new int[alphabet.length];
        int lineCounter = 0;

        String output = "";

        for (int i = 0; i < wordArray.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (alphabet[j] == wordArray[i]) {
                    counter[j]++;
                }


            }

        }

        for (int i = 0; i < alphabet.length; i++) {


            if (output.isEmpty()) {
                output = "\t" + alphabet[i] + " - " + counter[i] + "\t ";
                lineCounter++;
            } else {
                output = output + "\t" + alphabet[i] + " - " + counter[i] + "\t";
                lineCounter++;
            }
            if (lineCounter == 5) {
                output = output + "\n";
                lineCounter = 0;
            }


        }
        System.out.println("Amount of Letters: \n\n" + output);
    }
}









