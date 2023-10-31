package com.example.tictactoe;

public class Model {
    char[][] grid;
    int counter;
    private static final int GRID_SIZE = 3;
    private static final int WINNING_SEQUENCE_LENGTH = GRID_SIZE * GRID_SIZE;

    public Model(){
        grid = new char[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void reset() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }
        counter = 0;
    }

    void set(int x, int y, char mark){
        this.grid[x][y] = mark;
        counter += 1;
    }

    public boolean isFull(){
        return counter == WINNING_SEQUENCE_LENGTH;
    }

    public String[] detectWin(){
        String[] sequence;
        for (int i = 0; i < GRID_SIZE; i++) {
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
        for (int j = 0; j < GRID_SIZE; j++) {
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

    public boolean isEmpty(int x, int y){
        return grid[x][y] == '.';
    }
}
