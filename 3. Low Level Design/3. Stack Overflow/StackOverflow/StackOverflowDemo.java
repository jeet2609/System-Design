package StackOverflow;

import java.util.List;
import java.util.Arrays;

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverflow system = StackOverflow.getInstance();

        // Create users
        User alice = system.createUser("Alice", "alice@example.com");
        User bob = system.createUser("Bob", "bob@example.com");
        User charlie = system.createUser("Charlie", "charlie@example.com");

        // Alice asks a question
        Question javaQuestion = system.askQuestion(alice, "What is polymorphism in Java?", "Can someone explains ploymorphism in Java with an example?", Arrays.asList("java", "oop"));

        // Charlie comments on the question
        system.addComment(charlie, javaQuestion, "Great question! I'm also interested in learning about this.");

        // Bob answers alice question
        Answer bobAnswer = system.answerQuestion(bob, javaQuestion, "Polymorphism in Java is the ability of an object to take on many forms...");

        // Alice comments to Bob's answer
        system.addComment(alice, bobAnswer, "Thanks for the explanation! Could you provide a code example?");

		// Alice accepts bobs answer
		system.acceptAnswer(bobAnswer);

        // Charlie votes on the question and answer
        system.vote(charlie, javaQuestion, 1);	// Upvote
        system.vote(charlie, bobAnswer, 1);		// Upvote

		// Alice votes on answer
		system.vote(alice, bobAnswer, 1);	// Upvote

		// Bob asks another question
        Question pythonQuestion = system.askQuestion(bob, "How to use list comprehensions in Python?",
                "I'm new to Python and I've heard about list comprehensions. Can someone explain how to use them?",
                Arrays.asList("python", "list-comprehension"));

		// Alice answers Bob's question
		Answer aliceAnswer = system.answerQuestion(alice, pythonQuestion, "List comprehensions in Python provide a concise way to create lists...");

		// Charlie votes on Bob's question and Alice's answer
        system.vote(charlie, pythonQuestion, 1);  // Upvote
        system.vote(charlie, aliceAnswer, 1);  // Upvote

		// Print out the current state
        System.out.println("Question: " + javaQuestion.getTitle());
        System.out.println("Asked by: " + javaQuestion.getAuthor().getUsername());
        System.out.println("Tags: " + javaQuestion.getTags().stream().map(Tag::getName).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + javaQuestion.getVoteCount());
        System.out.println("Comments: " + javaQuestion.getComments().size());
        System.out.println("\nAnswer by " + bobAnswer.getAuthor().getUsername() + ":");
        System.out.println(bobAnswer.getContent());
        System.out.println("Votes: " + bobAnswer.getVoteCount());
        System.out.println("Accepted: " + bobAnswer.isAccepted());
        System.out.println("Comments: " + bobAnswer.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Alice: " + alice.getReputation());
        System.out.println("Bob: " + bob.getReputation());
        System.out.println("Charlie: " + charlie.getReputation());

		// Demonstrate search functionality
        System.out.println("\nSearch Results for 'java':");
        List<Question> searchResults = system.searchQuestions("java");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        System.out.println("\nSearch Results for 'python':");
        searchResults = system.searchQuestions("python");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

		// Demonstrate getting questions by user
        System.out.println("\nBob's Questions:");
        List<Question> bobQuestions = system.getQuestionsByUser(bob);
        for (Question q : bobQuestions) {
            System.out.println(q.getTitle());
        }
    }
}