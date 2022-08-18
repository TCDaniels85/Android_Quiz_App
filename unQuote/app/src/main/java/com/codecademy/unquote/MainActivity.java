package com.codecademy.unquote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Quiz application, 6 questions are chosen from a possible 13. These are then presented to the user in a multiple choice format
 * one after another. At the end the user is given their score for the questions answered and prompted to begin the quiz again.
 */
public class MainActivity extends AppCompatActivity {
    public int currentQuestionIndex, totalCorrect, totalQuestions;
    public ArrayList<Question> questions = new ArrayList<Question>();
    public ImageView questionImageView;
    public TextView questionTextView;
    public TextView questionsRemainingView;
    public Button answer0Button;
    public Button answer1Button;
    public Button answer2Button;
    public Button answer3Button;
    public Button submitButton;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);
        // Assign View member variables
        questionImageView = findViewById(R.id.image);
        questionTextView = findViewById(R.id.questionText);
        questionsRemainingView = findViewById(R.id.questionsRemaining);
        answer0Button = findViewById(R.id.answer1);
        answer1Button = findViewById(R.id.answer2);
        answer2Button = findViewById(R.id.answer3);
        answer3Button = findViewById(R.id.answer4);
        submitButton = findViewById(R.id.submit);

        //set onClickListener for each answer Button
        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(0);
            }
        });
        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(1);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(2);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(3);
            }
        });


        // set onClickListener for the submit answer Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSubmission();
            }
        });
        startNewGame();
    }

    /**
     * Start game method, this adds 13 questions to an arraylist then removes 7 and displays the first question to the user.
     */
    public void startNewGame(){
        Question question1 = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it… Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question question2 = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz",  "Maya Angelou",  "Abraham Lincoln",  "Ralph Waldo Emerson", 0);
        Question question3 = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers",  "Oprah Winfrey", 1);
        Question question4 = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question question5 = new Question(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);
        Question question6 = new Question(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);
        Question question7 = new Question(R.drawable.img_quote_6, "Here’s the truth, Will Smith did say this, but in which movie?", "Independence Day", "Bad Boys", "Men In Black", "The Pursuit of Happiness", 2);
        Question question8 = new Question(R.drawable.img_quote_7, "Which TV funny gal actually quipped this 1-liner?", "Ellen Degeneres", "Amy Poehler", "Betty White", "Tina Fay", 3);
        Question question9 = new Question(R.drawable.img_quote_8, "This mayor won’t get my vote — but did he actually give this piece of advice? And if not, who did?", "Forrest Gump, Forrest Gump", "Dorry, Finding Nemo", "Esther Williams", "The Mayor, Jaws", 1);
        Question question10 = new Question(R.drawable.img_quote_9, "Her heart will go on, but whose heart is it?", "Whitney Houston", "Diana Ross", "Celine Dion", "Mariah Carey", 0);
        Question question11 = new Question(R.drawable.img_quote_10, "He’s the king of something alright — to whom does this self-titling line belong to?", "Tony Montana, Scarface", "Joker, The Dark Knight", "Lex Luthor, Batman v Superman", "Jack, Titanic", 3);
        Question question12 = new Question(R.drawable.img_quote_11, "Is “Grey” synonymous for “wise”? If so, maybe Gandalf did preach this advice. If not, who did?", "Yoda, Star Wars", "Gandalf The Grey, Lord of the Rings", "Dumbledore, Harry Potter", "Uncle Ben, Spider-Man", 0);
        Question question13 = new Question(R.drawable.img_quote_12, "Houston, we have a problem with this quote — which space-traveler does this catch-phrase actually belong to?", "Han Solo, Star Wars", "Captain Kirk, Star Trek", "Buzz Lightyear, Toy Story", "Jim Lovell, Apollo 13", 2);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);
        questions.add(question13);
        //Log.i(TAG, "I am a logging pro" + questions.size());
        while(questions.size()>6){
            questions.remove(generateRandomNumber(questions.size()));
        }



        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();
        displayQuestion(firstQuestion);
        displayQuestionsRemaining(questions.size());

    }

    /**
     * Displays the number of questions remaining
     * @param remaining Integer which keeps track of the number of questions still to answer
     */
    public void displayQuestionsRemaining(Integer remaining){
        questionsRemainingView.setText(remaining.toString());
    }

    /**
     * Displays the question in the app, setting the text on the relevant buttons and textview, and setting the image to be displayed
     * @param question Question object from the array of questions
     */
    public void displayQuestion(Question question){
        answer0Button.setText(question.getAnswer0());
        answer1Button.setText(question.getAnswer1());
        answer2Button.setText(question.getAnswer2());
        answer3Button.setText(question.getAnswer3());
        questionImageView.setImageResource(question.getImageId());
        questionTextView.setText(question.getQuestionText());
    }

    /**
     * Displays a tick on the answer button that the user has selected, so they can visually confirm they have made a choice
     * @param answerSelected user's selected answer
     */
    public void onAnswerSelected(int answerSelected){
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.setPlayerAnswer(answerSelected);
        answer0Button.setText(currentQuestion.getAnswer0());
        answer1Button.setText(currentQuestion.getAnswer1());
        answer2Button.setText(currentQuestion.getAnswer2());
        answer3Button.setText(currentQuestion.getAnswer3());
        if(answerSelected == 0){
            answer0Button.setText("✔");
            //answer0Button.setBackgroundColor();
        }else if(answerSelected == 1){
            answer1Button.setText("✔");
        }else if(answerSelected == 2){
            answer2Button.setText("✔");
        }else if (answerSelected == 3){
            answer3Button.setText("✔");
        }

    }

    /**
     * Chooses a new question at random from the list of remaining questions. Sets the current question index
     * @return Question object from the questions array
     */
    public Question chooseNewQuestion(){
        int nextQuestion = generateRandomNumber(questions.size()-1);
        currentQuestionIndex = nextQuestion;
        return questions.get(currentQuestionIndex);
    }

    /**
     * Retrieves the current question object being displayed
     * @return question object
     */
    public Question getCurrentQuestion(){
        return questions.get(currentQuestionIndex);
    }

    /**
     * Method for when the submission button is clicked, checks if the answer is correct and adjusts score. If the player has answered all the questions
     * they are presenting them with a game over dialog box which contains  the score and prompts them to start again. Otherwise calls the chooseNextQuestion and
     * displayQuestion methods to continue the game
     */
    public void onAnswerSubmission(){
        if(getCurrentQuestion().getPlayerAnswer() == -1){
            return;
        }
        if(getCurrentQuestion().isCorrect()){
            totalCorrect++; //adds to score
        }

        questions.remove(currentQuestionIndex); //removes current question from the array
        //totalQuestions--;
        displayQuestionsRemaining(questions.size());
        if(questions.size() == 0){
            //Creates an alert dialogue to represent game over screen if all questions have been answered
            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Game Over!");
            gameOverDialogBuilder.setMessage(getGameOverMessage(totalCorrect, totalQuestions));
            gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                }
            });
            gameOverDialogBuilder.create().show();

        }else{
            //continue game
            chooseNewQuestion();
            displayQuestion(getCurrentQuestion());
        }

    }

    /**
     * Generate a random number with the maximum value being taken as a parameter
     * @param max maximum number the randomly generated number can be
     * @return int number
     */
    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    /**
     * Creates a game over message which depends on the number of questions the user answered correctly
     * @param totalCorrect number of correct answers
     * @param totalQuestions total number of questions
     * @return game over message string to be displayed to user
     */
    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}
