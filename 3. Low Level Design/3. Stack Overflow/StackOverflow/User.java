package StackOverflow;

import java.util.List;
import java.util.ArrayList;

public class User {
    private final int id;
    private final String userName;
    private final String email;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    public User(int id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.reputation = reputation;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    public Question askQuestion(String title, String content, List<String> tags) {
        Question question = new Question(title, content, this, tags);
        questions.add(question);
        updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Answer answerQuestion(Question question, String content) {
        Answer answer = new Answer(content, this, question);
        answers.add(answer);
        question.addAnswer(answer);
        updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public synchronized void updateReputation(int value) {
        this.reputation += value;
        if(reputation < 0) {
            this.reputation = 0;
        }
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return userName; }
    public String getEmail() { return email; }
    public int getReputation() { return reputation; }
    public List<Question> getQuestions() { return new ArrayList<>(questions); }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Comment> getComments() { return new ArrayList<>(comments); }
}
