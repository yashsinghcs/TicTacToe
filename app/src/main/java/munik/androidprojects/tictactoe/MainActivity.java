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
    public int[] gridFilled = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winCases = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    Boolean Endgame = false;
    int pointsx = 0;
    int pointso = 0;
    public void click(View view) {
        ImageView gridClicked = (ImageView) view;
        Button playAgainButt = (Button) findViewById(R.id.playAgainBut);
        TextView playerX = (TextView) findViewById(R.id.playerX);
        TextView playerO = (TextView) findViewById(R.id.playerO);
        if (Endgame == false) {
            if (gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] == -1) {
                //define players turn and prind desired sign in desired grid of the bord
                if (turn == 0) {
                    gridClicked.setImageResource(R.drawable.cirle);
                    gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] = turn;
                    turn = 1;
                } else {
                    gridClicked.setImageResource(R.drawable.cross);
                    gridFilled[Integer.parseInt(gridClicked.getTag().toString()) - 1] = turn;
                    turn = 0;
                }
                //to check win case!!
                for (int[] onewinCase : winCases) {
                    if (gridFilled[onewinCase[0] - 1] == gridFilled[onewinCase[1] - 1] && gridFilled[onewinCase[1] - 1] == gridFilled[onewinCase[2] - 1] && gridFilled[onewinCase[0] - 1] != -1) {
                        Endgame = true;

                        if (turn == 0) {
                            Toast.makeText(this, "crossWON!!", Toast.LENGTH_SHORT).show();
                            pointsx += 1;
                            playerX.setText("PLAYER(X)=" + pointsx);
                            playAgainButt.setVisibility(View.VISIBLE);

                        } else {
                            Toast.makeText(this, "crisWON!!", Toast.LENGTH_SHORT).show();
                            playAgainButt.setVisibility(View.VISIBLE);
                            pointso += 1;
                            playerO.setText("PLAYER(O)=" + pointso);


                        }

                    }
                }
            } else {
                Toast.makeText(this, "NoOneWins", Toast.LENGTH_SHORT).show();
                playAgainButt.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButt = (Button) findViewById(R.id.playAgainBut);
        playAgainButt.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridView = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridddy);
        for (int i = 0; i < gridView.getChildCount(); i++) {
            ImageView counter = (ImageView) gridView.getChildAt(i);
            counter.setImageDrawable(null);
        }
        gridFilled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        Endgame = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
