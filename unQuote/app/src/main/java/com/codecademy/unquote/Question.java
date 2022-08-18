package com.codecademy.unquote;

/**
 * Class to create question objects to be used in the quiz application
 */
public class Question {
    private int imageId;
    private String questionText;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;
    private int correctAnswer;
    private int playerAnswer;

    //Question constructor
    public Question(int imageIdentifier,
                    String questionString,
                    String answerZero,
                    String answerOne,
                    String answerTwo,
                    String answerThree,
                    int correctAnswerIndex) {
        imageId = imageIdentifier;
        questionText = questionString;
        answer0 = answerZero;
        answer1 = answerOne;
        answer2 = answerTwo;
        answer3 = answerThree;
        correctAnswer = correctAnswerIndex;
        playerAnswer = -1; //default answer, this will be modified by user
    }

    /**
     * Returns true if player answer is the same as the correct answer
     * @return boolean value to indicate correct answer
     */
    public boolean isCorrect() {
        return correctAnswer == playerAnswer;
    }

    /**
     * Accessor method to retrieve the question image
     * @return the image id for where the related image is located in the file structure
     */
    public int getImageId() {
        return imageId;
    }
    /**
     * Accessor method to retrieve the question text?
     * @return question text string
     */
    public String getQuestionText() {
        return questionText;
    }
    /**
     * Accessor method to retrieve the text for answer 0
     * @return the answer0 String
     */
    public String getAnswer0() {
        return answer0;
    }
    /**
     * Accessor method to retrieve the text for answer 1
     * @return the answer1 String
     */
    public String getAnswer1() {
        return answer1;
    }
    /**
     * Accessor method to retrieve the text for answer 2
     * @return the answer2 String
     */
    public String getAnswer2() {
        return answer2;
    }
    /**
     * Accessor method to retrieve the text for answer 3
     * @return the answer 3 String
     */
    public String getAnswer3() {
        return answer3;
    }
    /**
     * Accessor method to retrieve the correct answer for the given question
     * @return and int representing the correct answer
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }   /**
     * Accessor method to retrieve the answer the player has selected
     * @return the player's answer, represented by an int.
     */
    public int getPlayerAnswer() {
        return playerAnswer;
    }
    /**
     * Mutator method to set the players answer
     * @param playerAnswer represented by an int to indicate selection
     */
    public void setPlayerAnswer(int playerAnswer) {
        this.playerAnswer = playerAnswer;
    }
}

