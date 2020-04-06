package munik.androidprojects.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public int turn = 0;
    public void click(View view) {
        int[] gridFilled = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[][] winCases = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
        //turn =0:player 1 i.e  cross & if turn=1: player2  i.e criss
        for (int freeSpaces = 0; freeSpaces < 9; freeSpaces++) {
            if (turn == 0) {
                ImageView gridClicked = (ImageView) view;
                gridClicked.setImageResource(R.drawable.cirle);
                turn = 1;
            } else {
                ImageView gridClicked = (ImageView) view;
                gridClicked.setImageResource(R.drawable.cross);
                turn = 0;
            }
            for (int[] onewinCase : winCases) {
                if (gridFilled[onewinCase[0]] == gridFilled[onewinCase[1]] && gridFilled[onewinCase[1]] == gridFilled[onewinCase[2]]) {
                    Toast.makeText(this, "SomeONeHAs WON!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
