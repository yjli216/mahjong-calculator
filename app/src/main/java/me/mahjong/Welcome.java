package me.mahjong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void enter(View view) {

        EditText players[] = {
                (EditText) findViewById(R.id.player1),
                (EditText) findViewById(R.id.player2),
                (EditText) findViewById(R.id.player3),
                (EditText) findViewById(R.id.player4)
        };

        String p[] = new String[4];
        for (int i = 0; i < players.length; i++) {
            p[i] = players[i].getText().toString();
            if (p[i].equals("")) {
                p[i] = "Player " + (i + 1);
            }
        }

        //get max tai
        EditText edTai = (EditText) findViewById(R.id.tai);
        String t = edTai.getText().toString();
        if (t.trim().equals("")) t = "5";

        //get the increments
        EditText edIncr = (EditText) findViewById(R.id.incr);
        String i = edIncr.getText().toString();
        if (i.trim().equals("")) i = "0.1";


        Toast.makeText(Welcome.this, "Clicked", Toast.LENGTH_LONG).show();
//
        Intent gamePage = new Intent();
        gamePage.setClass(this, GamePage.class);
        gamePage.putExtra("playernames", p);
        gamePage.putExtra("tai", Integer.parseInt(t));
        gamePage.putExtra("incr", Double.parseDouble(i));
        startActivity(gamePage);
//        finish();
    }
}
