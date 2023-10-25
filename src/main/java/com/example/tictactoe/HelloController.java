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
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private GridPane map;

    @FXML
    private Label turn;
    static int player = 0;

    private static final int GRID_SIZE = 3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        Player.model = new Model();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
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
        File file = new File("src/main/resources/com/example/tictactoe/images/" + name);
        try {
            return file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(char[][] grid){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                getImage(grid, i, j);
            }
        }
    }

    private void getImage(char[][] grid, int i, int j) { //Method name ok?
        switch (grid[i][j]){
            case 'X': {
                ImageView image1 = (ImageView) getNodeByRowColumnIndex(i, j, map);
                image1.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/x_blue.png")).toExternalForm()));
            }
            break;
            case 'O': {
                ImageView image2 = (ImageView) getNodeByRowColumnIndex(i, j, map);
                image2.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/o_red.png")).toExternalForm()));
            }
            break;
            case '.': {
                ImageView image3 = (ImageView) getNodeByRowColumnIndex(i, j, map);
                image3.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/empty.png")).toExternalForm()));
            }
            break;
            case '1': {
                ImageView image4 = (ImageView) getNodeByRowColumnIndex(i, j, map);
                image4.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/x_yellow.png")).toExternalForm()));
            }
            break;
            case '2': {
                ImageView image5 = (ImageView) getNodeByRowColumnIndex(i, j, map);
                image5.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/o_yellow.png")).toExternalForm()));
            }
            break;
        }
    }
}