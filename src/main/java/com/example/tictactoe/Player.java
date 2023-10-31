package com.example.tictactoe;

public class Player {
    char mark;
    static Model model;

    public Player(char mark){
        this.mark = mark;
    }

    public void Play(int x, int y){
        model.set(x, y, mark);
    }
}
