package com.example.factattack;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private TextView currentPoints;
    private Button answer1btn;
    private Button answer2btn;
    private Button answer3btn;
    private Button answer4btn;
    private Button fiftyfifty;
    private Button giveAnswerbtn;
    private String rightAnswer;
    private String otherChoice;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    private int pointCount = 0;
    static final private int QUIZ_COUNT = 10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            //{"Question", "Right Answer, "Choice1", "Choice2", "Choice3"}
            {"How many colors are there in a rainbow?", "7", "5", "13", "8"},
            {"What so you call a span of one hundred years?", "Millennium", "Decade", "Eon", "Century"},
            {"How many degrees are in a circle?", "360", "45", "90",  "180"},
            {"What do you call the dessert made with ice cream and cake topped with meringue?", "Baked Alaska", "Eclair", "Donut", "Ice Cream Cake"},
            {"How many strings does a cello have?", "4", "5", "6", "8"},
            {"Who wrote The Grapes of Wrath?", "John Steinbeck", "J.K. Rowling", "George RR Martin", "J.R.R. Tolkien"},
            {"According to Greek mythology, who stole fire for mankind's benefit?", "Prometheus", "Zeus", "Atlas", "Persephone"},
            {"How many lines does a limerick have?", "5", "3", "4", "7"},
            {"In Portugal, what object is traditional thrown at friends in celebration of April Fool's Day?", "Flour", "Salt", "Wine", "Tomato"},
            {"What is the only snake in the world that builds nest for its eggs?", "King Cobra", "Python", "Anaconda", "Coral"},
            {"Which gland in the human body regulates metabolism?", "Thyroid", "Pituitary", "Pineal", "Adrenal"},
            {"Which famous toy manufacturer is also the world's largest tire manufacturer by units produced?", "Lego", "Mattel", "Bandai", "Nerf"},
            {"How many moons does Jupiter have?", "67", "4", "15", "86"},
            {"How many states are there in the United States of America?", "50", "34", "49", "52"},
            {"Where is the smallest bone in the body?", "Ear", "Hand", "Foot", "Spine"},
            {"Who painted the Mona Lisa?", "Da Vinci", "Van Gogh", "Rembrandt", "Picaso"},
            {"In what country does the Bullet Train run?", "Japan", "China", "France", "Canada"},
            {"What is Earth's largest continent?", "Asia", "Africa", "Europe", "Antarctica"},
            {"What country can you visit Machu Pichu?", "Peru", "Columbia", "Chile", "Bolivia"},
            {"What type of flowers produce vanilla pods", "Orchids", "Lilies", "Roses", "Chrysanthemum"},

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        currentPoints = (TextView)findViewById(R.id.currentPoints);
        answer1btn = (Button)findViewById(R.id.answer1btn);
        answer2btn = (Button)findViewById(R.id.answer2btn);
        answer3btn = (Button)findViewById(R.id.answer3btn);
        answer4btn = (Button)findViewById(R.id.answer4btn);
        fiftyfifty = (Button)findViewById(R.id.fiftyfiftybtn);
        giveAnswerbtn = (Button)findViewById(R.id.giveAnswerbtn);
        //Array for quizData
        for (int i = 0; i < 10; i++) {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Question
            tmpArray.add(quizData[i][1]); //Right Answer
            tmpArray.add(quizData[i][2]); //Choice 1
            tmpArray.add(quizData[i][3]); //Choice 2
            tmpArray.add(quizData[i][4]); //Choice 3

            quizArray.add(tmpArray);
        }
        showNextQuestion();
    }
    public void showNextQuestion() {

        countLabel.setText("Q" + quizCount);
        currentPoints.setText("Points: " + pointCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);
        //Set question and right answer
        //Array format: {"Question", "Right Answer, "Choice1", "Choice2", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);
        otherChoice = quiz.get(2);
        //Remove "Question" from quiz and shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);
        //Set choices
        answer1btn.setText(quiz.get(0));
        answer2btn.setText(quiz.get(1));
        answer3btn.setText(quiz.get(2));
        answer4btn.setText(quiz.get(3));

        quizArray.remove(randomNum);

        fiftyfifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pointCount >= 1) {
                    pointCount--;
                    currentPoints.setText("Points: " + pointCount);
                    Toast.makeText(QuizActivity.this, "The answer is " + rightAnswer + " or " + otherChoice, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(QuizActivity.this, R.string.no_points, Toast.LENGTH_SHORT).show();
                }
            }
        });
        giveAnswerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pointCount >= 3){
                    pointCount-= 3;
                    currentPoints.setText("Points: " + pointCount);
                    Toast.makeText(QuizActivity.this, "The answer is " + rightAnswer, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(QuizActivity.this, R.string.no_points, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void checkAnswer(View view) {
        //Get pushed button
        Button answerbtn = (Button) findViewById(view.getId());
        String btnText = answerbtn.getText().toString();

        String alertToast;

        if (btnText.equals(rightAnswer)) {
            alertToast = "Correct!";
            rightAnswerCount++;
            pointCount++;
        }
        else {
            alertToast = "Wrong!";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertToast);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (quizCount == QUIZ_COUNT) {
                    //Show result
                    Intent intent = new Intent(getApplicationContext(), result.class);
                    intent.putExtra("SCORE", pointCount);
                    startActivity(intent);
                }
                else {
                    quizCount++;
                    showNextQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
