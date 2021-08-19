package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    Button playagainbutton;
    int activeplayer=0;//variable to determine which player's turn it is.
    boolean activegamestate=true;//variable to check if the game is still active or not
    int[] gamestate={2,2,2,2,2,2,2,2,2};//an array to store initial values which will be changed to 0 or 1 during the game
    int[][] winningpositions={{3,4,5},{0,1,2},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};//declaring all the possible winning positions of the game in a double dimensional array.
    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && activegamestate) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.black);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);


            for (int[] winningPosition : winningpositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2)//checking if someone has won.
                {

                    activegamestate = false;
                    String winner = "'O'";
                    if (gamestate[winningPosition[0]] == 0) {

                        winner = "'X'";
                    }
                    TextView winnermessage = (TextView) findViewById(R.id.winnermessage);
                    winnermessage.setText(winner + " WINS");
                    LinearLayout playagainlayout = (LinearLayout) findViewById((R.id.playagainlayout));
                    playagainlayout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameisover = true;
                    for (int counterstate : gamestate) {
                        if (counterstate == 2) {
                            gameisover = false;
                        }
                    }
                    if (gameisover) {
                        TextView winnermessage = (TextView) findViewById(R.id.winnermessage);
                        winnermessage.setText("IT'S A DRAW");
                        LinearLayout playagainlayout = (LinearLayout) findViewById((R.id.playagainlayout));
                        playagainlayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

        public void playagain (View view)
        {
            activegamestate = true;
            LinearLayout playagainlayout = (LinearLayout) findViewById((R.id.playagainlayout));
            playagainlayout.setVisibility(View.INVISIBLE);
            activeplayer = 0;
            for (int i = 0; i < gamestate.length; i++) {
                gamestate[i] = 2;
            }
            GridLayout gridlayout = (GridLayout) findViewById(R.id.gridlayout);
            for (int i = 0; i < gridlayout.getChildCount(); i++) {
                ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
            }
        }

        /*The game starts when the user taps on any one of the image views in the blank spaces.
    When the image view of that particular image view is tapped ,it calls the dropin function.
    When the control enters the dropin function, it gets the tag of the imageview and sets the imageview to either O or X depending upon
    the activeplayer. Here I have added some animation to set the image ( imageview is first taken up by translation, set to the respective image and then dropped in again to the same position)
    After setting the image the activeplayer changes to 1. Then the second player tap another imageview and the same process goes on.
    The app also checks for the winner each time the player taps. The checking is done using a for loop. The loop contains an array 'winningPosition'
    which loops through the mini arrays of 'winningpositions' and tallies it with the positions of the gamestate. If all the three positions of the gamestate are equal and is not equal to 2, that means either one of the player has won.
    Then we can simply check for the gamestate value for 0 or 1, set the playagainlayout to visible and display the appropriate message (either 'X' WINS or 'O' WINS). If the gamestate values are not equal then
    display 'ITS A DRAW'.
    The last part of the code if the playagain function in which all the variables are reset to its initial values (including the arrays and the imageviews).
    The playagainlayout is also set to invisible.
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playagainbutton=(Button)findViewById(R.id.playagainbutton);
    }
}
