package com.example.tictactoe;

public class Model {
    char[][] grid;
    int counter;
    private static final int gridSize = 3;
    private static final int winningSequenceLength = gridSize * gridSize;

    public Model(){
        initializeGrid();
    }

    public void reset() {
        initializeGrid();
        counter = 0;
    }

    private void initializeGrid() {
        grid = new char[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '.';
            }
        }
    }

    void set(int x, int y, char mark){
        this.grid[x][y] = mark;
        counter++;
    }

    public boolean isFull(){
        return counter == winningSequenceLength;
    }

    public String[] detectWin(){
        String[] sequence = null;
        for (int i = 0; i < gridSize; i++) {
            if (checkSequence(grid[i][0], grid[i][1], grid[i][2])) {
                sequence = new String[]{i + ",0", i + ",1", i + ",2"};
                return sequence;
            }
            if (checkSequence(grid[0][i], grid[1][i], grid[2][i])) {
                sequence = new String[]{"0," + i, "1," + i, "2," + i};
                return sequence;
            }
        }
        if (checkSequence(grid[0][0], grid[1][1], grid[2][2])) {
            sequence = new String[]{"0,0", "1,1", "2,2"};
        } else if (checkSequence(grid[0][2], grid[1][1], grid[2][0])) {
            sequence = new String[]{"0,2", "1,1", "2,0"};
        }
        return sequence;
    }

    private boolean checkSequence(char c1, char c2, char c3) {
        return c1 != '.' && c1 == c2 && c2 == c3;
    }

    public boolean isEmpty(int x, int y){
        return grid[x][y] == '.';
    }
}
