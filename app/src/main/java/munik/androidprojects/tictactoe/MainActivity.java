package munik.androidprojects.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public void click(View view) {
        Boolean[] gridFilled = {false, false, false, false, false, false, false, false, false};
        int[][] winCases = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
        ImageView gridClicked = (ImageView) view;
        gridClicked.setImageResource(R.drawable.cirle);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
