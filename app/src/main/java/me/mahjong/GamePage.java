package me.mahjong;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class GamePage extends AppCompatActivity {


    private Game g;
    private String[] p;
    private int winnerid = -1;
    private int loserid = -1;
    private int tai = 0;
    private boolean zimou = false;
    TextView text[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        // get the Intent which caused the start of this activity
        Intent caller = getIntent();
        //get player names
        p = caller.getStringArrayExtra("playernames");

        int tai = caller.getIntExtra("tai", 5);
        double incr = caller.getDoubleExtra("incr", 0.10);

        //create Game
        g = new Game(p, tai, incr);

        //set boxes with names
        text = new TextView[] {
                (TextView) findViewById(R.id.pl1),
                (TextView) findViewById(R.id.pl2),
                (TextView) findViewById(R.id.pl3),
                (TextView) findViewById(R.id.pl4)
        };

        updateDisplay();

        //set text of winning buttons
        TextView playersTV[] = {
                (TextView) findViewById(R.id.p1wins),
                (TextView) findViewById(R.id.p2wins),
                (TextView) findViewById(R.id.p3wins),
                (TextView) findViewById(R.id.p4wins),
                (TextView) findViewById(R.id.p1lose),
                (TextView) findViewById(R.id.p2lose),
                (TextView) findViewById(R.id.p3lose),
                (TextView) findViewById(R.id.p4lose)
        };

        for (int i = 0; i < playersTV.length; i++) {
            playersTV[i].setText(p[i % 4]);
        }

        // For zimou switch button
        Switch switchButton = (Switch) findViewById(R.id.zimou);

        switchButton.setChecked(false);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    zimou = true;
                    Toast.makeText(GamePage.this, "zimou", Toast.LENGTH_SHORT).show();
                } else {
                    zimou = false;
                    Toast.makeText(GamePage.this, "not zimou", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void winnerButton(View view) {
        boolean winner = ((RadioButton) view).isChecked();
        //check which radio button was clicked
        switch(view.getId()) {
            case R.id.p1wins:
                if (winner) winnerid = 0;
                break;
            case R.id.p2wins:
                if (winner) winnerid = 1;
                break;
            case R.id.p3wins:
                if (winner) winnerid = 2;
                break;
            case R.id.p4wins:
                if (winner) winnerid = 3;
                break;
            default:
                winnerid = -1;
                break;
        }
    }

    public void loserButton(View view) {
        boolean loser = ((RadioButton) view).isChecked();
        //check which radio button was clicked
        switch(view.getId()) {
            case R.id.p1lose:
                if (loser) loserid = 0;
                break;
            case R.id.p2lose:
                if (loser) loserid = 1;
                break;
            case R.id.p3lose:
                if (loser) loserid = 2;
                break;
            case R.id.p4lose:
                if (loser) loserid = 3;
                break;
            default:
                loserid = -1;
                break;
        }
    }

    public void p1quick(View view) {
        g.updateGameState(0, loserid, true, 0);
        updateDisplay();
    }

    public void p2quick(View view) {
        g.updateGameState(1, loserid, true, 0);
        updateDisplay();
    }

    public void p3quick(View view) {
        g.updateGameState(2, loserid, true, 0);
        updateDisplay();
    }

    public void p4quick(View view) {
        g.updateGameState(3, loserid, true, 0);
        updateDisplay();
    }

    public void updateDisplay() {
        //set the text on top of the screen
        DecimalFormat df = new DecimalFormat("##.##");
        for (int i = 0; i < text.length; i++) {
            text[i].setText(p[i] + ": " + df.format(g.getMoney(i)));
        }
    }


    public void updateMoney(View view) {
        //update Tai
        EditText ET = (EditText) findViewById(R.id.taiswon);
        String temp = ET.getText().toString();

        if (temp == null) {
            Toast.makeText(GamePage.this, "Enter Tai", Toast.LENGTH_SHORT).show();
        } else if (temp.length() == 0) {
            Toast.makeText(GamePage.this, "Invalid Tai", Toast.LENGTH_SHORT).show();
        } else tai = Integer.parseInt(temp);


        if (winnerid == -1) {
            Toast.makeText(GamePage.this, "Set Winner", Toast.LENGTH_SHORT).show();
            return;
        } else if (loserid == -1 && !zimou) {
            Toast.makeText(GamePage.this, "Set Loser", Toast.LENGTH_SHORT).show();
            return;
        } else if (loserid == winnerid && loserid != -1) {
            Toast.makeText(GamePage.this, "Winner cannot be loser", Toast.LENGTH_SHORT).show();
            return;
        } if (tai < 1) {
            Toast.makeText(GamePage.this, "Invalid Tai", Toast.LENGTH_SHORT).show();
            return;
        }
        g.updateGameState(winnerid, loserid, zimou, tai);
        updateDisplay();
    }
}
