package com.example.tictactoeonline;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Offlineactivity extends AppCompatActivity implements View.OnClickListener{
    private TextView score1, score2, status;
    private final Button[] buttons = new Button[9];
    private Button resetgame;
    private int count1, counnt2, rountcount;
    boolean active;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] win = {{0,1,2}, {3,4,5} ,{6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        score1 = findViewById(R.id.playeronescore);
        score2 = findViewById(R.id.playertwoscore);
        status = findViewById(R.id.playerstatus);
        resetgame = findViewById(R.id.reset);
        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        buttons[4] = findViewById(R.id.btn4);
        buttons[5] = findViewById(R.id.btn5);
        buttons[6] = findViewById(R.id.btn6);
        buttons[7] = findViewById(R.id.btn7);
        buttons[8] = findViewById(R.id.btn8);
        buttons[0].setOnClickListener(this);
        buttons[1].setOnClickListener(this);
        buttons[2].setOnClickListener(this);
        buttons[3].setOnClickListener(this);
        buttons[4].setOnClickListener(this);
        buttons[5].setOnClickListener(this);
        buttons[6].setOnClickListener(this);
        buttons[7].setOnClickListener(this);
        buttons[8].setOnClickListener(this);
        rountcount = 0;
        count1 = 0;
        counnt2 = 0;
        active = true;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonId = v.getResources().getResourceEntryName(v.getId());
        int gamestatepointer = Integer.parseInt(buttonId.substring(buttonId.length() -1));
        if(active){
            ((Button) v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            gamestate[gamestatepointer] = 0;
        }
        else{
            ((Button) v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            gamestate[gamestatepointer] = 1;
        }
        rountcount++;
        if(checkWinner()){
            if(active){
                count1++;
                updateplayerscore();
                Toast.makeText(this, "Player One Won!", Toast.LENGTH_SHORT).show();
            }
            else {
                counnt2++;
                updateplayerscore();
                Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();
            }
            playAgain();
            if(count1 > counnt2){
                status.setText("Player One is Winning!");
            }
            else if(count1 < counnt2){
                status.setText("Player Two is Winning!");
            }
            else {
                status.setText("");
            }
        }
        else if (rountcount == 9){
            playAgain();
            Toast.makeText(this, "No Winner!!", Toast.LENGTH_SHORT).show();
        }
        else{
            active = !active;
        }
        resetgame.setOnClickListener(v1 -> {
            playAgain();
            count1 = 0;
            counnt2 = 0;
            status.setText("");
            updateplayerscore();
        });
    }
    public boolean checkWinner(){
        boolean winnerresult = false;
        for(int[] winposition : win){
            if (gamestate[winposition[0]] == gamestate[winposition[1]] && gamestate[winposition[1]] == gamestate[winposition[2]] && gamestate[winposition[0]] != 2) {
                winnerresult = true;
                break;
            }
        }
        return winnerresult;
    }
    @SuppressLint("SetTextI18n")
    public void updateplayerscore(){
        score1.setText(Integer.toString(count1));
        score2.setText((Integer.toString(counnt2)));
    }
    public void playAgain(){
        rountcount = 0;
        active = true;
        for (int i = 0; i < buttons.length; i++) {
            gamestate[i] = 2;
            buttons[i].setText("");
        }
    }
}
