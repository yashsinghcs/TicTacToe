package munik.androidprojects.tictactoe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;


    //turn =0:player 1 i.e  cross & if turn=1: player2  i.e criss
    public int turn = 0;
    //to determine no of grids filled
    public int[] gridFilled = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    //total no of wincases
    int[][] winCases = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    //boolean to check weather one game is over or not by seeing if sone has won or not
    Boolean Endgame = false;
    //tocalculate point of X
    int pointsx = 0;
    //to calculate point of O
    int pointso = 0;
    //view for output the one who win else blank
    TextView oneWhoWins;

    //to run when we click in grid to input the criss or crooss in the grid
    public void click(View view) {
        oneWhoWins = (TextView) findViewById(R.id.whoIsTheWinnerView);
        //to have whos turn is it showing on the app
        TextView turnview = (TextView) findViewById(R.id.turnview);
        //to get the imageview clicked
        ImageView gridClicked = (ImageView) view;
        //too get the play button visible
        Button playAgainButt = findViewById(R.id.playAgainBut);
        //to get textview of player X which will help to increase points and display
        TextView playerX = findViewById(R.id.playerX);
        //to get textview of player O which will help to increase points and display
        TextView playerO = findViewById(R.id.playerO);
        if (Endgame == false) {
            //check if any element of the grid is still empty
            if (gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] == -1) {
                //define players turn and print desired sign in desired grid of the bord
                if (turn == 0) {
                    //to set the grid imageview to criss from image resourse
                    gridClicked.setImageResource(R.drawable.cirle);
                    //to set the gridview to 0 for criss which makes track index whrere criss is mapped
                    gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] = turn;
//                    to flip the turn
                    turn = 1;
                    //to display the turn on app
                    turnview.setText("TURN=(X)");
                } else {
                    //to set the grid imageview to cross from resources
                    gridClicked.setImageResource(R.drawable.cross);
                    //to set the gridview to 1 for cross which makes track index whrere cross is mapped
                    gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] = turn;
//                    to flip the turn
                    turn = 0;
                    //to display the turn on app
                    turnview.setText("TURN=(O)");
                }
                //to check win case!!
                for (int[] onewinCase : winCases) {
                    //condition to check winning cases with indexez filled with criss or cross
                    if (gridFilled[onewinCase[0] - 1] == gridFilled[onewinCase[1] - 1] && gridFilled[onewinCase[1] - 1] == gridFilled[onewinCase[2] - 1] && gridFilled[onewinCase[0] - 1] != -1) {
                        //if someone wins , then endgame gets true
                        Endgame = true;

                        if (turn == 0) {
//                            Toast.makeText(this, "crossWON!!", Toast.LENGTH_SHORT).show();
                            oneWhoWins.setText("PLAYER (X) i.e CROSS WON!!");
//                            to increase X's point by 1'
                            pointsx += 1;
                            //increse point of player X
                            playerX.setText("PLAYER(X)=" + pointsx);
                            //too get the play button visible
                            playAgainButt.setVisibility(View.VISIBLE);
                            mediaPlayer.start();
                            mediaPlayer.setLooping(true);

                        } else {
//                            Toast.makeText(this, "crisWON!!", Toast.LENGTH_SHORT).show();
                            oneWhoWins.setText("PLAYER (O) i.e CRISS WON!!");
                            //too get the play button visible
                            playAgainButt.setVisibility(View.VISIBLE);
                            pointso += 1;
                            //increase the point of player O
                            playerO.setText("PLAYER(O)=" + pointso);
                            mediaPlayer.start();
                            mediaPlayer.setLooping(true);
                        }

                    }
                }
                //if board gets filled with this input then it gives false else true which helps to
                // determine if bord is filled and end the game round
                boolean boardFilled = false;
                for (int i : gridFilled) {
                    if (i == -1) {
                        boardFilled = true;
                        break;
                    }

                }
                //checks if bord filled is false meaning that bord is filled completely
                if (boardFilled == false) {
                    oneWhoWins.setText("NOBODY WON THIS ROUND !!!");
                    playAgainButt.setVisibility(View.VISIBLE);
                }
            } // if the grid gets filled and no one wins
            }
        }


    //    to  execute when reset button is clicked
    public void resetGameForNewPlayers(View view) {
        mediaPlayer.pause();
        //to have whos turn is it showing on the app and reset it to zero
        TextView turnview = (TextView) findViewById(R.id.turnview);
        //to get textview of player X which will help to increase points and display
        TextView playerX = findViewById(R.id.playerX);
        //to get textview of player O which will help to increase points and display
        TextView playerO = findViewById(R.id.playerO);
        turnview.setText("TURN=(O)");
//        TO SET THE PLAYER O VALUE TO 0
        playerO.setText("PLAYER(O)=0");
//        TO SET THE PLAYER X VALUE TO 0
        playerX.setText("PLAYER(X)=0");
        //set the turn to default after the game resets
        turn = 0;
        //call playAgain method to set the initial satate of the board
        playAgain(view);
    }

    //to execute when play again button is pressed
    public void playAgain(View view) {
        mediaPlayer.pause();
//        to vanish the reselt text which shows who won thw game
        oneWhoWins = (TextView) findViewById(R.id.whoIsTheWinnerView);
        oneWhoWins.setText("");
//        to get the button playagain clicked
        Button playAgainButt = findViewById(R.id.playAgainBut);
//        to make the button invisible for the new game
        playAgainButt.setVisibility(View.INVISIBLE);
//        to get the gridview
        androidx.gridlayout.widget.GridLayout gridView = findViewById(R.id.gridddy);
        //to again initialise the the content of the grids imageview to drawableImage "null"
        for (int i = 0; i < gridView.getChildCount(); i++) {
            //to get the imageview at gridview ith location
            ImageView counter = (ImageView) gridView.getChildAt(i);
            //to set the imageview to null
            counter.setImageDrawable(null);
        }
        //initialise the gridfilled for next game
        gridFilled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        //set this to false so that new game start
        Endgame = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mediaPlayer = MediaPlayer.create(this, R.raw.winsound);

    }
}
