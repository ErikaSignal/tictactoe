package com.example.tictactoe;

public class Player {
    char mark;
    static Model model;

    public Player(char mark){ //initializing a new instance of the Player class with the specified mark, 'X' or 'O'
        this.mark = mark;
    }

    public void Play(int x, int y){ //calls the set() method from the Model class to make a move at the specified position
        model.set(x, y, mark);
    }
}