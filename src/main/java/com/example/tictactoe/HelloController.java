package com.example.tictactoe;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    @FXML
    private Button replayButton;

    private boolean gameActive = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        Player.model = new Model();

        replayButton.setOnAction(event -> resetGame());

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int col = i;
                final int row = j;
                imageView.setOnMouseClicked(evt -> {
                    if (!gameActive) {
                        return;
                    }
                    if (player == 0) {
                        if (Player.model.isEmpty(row, col)) {
                            player1.Play(row, col);
                            player = 1;
                            if (Player.model.detectWin() != null) {
                                turn.setText("Player X win!");
                                String[] positions = Player.model.detectWin();
                                for (String pos : positions) {
                                    int x = Integer.parseInt(pos.split(",")[0]);
                                    int y = Integer.parseInt(pos.split(",")[1]);
                                    player1.setMark('1');
                                    player1.Play(x, y);
                                }
                                gameActive = false;
                            } else {
                                if (Player.model.isFull()) {
                                    turn.setText("Game over");
                                    player = 1;
                                    gameActive = false;
                                } else {
                                    turn.setText("Player O turn");
                                }
                            }
                        }
                    } else {
                        if (player != -1) {
                            if (Player.model.isEmpty(row, col)) {
                                player2.Play(row, col);
                                player = 0;
                                if (Player.model.detectWin() != null) {
                                    turn.setText("Player O win!");
                                    String[] positions = Player.model.detectWin();
                                    for (String pos : positions) {
                                        int x = Integer.parseInt(pos.split(",")[0]);
                                        int y = Integer.parseInt(pos.split(",")[1]);
                                        player1.setMark('2');
                                        player1.Play(x, y);
                                    }
                                    player = -1;
                                    gameActive = false;
                                } else {
                                    if (Player.model.isFull()) {
                                        turn.setText("Game over");
                                        player = -1;
                                        gameActive = false;
                                    } else {
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

    private void getImage(char[][] grid, int i, int j) {
        ImageView imageView = (ImageView) getNodeByRowColumnIndex(i, j, map);
        String imageName;
        switch (grid[i][j]) {
            case 'X', '1' -> imageName = "x_blue.png";
            case 'O', '2' -> imageName = "o_red.png";
            case '.' -> imageName = "empty.png";
            default -> {
                return;
            }
        }
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/" + imageName)).toExternalForm()));
    }

    private void resetGame() {
        Player.model.reset();
        player = 0;
        if (Player.player1 == null) {
            Player.player1 = new Player('X');
        }
        if (Player.player2 == null) {
            Player.player2 = new Player('O');
        }
        Player.player1.setMark('X');
        Player.player2.setMark('O');
        turn.setText("Player X turn");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                ImageView imageView = (ImageView) getNodeByRowColumnIndex(i, j, map);
                imageView.setImage(new Image(getURL("empty.png")));
            }
        }
        gameActive = true;
    }
}