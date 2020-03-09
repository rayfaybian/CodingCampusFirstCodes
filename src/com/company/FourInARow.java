package com.company;

import java.util.Scanner;

public class FourInARow {
    private static boolean isEnglish = true;
    private static boolean gameOver = false;
    private static boolean isFinished = false;
    private static boolean isPlayerX = true;
    private static boolean userInputIsOkay = false;
    private static char x = 'X';
    private static char o = 'O';
    private static char[][] field = new char[6][7];
    private static Scanner input = new Scanner(System.in);
    private static StringBuilder output = new StringBuilder();
    private static int turnCounter = 0;
    private static String currentPlayerName = "";
    private static String playerXName = "";
    private static String playerOName = "";
    private static String winnerName = "";
    private static String loserName = "";

    public static void main(String[] args) {
        language();
        getNames();
        runGame();
    }

    private static void runGame() {


        while (!gameOver) {
            turnCounter = 0;

            if (isEnglish) {
                System.out.println("\nWelcome to Four In A Row." +
                        "\nBeat the other player by placing 4 chips into a straight line." +
                        "\nX will make the first move.\n");
            } else {
                System.out.println("\nWillkommen bei Vier gewinnt." +
                        "\nBesiege deinen Gegenspieler indem du 4 Symbole in eine gerade Linie bringst");
            }

            printBoard();
            if (isEnglish) {
                System.out.println("Enter the number of the column where you want to drop your chip.");
            } else {
                System.out.println("Gib die Zahl der Spalte an in die du dein Symbol setzen möchtest.");
            }

            while (!isFinished) {

                determinePlayer();
                getUserInput();
                printBoard();
                checkForWinner();
            }

            newGame();

        }
    }

    private static void getUserInput() {
        boolean fieldIsEmpty = false;
        userInputIsOkay = false;
        int row = 5;
        while (!userInputIsOkay) {
            try {
                int playersColumn = input.nextInt() - 1;

                while (!fieldIsEmpty)
                    if (field[row][playersColumn] == x | field[row][playersColumn] == o) {
                        row--;
                    } else {
                        fieldIsEmpty = true;
                        userInputIsOkay = true;
                    }

                if (isPlayerX) {
                    field[row][playersColumn] = x;
                } else {
                    field[row][playersColumn] = o;
                }
            } catch (NumberFormatException e1) {
                if (isEnglish) {
                    System.err.println("Invalid input! Pick a column between 1-7.");
                    System.out.println(currentPlayerName + ", your turn.\n");
                } else {
                    System.err.println("Eingabe Fehlerhaft! Wähle eine Spalte zwischen 1 und 7.");
                    System.out.println(currentPlayerName + ", du bist dran.\n");
                }
            } catch (ArrayIndexOutOfBoundsException e2) {
                if (isEnglish) {
                    System.err.println("Invalid input. Pick a number between 1-7.");
                    System.out.println(currentPlayerName + ", your turn.\n");
                } else {
                    System.err.println("Eingabe Fehlerhaft! Wähle eine Spalte zwischen 1 und 7.");
                    System.out.println(currentPlayerName + ", du bist dran.\n");
                }
            }
        }
    }//player can pick which column he wants to drop his next chip

    private static void determinePlayer() {

        if (isPlayerX) {
            currentPlayerName = playerXName;
        } else {
            currentPlayerName = playerOName;
        }
        if (isEnglish) {
            System.out.println(currentPlayerName + ", your turn.\n");
        } else {
            System.out.println(currentPlayerName + ", du bist dran.\n");
        }

    } //switches between X & O after every round

    private static void printBoard() {
        int columnCounter = 0;
        System.out.println();
        output.append("   1   2   3   4   5   6   7\n");
        for (int i = 0; i < 6; i++) {
            //output.append(i+1).append(" ");
            for (int j = 0; j < 7; j++) {
                output.append(" | ");
                output.append(field[i][j]);
                columnCounter++;
            }
            if (columnCounter == 7) {
                output.append(" |\n");
                columnCounter = 0;
            }
        }
        System.out.println(output);
        output.setLength(0);
    } //prints the board after every turn containing all previous executed moves

    private static void checkForWinner() {
        char winner;

        turnCounter++;

        if (isPlayerX) {
            winner = x;
            winnerName = playerXName;
            loserName = playerOName;
            isPlayerX = false;
        } else {
            winner = o;
            winnerName = playerOName;
            loserName = playerXName;
            isPlayerX = true;
        }

        for (char[] chars : field) {
            for (int col = 0; col < chars.length - 3; col++) {
                if ((chars[col] == winner) && (chars[col] == chars[col + 1])
                        && (chars[col] == chars[col + 2])
                        && (chars[col] == chars[col + 3])) {
                    announceWinner(winnerName, loserName);
                }
            }
        } //check horizontally

        for (int col = 0; col < field[0].length; col++) {
            for (int row = 0; row < field.length - 3; row++) {
                if ((field[row][col] == winner) && (field[row][col] == field[row + 1][col])
                        && (field[row][col] == field[row + 2][col])
                        && (field[row][col] == field[row + 3][col])) {
                    announceWinner(winnerName, loserName);
                }
            }
        } //check vertically

        for (int row = 0; row < field.length - 3; row++) {
            for (int col = 0; col < field[row].length - 3; col++) {
                if ((field[row][col] == winner) && (field[row][col] == field[row + 1][col + 1])
                        && (field[row][col] == field[row + 2][col + 2])
                        && (field[row][col] == field[row + 3][col + 3])) {
                    announceWinner(winnerName, loserName);
                }
            }
        } //check diagonally first direction

        for (int row = 0; row < field.length - 3; row++) {
            for (int col = 3; col < field[row].length; col++) {
                if ((field[row][col] == winner) && (field[row][col] == field[row + 1][col - 1])
                        && (field[row][col] == field[row + 2][col - 2])
                        && (field[row][col] == field[row + 3][col - 3])) {
                    announceWinner(winnerName, loserName);
                }
            }
        } //check diagonally second direction

        if ((turnCounter == 42) && (!isFinished)) {
            if (isEnglish) {
                System.out.println("It´s a draw. No winner because both of you suck!");
            } else {
                System.out.println("Unentschieden! Niemand gewinnt weil ihr beide mies seid!");
            }
            isFinished = true;
        } //check for draw
    }//checks if either player has won or if it´s a draw

    private static void announceWinner(String winnerName, String loserName) {
        if (isEnglish) {
            System.out.println(winnerName + ", you destroyed " + loserName + " !!!");

        } else {
            System.out.println(winnerName + " du hast " + loserName + " zerstört!!!");
        }
        isFinished = true;
    }
    //announce if somebody won or if it´s a draw

    private static void newGame() {
        userInputIsOkay = false;
        while (!userInputIsOkay) {

            if (isEnglish) {
                System.out.println("\nDo you want to play again? Ⓨ/Ⓝ");
            } else {
                System.out.println("\nWollt ihr noch eine Runde spielen? Ⓨ/Ⓝ");
            }
            try {
                String answer = input.next();
                if (answer.equalsIgnoreCase("y")) {
                    userInputIsOkay = true;
                    resetGame();
                    runGame();
                }
                if (answer.equalsIgnoreCase("n")) {
                    if (isEnglish) {
                        System.out.println("ッ Thanks for playing. See ya! ッ");
                    } else {
                        System.out.println("ッ Danke für´s spielen. Bis bald! ッ");
                    }
                    userInputIsOkay = true;
                    gameOver = true;
                } else {
                    if (isEnglish) {
                        System.err.println("Wrong input!");
                    } else {
                        System.err.println("Eingabe Fehlerhaft!");
                    }
                }
            } catch (Exception e) {
                if (isEnglish) {
                    System.err.println("Wrong input!");
                } else {
                    System.err.println("Eingabe Fehlerhaft!");
                }

            }
        }

    }//asks you if you want a rematch or quit the game

    private static void resetGame() {
        gameOver = false;
        isFinished = false;
        isPlayerX = true;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                field[row][col] = '\u0000';

            }

        }

    }//empties the board and resets all values to their default

    private static boolean language() {
        System.out.println("\n\nLanguage/Sprache\n\n\t1 English\n\t2 Deutsch\n");
        int languageCode = input.nextInt();
        switch (languageCode) {
            case 1:
                isEnglish = true;
                break;
            case 2:
                isEnglish = false;
                break;
            default:
                System.out.println("Wrong input!/Falsche Eingabe!");
        }
        return isEnglish;
    }//choose between english and german

    private static void getNames() {
        if (isEnglish) {
            System.out.println("Player X, what´s your name?");
            playerXName = input.next();
            System.out.println("Hello " + playerXName + "!\n");

            System.out.println("Player O, what´s your name?");
            playerOName = input.next();
            System.out.println("Hello " + playerOName + "!\n");
        } else {
            System.out.println("Spieler X, wie ist dein Name?");
            playerXName = input.next();

            System.out.println("Hallo " + playerXName + "!\n");

            System.out.println("Spieler O, wie ist dein Name?");
            playerOName = input.next();
            System.out.println("Hallo " + playerOName + "!\n");
        }
    }//ask players for their names
}


