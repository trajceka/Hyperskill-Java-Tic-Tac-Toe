package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        
        UserInterface ui = new TextUserInterface(sc);
        ui.start();
          
    }
}

interface UserInterface {

    void start();
}

class TextUserInterface implements UserInterface {
    
    private Scanner sc;
    private int lfield = 3;
    private char[][] cells = new char[lfield][lfield];
    private int x = 0;
    private int y = 0;
    private int e = 0;
    
    public TextUserInterface(Scanner sc) {
        this.sc = sc;
        for (int i = 0; i < lfield; i++) {
            for (int j = 0; j < lfield; j++) {
                cells[i][j] = ' ';
            }
        }
    }
    
    @Override
    public void start() {
        //System.out.print("Enter cells: ");
        //inputB();
        displayB();
        int stateG = 0;
        while (stateG == 0) {
            stateG = state();
            if (stateG == 0) {
                inputCor();
                displayB();
            }
        }
        printStatus(stateG);
    }
    
    private void inputB(){
        String input = sc.next();
        for (int i = 0; i < lfield; i++) {
            for (int j = 0; j < lfield; j++) {
                cells[i][j] = input.charAt(i * lfield + j);
            }
        }
    }
    
    private void displayB() {
        
        System.out.println("---------");
        System.out.println("| " + cells[0][0] + " " + cells[0][1] + " " + cells[0][2] + " |");
        System.out.println("| " + cells[1][0] + " " + cells[1][1] + " " + cells[1][2] + " |");
        System.out.println("| " + cells[2][0] + " " + cells[2][1] + " " + cells[2][2] + " |");
        System.out.println("---------");        
    }
    
    private int state() {
        x = 0;
        y = 0;
        for (int i = 0; i < lfield; i++) {
            for (int j = 0; j < lfield; j++) {
                if (cells[i][j] == 'X') {
                    x++;
                }
                if (cells[i][j] == 'O') {
                    y++;
                }
                if (cells[i][j] == ' ') {
                    e++;
                }
            }
        }
        int[] w = new int[2];
        char xo = 'O';
        for (int i = 0; i < 2; i++) {
            if (i == 1) {
                xo = 'X';
            }
            if (cells[0][0] == xo && cells[0][1] == xo && cells[0][2] == xo) {
                w[i]++;
            }
            if (cells[1][0] == xo && cells[1][1] == xo && cells[1][2] == xo) {
                w[i]++;
            }
            if (cells[2][0] == xo && cells[2][1] == xo && cells[2][2] == xo) {
                w[i]++;
            }
            if (cells[0][0] == xo && cells[1][0] == xo && cells[2][0] == xo) {
                w[i]++;
            }
            if (cells[0][1] == xo && cells[1][1] == xo && cells[2][1] == xo) {
                w[i]++;
            }
            if (cells[0][2] == xo && cells[1][2] == xo && cells[2][2] == xo) {
                w[i]++;
            }
            if (cells[0][0] == xo && cells[1][1] == xo && cells[2][2] == xo) {
                w[i]++;
            }
            if (cells[2][0] == xo && cells[1][1] == xo && cells[0][2] == xo) {
                w[i]++;
            }
        }
        if (w[0] > 0 && w[1] > 0 || w[0] > 2 || w[1] > 2 || Math.abs(x - y) > 1) {
            // System.out.println("Impossible");
            return 4;
        } else if (w[0] > 0) {
            // System.out.println("O wins");
            return 1;
        } else if (w[1] > 0) {
            // System.out.println("X wins");
            return 2;
        } else if (x + y >= 9) {
            // System.out.println("Draw");
            return 3;
        } else {
            // System.out.println("Game not finished");
            return 0;
        }
    }
    
    private void inputCor() {
        char xo = 'X';
        if (x > y) {
            xo = 'O';
        } else {
            xo = 'X';
        }
        boolean isG = false;
        while (!isG) {
            System.out.println("Enter the coordinates: ");
            int[] coor = {-1, -1};
            String scor = sc.nextLine();
            boolean isText = false;
            Scanner readscor = new Scanner(scor);
            for (int i = 0; i < 2 && readscor.hasNext(); i++) { 
                if (readscor.hasNextInt()) {
                    coor[i] = Integer.parseInt(readscor.next());
                } else {
                    System.out.println("You should enter numbers!");
                    isText = true;
                    break;
                }
            }
            if (!isText) {
                if (coor[0] < 1 || coor[0] > 3 || coor[1] < 1 || coor[1] > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (cells[3 - coor[1]][coor[0] - 1] == 'X' || cells[3 - coor[1]][coor[0] - 1] == 'O' ) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    cells[3 - coor[1]][coor[0] - 1] = xo;
                    isG = true;
                }
            }
        }
    }
    
    private void printStatus(int statusG) {
        switch (statusG) {
            case 1:
                System.out.println("O wins");
                break;
            case 2:
                System.out.println("X wins");
                break;
            case 3:
                System.out.println("Draw");
                break;
            default:
                System.out.println("Impossible");
                break;
        }
    }
    
    
    /*
    private void commented() {
        // System.out.println("X O X");
        // System.out.println("O X O");
        // System.out.println("X X O");
    }
    */
