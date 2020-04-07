package munik.androidprojects.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
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
                    turn = 1;
                    turnview.setText("TURN=(X)");
                } else {
                    //to set the grid imageview to cross from resources
                    gridClicked.setImageResource(R.drawable.cross);
                    //to set the gridview to 1 for cross which makes track index whrere cross is mapped
                    gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] = turn;
                    turn = 0;
                    turnview.setText("TURN=(O)");
                }
                //to check win case!!
                for (int[] onewinCase : winCases) {
                    //condition to check winning cases with indexez filled with criss or cross
                    if (gridFilled[onewinCase[0] - 1] == gridFilled[onewinCase[1] - 1] && gridFilled[onewinCase[1] - 1] == gridFilled[onewinCase[2] - 1] && gridFilled[onewinCase[0] - 1] != -1) {
                        Endgame = true;
                        if (turn == 0) {
                            Toast.makeText(this, "crossWON!!", Toast.LENGTH_SHORT).show();
                            oneWhoWins.setText("PLAYER (X) i.e CROSS WON!!");
                            pointsx += 1;
                            //increse point of player X
                            playerX.setText("PLAYER(X)=" + pointsx);
                            //too get the play button visible
                            playAgainButt.setVisibility(View.VISIBLE);

                        } else {
                            Toast.makeText(this, "crisWON!!", Toast.LENGTH_SHORT).show();
                            oneWhoWins.setText("PLAYER (O) i.e CRISS WON!!");
                            //too get the play button visible
                            playAgainButt.setVisibility(View.VISIBLE);
                            pointso += 1;
                            //increase the point of player O
                            playerO.setText("PLAYER(O)=" + pointso);
                        }

                    }
                }
            } // if the grid gets filled and no one wins
            else {
                Toast.makeText(this, "NoOneWins", Toast.LENGTH_SHORT).show();
                //too get the play button visible
                playAgainButt.setVisibility(View.VISIBLE);
            }
        }
    }


    //    to  execute when reset button is clicked
    public void resetGameForNewPlayers(View view) {
        //to have whos turn is it showing on the app and reset it to zero
        TextView turnview = (TextView) findViewById(R.id.turnview);
        //to get textview of player X which will help to increase points and display
        TextView playerX = findViewById(R.id.playerX);
        //to get textview of player O which will help to increase points and display
        TextView playerO = findViewById(R.id.playerO);
        turnview.setText("TURN=(O)");
//        TO SET THE PLAYER X VALUE TO 0
        playerO.setText("PLAYER(O)=0");
//        TO SET THE PLAYER O VALUE TO 0
        playerX.setText("PLAYER(O)=0");
        playAgain(view);


    }

    //to execute when play again button is pressed
    public void playAgain(View view) {
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
    }
}
