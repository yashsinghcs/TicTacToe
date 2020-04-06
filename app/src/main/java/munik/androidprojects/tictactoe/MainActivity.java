package munik.androidprojects.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //turn =0:player 1 i.e  cross & if turn=1: player2  i.e criss
    public int turn = 0;
    public int[] gridFilled = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winCases = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    Boolean Endgame = false;

    public void click(View view) {


        ImageView gridClicked = (ImageView) view;
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
                        } else {
                            Toast.makeText(this, "crisWON!!", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            } else {
                Toast.makeText(this, "NoOneWins", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
