package snake;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Timer;

public class SnakeGame implements ActionListener {
	static LinkedList<SnakeBody> snake = new LinkedList<>();

	public static void main(String args[]) {

		newGame(90);

	}

	public static void newGame(int dificultyLevel) {
		new SnakeGame(80, 80, dificultyLevel);
	}

	private Random rand = new Random();
	private Field field;
	private Gameview view;
	private Timer timer;
	private int delay = 100;
	private int dificulty;

	SnakeGame(int depth, int width, int dificultyLevel) {

		field = new Field(depth, width);
		dificulty = dificultyLevel;
		// Create a view of the state of each location in the field.
		view = new Gameview(depth, width);

		view.setColor(SnakeBody.class, Color.GREEN);
		view.setColor(Food.class, Color.RED);
		Location startingLocation = new Location(rand.nextInt(depth - 10) + 5, rand.nextInt(width - 10) + 5);
		snake.add(new SnakeBody(startingLocation));
		snake.add(new SnakeBody(new Location(startingLocation.getRow() + 1, startingLocation.getCol())));
		snake.add(new SnakeBody(new Location(startingLocation.getRow() + 2, startingLocation.getCol())));
		field.place(snake.get(0), startingLocation);
		field.place(snake.get(1), snake.get(1).getCurrentLocation());
		field.place(snake.get(2), snake.get(2).getCurrentLocation());
		field.place(new Food(), field.getFreeLocation());
		view.showStatus(field);
		timer = new Timer(delay, this); // Only execute once
		timer.start(); // Go go go!
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		moveSnake();

	}

	private void end() {
		new EndScreen(snake.size() - 3, this);
		timer.stop();

	}

	public void moveSnake() {

		try {
			SnakeBody head = snake.getFirst();
			SnakeBody additional = null;
			SnakeBody tail = snake.removeLast();
			Location tailLocation = tail.getCurrentLocation();
			field.clear(tail.getCurrentLocation());
			field.clear(head.getCurrentLocation());
			tail.setCurrentLocation(
					new Location(head.getCurrentLocation().getRow(), head.getCurrentLocation().getCol()));
			snake.add(1, tail);

			if (head.getCurrentDirection().equals("up")) {
				head.getCurrentLocation().moveup();
			} else if (head.getCurrentDirection().equals("down")) {
				head.getCurrentLocation().movedown();
			} else if (head.getCurrentDirection().equals("left")) {
				head.getCurrentLocation().moveleft();
			} else if (head.getCurrentDirection().equals("right")) {
				head.getCurrentLocation().moveright();
			}
			if (field.getObjectAt(head.getCurrentLocation()) instanceof Food) {
				additional = new SnakeBody(tailLocation);
				snake.addLast(additional);
				field.place(additional, tailLocation);
				field.place(new Food(), field.getFreeLocation());
				delay = delay * dificulty / 100;
				timer.setDelay(delay);

			}
			if (field.getObjectAt(head.getCurrentLocation()) instanceof SnakeBody) {
				end();
			}
			field.place(head, head.getCurrentLocation());
			field.place(tail, tail.getCurrentLocation());
			view.showStatus(field);
		} catch (Exception e) {
			end();
			// TODO Auto-generated catch block

		}

	}

	void quit() {
		snake.clear();
		view.quit();

		// TODO Auto-generated method stub

	}

	public void restart(int dificultyLevel) {
		quit();
		newGame(dificultyLevel);
	}

}
