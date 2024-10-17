package StackOverflow;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Answer implements Votable, Commentable {
    private final int id;
    private final String content;
    private final User author;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(String content, User author, Question question) {
        this.id = generateId();
        this.content = content;
        this.author = author;
        this.question = question;
        this.isAccepted = false;
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    @Override
    public void vote(User user, int value) {
        if(value != -1 && value != 1) {
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        }

        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * 10);    // +10 for upvote, -10 for downvote
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void markAsAccepted() {
        if(isAccepted) {
            throw new IllegalStateException("This answer is already accepted");
        }
        isAccepted = true;
        author.updateReputation(15);    // +15 reputation for accepted answer
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    // Getters
    public int getId() { return id; }
    public String getContent() { return content; }
    public User getAuthor() { return author; }
    public Question getQuestion() { return question; }
    public Date getCreationDate() { return creationDate; }
    public boolean isAccepted() { return isAccepted; }
}
