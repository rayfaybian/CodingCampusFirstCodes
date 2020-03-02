package com.company;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {
    private static boolean isFinished = false;
    private static boolean playerX = true;
    private static int row;
    private static int column;
    private static char x = 'X';
    private static char o = 'O';
    private static char[][] field = new char[3][3];
    private static Scanner input = new Scanner(System.in);
    //private static String output = "";
    private static StringBuilder output2 = new StringBuilder();

    public static void main(String[] args) {
        // create a TicTacToe game
        game();
    }

    private static void game() {

        System.out.println("\nWelcome to TicTacToe.\nBeat the other player by placing 3 X´s or 3 O`s into a straight line.\nX will make the first move.\n");
        printBoard();

        while (!isFinished) {
            //output = "";

            determinePlayer();
            getUserInput();
            printBoard();
            checkForWinner();
        }

    }

    private static void getUserInput() {
        int row;
        int column;
        String[] userInput = input.next().split(",");

        row = Integer.parseInt(userInput[0]);
        column = Integer.parseInt(userInput[1]);

        if (playerX) {
            field[row][column] = x;

        } else {
            field[row][column] = o;

        }
    } //player can type in the coordinates for his next move into the console

    private static void determinePlayer() {
        if (playerX) {
            System.out.println("Enter the coordinates where you want to place an X.\n");
        } else {
            System.out.println("Enter the coordinates where you want to place an O.\n");
        }
    } //switches between X & O after every round

    private static void printBoard() {
        int columnCounter = 0;
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //output = output + ("|" + field[i][j]);
                output2.append("|");
                output2.append(field[i][j]);

                columnCounter++;
            }
            if (columnCounter == 3) {
                //output = output + ("|\n");
                output2.append("|\n");
                columnCounter = 0;
            }

        }
        //System.out.println(output);
        System.out.println(output2);
        output2.setLength(0);
    } //prints the board after every turn containing all previous executed moves

    private static void checkForWinner() {
        char winner;
        if (playerX) {
            winner = x;
            playerX = false;
        } else {
            winner = o;
            playerX = true;
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
    } //checks if the game is won by either player
}



