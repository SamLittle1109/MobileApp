package com.example.lit15146664.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    //variables
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;

    private List<QuestionObject> questions;

    private QuestionObject currentQuestion;
    private int index;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connecting the variables to interface items
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        //set question text
        lblQuestion.setText("Is my name Samuel?");

        //set image picture
        imgPicture.setImageResource(R.drawable.cat);

        index = 0;
        score = 0;

        //onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                determineButtonPress(false);

            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                determineButtonPress(true);
            }
        });

        generateQuestions();

        setUpQuestion();

        endGame();

        Paper.init(this);
    }

    private void generateQuestions(){
        questions = new ArrayList<>();

        questions.add(new QuestionObject("Is Paris the capital of France?", true, R.drawable.skype));
        questions.add(new QuestionObject("Does the metro smell?", true, R.drawable.skype));
        questions.add(new QuestionObject("Are french people nice?", false, R.drawable.skype));
        questions.add(new QuestionObject("Can I speak french?", true, R.drawable.skype));
        questions.add(new QuestionObject("Is 10 an even number?", true, R.drawable.skype));
        questions.add(new QuestionObject("Can you count past 5?", false, R.drawable.skype));
        questions.add(new QuestionObject("Is Paris the capital of France?", true, R.drawable.skype));
        questions.add(new QuestionObject("Is Paris the capital of France?", true, R.drawable.skype));
        questions.add(new QuestionObject("Is Paris the capital of France?", true, R.drawable.skype));
        questions.add(new QuestionObject("Is Paris the capital of France?", true, R.drawable.skype));

    }

    private void setUpQuestion() {

        if (index == questions.size()) {

            Log.d("SAMUEL_APP", "Ended all questions");

        } else {


            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());

            index++;
        }
    }



    private void determineButtonPress(boolean answer){

        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer){
            //Correct Answer

            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            //else Wrong Answer

            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestion();

    }

    private void endGame(){

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations!")
                .setMessage("You scored " + score + "points!")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //new high score
                        ScoreObject highscore = new ScoreObject("sameul", score, new Date().getTime());

                        //user pref
                        List<ScoreObject> highscores = Paper.book().read("Highscores", new ArrayList<ScoreObject>());

                        //add item
                        highscores.add(highscore);

                        //save again
                        Paper.book().write("Highscores", highscores);


                        finish();

                    }
                })

            .create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
