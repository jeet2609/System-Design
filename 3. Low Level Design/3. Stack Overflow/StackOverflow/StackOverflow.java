package StackOverflow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverflow {
	public static StackOverflow instance;
	private final Map<Integer, User> users;
	private final Map<Integer, Question> questions;
	private final Map<Integer, Answer> answers;
	private final Map<Integer, Tag> tags;

	private StackOverflow() {
		this.users = new ConcurrentHashMap<>();
		this.questions = new ConcurrentHashMap<>();
		this.answers = new ConcurrentHashMap<>();
		this.tags = new ConcurrentHashMap<>();
	}

	public static StackOverflow getInstance() {
		if (instance == null) {
			instance = new StackOverflow();
		}
		return instance;
	}

	public User createUser(String userName, String email) {
		int id = users.size() + 1;
		User user = new User(id, userName, email);
		users.put(id, user);
		return user;
	}

	public Question askQuestion(User user, String title, String content, List<String> tags) {
		Question question = user.askQuestion(title, content, tags);
		questions.put(question.getId(), question);
		for (Tag tag : question.getTags()) {
			this.tags.putIfAbsent(tag.getId(), tag);
		}
		return question;
	}

	public Answer answerQuestion(User user, Question question, String content) {
		Answer answer = user.answerQuestion(question, content);
		answers.put(answer.getId(), answer);
		return answer;
	}

	public Comment addComment(User user, Commentable commentable, String content) {
		return user.addComment(commentable, content);
	}

	public void vote(User user, Votable votable, int value) {
		user.addVote(votable, value);
	}

	public void acceptAnswer(Answer answer) {
		answer.markAsAccepted();
	}

	public List<Question> searchQuestions(String query) {
		return questions.values().stream()
				.filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) ||
						q.getContent().toLowerCase().contains(query.toLowerCase()) ||
						q.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(query)))
				.collect(Collectors.toList());
	}

	public List<Question> getQuestionsByUser(User user) {
		return user.getQuestions();
	}

	public User getUserById(int id) {
		return users.get(id);
	}

	public Question getQuestionById(int id) {
		return questions.get(id);
	}

	public Answer getAnswerById(int id) {
		return answers.get(id);
	}
}
