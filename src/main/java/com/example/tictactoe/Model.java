package com.example.tictactoe;

public class Model {
    char[][] grid;
    int counter;

    public Model(){
        counter = 0;
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '.';
            }
        }
    }

    void set(int x, int y, char mark){
        this.grid[x][y] = mark;
        counter += 1;
    }

    public boolean isFull(){
        return counter == 9;
    }

    public String[] detectWin(){
        String[] sequence;
        for (int i = 0; i < 3; i++) {
            if(grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]){
                if(grid[i][0] != '.'){
                    sequence = new String[3];
                    sequence[0] = i + "," +0;
                    sequence[1] = i + "," +1;
                    sequence[2] = i + "," +2;
                    return sequence;
                }
            }
        }
        for (int j = 0; j < 3; j++) {
            if(grid[0][j] == grid[1][j] && grid[0][j] == grid[2][j]){
                if(grid[0][j] != '.'){
                    sequence = new String[3];
                    sequence[0] = 0 + "," +j;
                    sequence[1] = 1 + "," +j;
                    sequence[2] = 2 + "," +j;
                    return sequence;
                }
            }
        }
        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            if (grid[0][0] != '.') {
                sequence = new String[3];
                sequence[0] = 0 + "," + 0;
                sequence[1] = 1 + "," + 1;
                sequence[2] = 2 + "," + 2;
                return sequence;
            }
        }
        if(grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            if (grid[1][1] != '.') {
                sequence = new String[3];
                sequence[0] = 0 + "," + 2;
                sequence[1] = 1 + "," + 1;
                sequence[2] = 2 + "," + 0;
                return sequence;
            }
        }
        return null;
    }

    public char[][] getGrid(){
        return this.grid;
    }

    public boolean isEmpty(int x, int y){
        return grid[x][y] == '.';
    }
}
