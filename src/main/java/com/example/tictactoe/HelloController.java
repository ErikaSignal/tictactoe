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
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    Player player1 = new Player('X');
    Player player2 = new Player('O');
    private static final int PLAYER_X = 0;
    private static final int PLAYER_O = 1;
    @FXML
    private GridPane map;
    @FXML
    private Label turn;
    @FXML
    private Button replayButton;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;

    private static final int gridSize = 3;
    private boolean gameActive = true;

    static int currentPlayer = PLAYER_X;
    static int player1score = 0;
    static int player2score = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //initializes the game board, sets up event handlers, and sets the initial turn to Player X
        Player.model = new Model();

        replayButton.setOnAction(event -> resetGame());

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int[] col = {i};
                final int[] row = {j};
                imageView.setOnMouseClicked(evt -> {
                    if (handlePlayerClick(row, col)) return;
                    updateUI(Player.model.grid);
                });
                map.setHgap(10);
                map.setVgap(10);
                map.add(imageView, i, j);
            }
        }
        turn.setText("Player X turn");
    }

    private boolean handlePlayerClick(int[] row, int[] col) { //makes the player/computer moves and updates the score if there is a win
        if (!gameActive) {
            return true;
        }
        if (currentPlayer == PLAYER_X) {
            if (Player.model.isEmpty(row[0], col[0])) {
                playerMove(row[0], col[0]);
                player1score = playerCheck(player1, player1score);
                player1Score.setText("Player X score: " + player1score);
            }
            if(currentPlayer == PLAYER_O && gameActive){
                computerMove();
                player2score = playerCheck(player2, player2score);
                player2Score.setText("Player O score: " + player2score);
            }
        }
        return false;
    }

    private int playerCheck(Player player, int playerScore) { //checks for who's turn it is and prints it out. Also checks for win or game over
        char playerSymbol = player.mark;
        if (Player.model.detectWin() != null) {
            turn.setText("Player " + playerSymbol + " wins!");
            detectWin(player);
            playerScore++;
            gameActive = false;
        } else if (Player.model.isFull()) {
            gameOver();
        } else {
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            turn.setText("Player " + (currentPlayer == PLAYER_X ? "X" : "O") + " turn");
        }
        return playerScore;
    }

    private void gameOver() { //updates the UI to indicate that the game is over
        turn.setText("Game over");
        gameActive = false;
    }

    private void playerMove(int row, int col) { //handles the player's move, updates the model, and switches to Player O's turn
        if (Player.model.isEmpty(row, col)) {
            player1.Play(row, col);
        }
    }

    private void computerMove() { //handles computer's move by randomly selecting an empty position and updates the model. Switches to Player X's turn
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(gridSize);
            col = random.nextInt(gridSize);
        } while (!Player.model.isEmpty(row, col));
        player2.Play(row, col);
    }

    private void detectWin(Player player1) { //detects the winning sequence and updates the UI accordingly. Ends the game
        String[] positions = Player.model.detectWin();
        for (String pos : positions) {
            int x = Integer.parseInt(pos.split(",")[0]);
            int y = Integer.parseInt(pos.split(",")[1]);
            player1.Play(x, y);
        }
        gameActive = false;
    }

    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridpane){ //retrieves the node (ImageView) at the specified row and column in the GridPane
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

    public String getURL(String name){ //constructs a file URL for the image file based on its name
        File file = new File("src/main/resources/com/example/tictactoe/images/" + name);
        try {
            return file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateUI(char[][] grid){ //updates the UI based on the current state of the game grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                getImage(grid, i, j);
            }
        }
    }

    private void getImage(char[][] grid, int i, int j) { //updates the image in the UI based on the character in the grid at position
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

    private void resetGame() { //resets the game by clearing the grid, updating the UI, and resetting game-related variables
        Player.model.reset();
        currentPlayer = PLAYER_X;
        turn.setText("Player X turn");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                ImageView imageView = (ImageView) getNodeByRowColumnIndex(i, j, map);
                imageView.setImage(new Image(getURL("empty.png")));
            }
        }
        gameActive = true;
    }
}