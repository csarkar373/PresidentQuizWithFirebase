package com.westhillcs.presidentquiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    private static final String FB_TOKEN = "jTib6PCMSWf02qjDTrMRdHJpvbN7a5H45zUypG1a";

    private static Firebase fb;

    private static ImageView iv_picture;
    private static RadioGroup rg_choices;
    private static RadioButton rb_selected;

    private static RadioButton rb_choiceA;
    private static RadioButton rb_choiceB;
    private static RadioButton rb_choiceC;
    private static TextView tv_question;

    private static Button b_submit;

    private int currentQuestionIndex;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.initialize();
    }

    private void initialize() {

        iv_picture = (ImageView)findViewById(R.id.picture_iv);
        rg_choices = (RadioGroup)findViewById(R.id.choices_rg);
        rb_choiceA = (RadioButton)findViewById(R.id.a_rb);
        rb_choiceB = (RadioButton)findViewById(R.id.b_rb);
        rb_choiceC = (RadioButton)findViewById(R.id.c_rb);
        tv_question = (TextView)findViewById(R.id.question_tv);

        b_submit = (Button)findViewById(R.id.submit_b);

        Firebase.setAndroidContext(this);
        fb = new Firebase("https://presidents-quiz-android-studio.firebaseio.com/questions");
        fb.authWithCustomToken(FB_TOKEN, null);


        fb.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                questions = new ArrayList<Question>();

                for (DataSnapshot q : snapshot.getChildren())  {

                    questions.add( q.getValue(Question.class));
                }

                currentQuestionIndex = 0;
                displayQuestion(  currentQuestionIndex );
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // not used
            }
        });



        /*
        questions = new ArrayList<Question>();

        questions.add(new Question(R.mipmap.rooschurch, "Which president is shown here seated next to Churchill?",
                "Eisenhower", "F.D. Roosevelt", "Nixon", 'b'));
        questions.add(new Question(R.mipmap.nixon, "Who is the Watergate president?",
                "Carter", "Reagan", "Nixon", 'c'));
        questions.add(new Question(R.mipmap.carterchina, "Which president shown here help normalize relations with China?",
                "Carter", "F.D. Roosevelt", "Nixon", 'a'));
        questions.add(new Question(R.mipmap.atomic, "Which president approved the use of the A-bomb?",
                "Truman", "F.D. Roosevelt", "Kennedy", 'a'));
                */



        b_submit.setOnClickListener(
           new View.OnClickListener(){
               @Override
               public void onClick(View v) {
                   if (this.answerIsRight()) {
                       Toast.makeText(getApplicationContext(), "Right!", Toast.LENGTH_SHORT).show();
                       advance();
                   } else {
                       Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
                   }
               }

               private boolean answerIsRight() {
                   String answer = "x";
                   int id = rg_choices.getCheckedRadioButtonId();
                   rb_selected = (RadioButton)findViewById(id);
                   if (rb_selected == rb_choiceA) answer = "a";
                   if (rb_selected == rb_choiceB) answer = "b";
                   if (rb_selected == rb_choiceC) answer = "c";
                   return questions.get(currentQuestionIndex).isCorrectAnswer(answer);
               }
           }

        );

    }

    private void displayQuestion( int index ) {

        // iv_picture.setImageResource( questions.get(currentQuestionIndex).getPictureID());
        String url = questions.get(currentQuestionIndex).getUrl();
        Ion.with(iv_picture).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).load( url );

        tv_question.setText( questions.get(currentQuestionIndex).getQuestionText());
        rb_choiceA.setText( questions.get(currentQuestionIndex).getChoiceA());
        rb_choiceB.setText( questions.get(currentQuestionIndex).getChoiceB());
        rb_choiceC.setText( questions.get(currentQuestionIndex).getChoiceC());
        rg_choices.clearCheck();
    }

    private void advance() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        displayQuestion(currentQuestionIndex);
    }








}

