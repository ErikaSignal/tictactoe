package com.example.tictactoe;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private GridPane map;

    @FXML
    private Label turn;
    static int player = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        Player.model = new Model();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int col = i;
                final int row = j;

                imageView.setOnMouseClicked(evt -> {
                    if(player == 0){
                        if(Player.model.isEmpty(row, col)){
                            player1.Play(row, col);
                            player = 1;
                            if(Player.model.detectWin() != null){
                                turn.setText("Player X win!");
                                String[] positions = Player.model.detectWin();
                                for (String pos : positions){
                                    int x = Integer.parseInt(pos.split(",")[0]);
                                    int y = Integer.parseInt(pos.split(",")[1]);
                                    player1.setMark('1');
                                    player1.Play(x, y);
                                }
                            }else {
                                if(Player.model.isFull()){
                                    turn.setText("Game over");
                                    player = 1;
                                }else {
                                    turn.setText("Player O turn");
                                }
                            }
                        }
                    }else {
                        if(player != -1){
                            if(Player.model.isEmpty(row, col)){
                                player2.Play(row, col);
                                player = 0;
                                if(Player.model.detectWin() != null){
                                    turn.setText("Player O win!");
                                    String[] positions = Player.model.detectWin();
                                    for(String pos : positions){
                                        int x = Integer.parseInt(pos.split(",")[0]);
                                        int y = Integer.parseInt(pos.split(",")[1]);
                                        player1.setMark('2');
                                        player1.Play(x, y);
                                    }
                                    player = -1;
                                }else {
                                    if(Player.model.isFull()){
                                        turn.setText("Game over");
                                        player = -1;
                                    }else {
                                        turn.setText("Player X turn");
                                    }
                                }
                            }
                        }
                    }
                    update(Player.model.grid);
                });
                map.setHgap(10);
                map.setVgap(10);
                map.add(imageView, i, j);
            }
        }
        turn.setText("Player X turn");
    }

    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridpane){
        Node result = null;
        ObservableList<Node> children = gridpane.getChildren();

        for (Node node : children){
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column){
                result = node;
                break;
            }
        }
        return result;
    }

    public String getURL(String name){
        File file = new File("src/main/java/com/example/tictactoe/images/" + name);
        try {
            return file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(char[][] grid){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (grid[i][j]){
                    case 'X': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j, map);
                        imv.setImage(new Image(getURL("x.blue.png")));
                    }
                    break;
                    case '0': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j, map);
                        imv.setImage(new Image(getURL("o.red.png")));
                    }
                    break;
                    case '.': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j, map);
                        imv.setImage(new Image(getURL("empty.png")));
                    }
                    break;
                    case '1': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j, map);
                        imv.setImage(new Image(getURL("x.yellow.png")));
                    }
                    break;
                    case '2': {
                        ImageView imv = (ImageView) getNodeByRowColumnIndex(i, j, map);
                        imv.setImage(new Image(getURL("o.yellow.png")));
                    }
                    break;
                }
            }
        }
    }
}