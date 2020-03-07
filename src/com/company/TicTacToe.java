package com.company;

import java.util.Scanner;

public class TicTacToe {
    private static boolean gameOver = false;
    private static boolean isFinished = false;
    private static boolean isPlayerX = true;
    private static boolean userInputIsOkay = false;
        private static int row;
    private static int column;
    private static char x = 'X';
    private static char o = 'O';
    private static char[][] field = new char[3][3];
    private static Scanner input = new Scanner(System.in);
    //private static String output = "";
    private static StringBuilder output = new StringBuilder();
    private static int turnCounter = 0;
    private static String currentPlayer = "";

    public static void main(String[] args) {
        // create a TicTacToe game
        game();
    }

    private static void game() {

        while (!gameOver) {


            System.out.println("\nWelcome to TicTacToe.\nBeat the other player by placing 3 X´s or 3 O`s into a straight line.\nX will make the first move.\n");
            printBoard();
            System.out.println("Type in a row, a coma, and a column. For example 1,1");

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
        userInputIsOkay = false;
        while(!userInputIsOkay)

        try {
            String[] userInput = input.next().split(",");

            row = Integer.parseInt(userInput[0]);
            column = Integer.parseInt(userInput[1]);

            if (field[row - 1][column - 1] == x | field[row - 1][column - 1] == o) {
                System.out.println("Spot already taken! Try an empty field.");
                System.out.println("Enter the coordinates where you want to place a " + currentPlayer + "\n");

            } else{
                userInputIsOkay = true;
            }

            if (isPlayerX) {
                field[row - 1][column - 1] = x;
            } else {
                field[row - 1][column - 1] = o;
            }

        } catch (NumberFormatException e1) {
            System.err.println("Invalid input. Try again!");
            System.out.println("Enter the coordinates where you want to place a " + currentPlayer + "\n");
        } catch (ArrayIndexOutOfBoundsException e2) {
            System.err.println("Field " + row + "," + column + " does not exist. Try again!");

        }
    }

    //player can type in the coordinates for his next move into the console

    private static void determinePlayer() {
        turnCounter++;
        if (isPlayerX) {
            currentPlayer = "X";
        } else {
            currentPlayer = "O";
        }
        System.out.println("Enter the coordinates where you want to place a " + currentPlayer + "\n");
    } //switches between X & O after every round

    private static void printBoard() {
        int columnCounter = 0;
        System.out.println();
        output.append("   1 2 3\n");
        for (int i = 0; i < 3; i++) {
            output.append(i + 1).append(" ");
            for (int j = 0; j < 3; j++) {
                output.append("|");
                output.append(field[i][j]);
                columnCounter++;
            }
            if (columnCounter == 3) {
                output.append("|\n");
                columnCounter = 0;
            }
        }
        System.out.println(output);
        output.setLength(0);
    } //prints the board after every turn containing all previous executed moves

    private static void checkForWinner() {
        char winner;
        if (isPlayerX) {
            winner = x;
            isPlayerX = false;
        } else {
            winner = o;
            isPlayerX = true;
        }

        for (int i = 0; i < field.length; i++)
            if ((field[0][i] == winner) && (field[1][i] == winner) && (field[2][i] == winner)) {
                isFinished = true;
            } else if (((field[i][0]) == winner) && ((field[i][1]) == winner) && ((field[i][2]) == winner)) {
                isFinished = true;
            } else if (((field[0][0]) == winner) && ((field[1][1]) == winner) && ((field[2][2]) == winner)) {
                isFinished = true;
            } else if (((field[0][2]) == winner) && ((field[1][1]) == winner) && ((field[2][0]) == winner)) {
                isFinished = true;
            }

        if (isFinished) {
            System.out.println(winner + " has won the game!\nCongratulations!");
        }


        if ((turnCounter == 9) && (!isFinished)) {
            System.out.println("No winner. It´s a draw!");
            isFinished = true;
        }


    } //checks if the game is won by either player

    private static boolean newGame() {
        System.out.println("\nDo you want to play again? y/n");

        try {
            String answer = input.next();
            if (answer.equalsIgnoreCase("y")) {
                resetGame(field);
                game();
            }
            if (answer.equalsIgnoreCase("n")) {
                System.out.println("Thanks for playing. See ya!");
                gameOver = true;
            } else {
                System.out.println("Wrong input!");
                newGame();
            }

        } catch (Exception e) {
            System.out.println("Wrong input!");
            newGame();

        }


        return gameOver;
    }//asks you if you want a rematch or quit the game

    private static void resetGame(char[][] field) {
        gameOver = false;
        isFinished = false;
        isPlayerX = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = '\u0000';

            }

        }

    }//empties the board and resets all values to their default

}



