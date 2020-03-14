package com.company.Battleships;

import java.util.Scanner;

public class Game {
    private static StringBuilder output = new StringBuilder();
    private static char[][] playerShips = new char[10][10];
    private static char[][] cpuShips = new char[10][10];
    private static char[][] playerField = new char[10][10];
    private static char[][] cpuField = new char[10][10];
    private static Scanner userInput = new Scanner(System.in);
    private static int schlachtschiff = 1;
    private static int kreuzer = 2;
    private static int zerstörer = 3;
    private static int uBoot = 4;
    private static int sizeOfShip;
    private static String shipName;
    private static char callSign;
    private static int row;
    private static int col;

    public static void main(String[] args) {


        emptyField();
        printPlayerBoard();
        getShipCoordinates();


        {
            int columnCounter = 0;
            System.out.println();
            output.append("\t| A | B | C | D | E | F | G | H | I | J |\n");
            for (int i = 0; i < 10; i++) {
                output.append(i + 1).append("\t| ");
                for (int j = 0; j < 10; j++) {
                    output.append(playerShips[i][j] + " | ");
                    columnCounter++;
                }
                if (columnCounter == 10) {
                    output.append("\n");
                    columnCounter = 0;
                }
            }


            System.out.println(output);
            output.setLength(0);

        }
        getShipCoordinates();

        {
            int columnCounter = 0;
            System.out.println();
            output.append("\t| A | B | C | D | E | F | G | H | I | J |\n");
            for (int i = 0; i < 10; i++) {
                output.append(i + 1).append("\t| ");
                for (int j = 0; j < 10; j++) {
                    output.append(playerShips[i][j] + " | ");
                    columnCounter++;
                }
                if (columnCounter == 10) {
                    output.append("\n");
                    columnCounter = 0;
                }
            }


            System.out.println(output);
            output.setLength(0);

        }
    }

    private static void getShipCoordinates() {
        System.out.println("Wähle ein Schiff.\n1 Schlachtschiff\n2 Kreuzer\n3 Zerstörer\n4 Uboot");
        int playerShip = userInput.nextInt();
        switch (playerShip) {
            case 1:
                shipName = "Schlachtschiff";
                callSign = 'S';
                sizeOfShip = 5;
                break;
            case 2:
                shipName = "Kreuzer";
                callSign = 'K';
                sizeOfShip = 4;
                break;
            case 3:
                shipName = "Zerstörer";
                callSign = 'Z';
                sizeOfShip = 3;
                break;
            case 4:
                shipName = "Uboot";
                callSign = 'U';
                sizeOfShip = 2;
                break;
            default:
                System.out.println("Falsche Eingabe!");
        }


        System.out.println("Wähle eine Spalte für dein Schiff.");
        char shipCol = userInput.next().toUpperCase().charAt(0);

        col = getNumberForLetter(shipCol);

        System.out.println("Wähle eine Reihe für dein Schiff.");
        row = userInput.nextInt() - 1;

        System.out.println("Wähle eine Orientierung für dein Schiff.\n1 Horizontal\n2 Vertikal");
        try {
            int orientation = userInput.nextInt();
            switch (orientation) {
                case 1:
                    setShipHorizontal();
                    break;

                case 2:
                    setShipVertical();
                    break;

                default:
                    System.err.println("Eingabe Fehlerhaft! Bitte wähle zwischen 1 und 2");
                    break;
            }
        } catch (NumberFormatException e1) {
            System.err.println("Eingabe Fehlerhaft! Bitte wähle zwischen 1 und 2");
        }
    }

    private static void setShipHorizontal() {
        for (int i = 0; i < sizeOfShip; i++)
            {
                if (playerShips[row][col] != '\u0000') {
                    System.out.println("Nicht möglich. An dieser Stelle ist bereits ein Schiff.");
                } else {
                    col++;
                }
            }
            for (int k = 0; k < sizeOfShip; k++) {
                col--;
            }

        for (int j = 0; j < sizeOfShip + 1; j++) {
            col++;
            playerShips[row][col] = callSign;
        }

    }

    private static void setShipVertical() {
        for (int i = 0; i < sizeOfShip; i++)
            {
                if (playerShips[row][col] != '\u0000') {
                    System.out.println("Nicht möglich. An dieser Stelle ist bereits ein Schiff.");

                } else {
                    row++;
                }
            }
            for (int k = 0; k < sizeOfShip; k++) {
                row--;
            }
            playerShips[row][col] = callSign;
            for (int j = 0; j < sizeOfShip; j++) {
                row++;
                playerShips[row][col] = callSign;
            }

    }

    private static int getNumberForLetter(char letter) {

        return letter - (int) 'A';
    }

    private static void printPlayerBoard() {
        printCpuBoard();
    }

    private static void printCpuBoard() {
        int columnCounter = 0;
        System.out.println();
        output.append("\t| A | B | C | D | E | F | G | H | I | J |\n");
        for (int i = 0; i < 10; i++) {
            output.append(i + 1).append("\t| ");
            for (int j = 0; j < 10; j++) {
                output.append(cpuField[i][j] + " | ");
                columnCounter++;
            }
            if (columnCounter == 10) {
                output.append("\n");
                columnCounter = 0;
            }
        }
        System.out.println(output);
        output.setLength(0);
    }

    private static void emptyField() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerField[i][j] = '-';
            }
        }
    }

}