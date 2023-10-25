package com.example.tictactoe;

public class Player {
    char mark;
    static Model model;
    public static Player player1;
    public static Player player2;

    public Player(char mark){
        this.mark = mark;
    }

    public void Play(int x, int y){
        model.set(x, y, mark);
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }
}
