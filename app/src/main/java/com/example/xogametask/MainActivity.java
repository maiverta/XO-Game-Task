package com.example.xogametask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int playerTurn = 1;
    boolean isActiveGame= true;
    // 1-O 2-X 0-empty
    int[] gameState= {0,0,0,0,0,0,0,0,0};
    int[][] gameWinIndexes = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int checkWin(){
        for (int gameWinIndex = 0; gameWinIndex < gameWinIndexes.length; gameWinIndex++) {
            int[] winStateIndexes = gameWinIndexes[gameWinIndex];
            // The values of the winStateIndexes are indexes that in the gameState
            // need to be checked if their values at these indexes are equals
            // If the values are equals and not 0(empty) its a win to O-1 / X-2
            if (gameState[winStateIndexes[0]] != 0 && gameState[winStateIndexes[0]] == gameState[winStateIndexes[1]]
                    && gameState[winStateIndexes[1]] == gameState[winStateIndexes[2]]) {
                showWinLine(gameWinIndex);
                return gameState[winStateIndexes[0]];
            }
        }
        return -1;
    }

    public void playAgain(View view) {
        isActiveGame = true;
        playerTurn = 1;
        Arrays.fill(gameState, 0);
        // replace all images of x/o to empty
        ((ImageView) findViewById(R.id.main_image_0)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_1)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_2)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_3)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_4)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_5)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_6)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_7)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.main_image_8)).setImageResource(R.drawable.empty);

        ((ImageView) findViewById(R.id.main_image_player_status)).setImageResource(R.drawable.xplay);
        (findViewById(R.id.main_play_again)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.empty);

    }

    public void showWinLine(int winIndex){
        switch(winIndex) {
            case 0:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark3);
                break;
            case 1:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark4);
                break;
            case 2:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark5);
                break;
            case 3:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark6);
                break;
            case 4:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark7);
                break;
            case 5:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark8);
                break;
            case 6:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark1);
                break;
            case 7:
                ((ImageView) findViewById(R.id.main_winning_line)).setImageResource(R.drawable.mark2);
                break;
            default:
        }
    }
    public void playTurn(View view) {
        ImageView img = (ImageView) view;

        int clickedImageIndex= Integer.parseInt(img.getTag().toString());
        if(!isActiveGame || gameState[clickedImageIndex] != 0) return;
        if(playerTurn % 2 == 0) {
            gameState[clickedImageIndex]= 1;
            img.setImageResource(R.drawable.o);
            ((ImageView) findViewById(R.id.main_image_player_status)).setImageResource(R.drawable.xplay);

        }
        else {
            gameState[clickedImageIndex]= 2;
            img.setImageResource(R.drawable.x);
            ((ImageView) findViewById(R.id.main_image_player_status)).setImageResource(R.drawable.oplay);
        }
        playerTurn++;

        int winIndex = checkWin();
        if(winIndex != -1 || playerTurn == 10){
            isActiveGame = false;
            findViewById(R.id.main_play_again).setVisibility(View.VISIBLE);
        }
        if(winIndex == 1){
            ((ImageView) findViewById(R.id.main_image_player_status)).setImageResource(R.drawable.owin);
        } else if(winIndex == 2){
            ((ImageView) findViewById(R.id.main_image_player_status)).setImageResource(R.drawable.xwin);
        }
         else if(playerTurn == 10){
            ((ImageView) findViewById(R.id.main_image_player_status)).setImageResource(R.drawable.nowin);
         }
    }
}