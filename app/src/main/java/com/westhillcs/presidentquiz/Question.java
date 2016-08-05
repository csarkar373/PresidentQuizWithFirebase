package com.westhillcs.presidentquiz;

/**
 * Created by Chandan on 5/12/2016.
 */
public class Question {

    private int pictureID;
    private String url;
    private String questionText;
    private String choiceA;
    private String choiceB;
    private String choiceC;

    private String correctAnswer;
    private boolean creditAlreadyGiven;

    public Question() {
        // empty constructor needed by Firebase deserializer
    }


    public Question(int pictureID, String questionText, String choiceA, String choiceB, String choiceC, String correctAnswer) {
        this.pictureID = pictureID;
        this.questionText = questionText;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.correctAnswer = correctAnswer;
        // intially, no credit has been given for this question
        this.creditAlreadyGiven = false;
    }

    public String getUrl() {        return url;    }

    public void setUrl(String url) {        this.url = url;    }

    public int getPictureID() {
        return pictureID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCreditAlreadyGiven() {
        return creditAlreadyGiven;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    public boolean isCorrectAnswer(String selectedAnswer) {
        return (selectedAnswer.equals(correctAnswer));
    }

    @Override
    public String toString() {
        return questionText;
    }

}
