import java.util.Random;
import java.util.Scanner;

public class Mastermind {
	private boolean first_round = true;
	private int attempts = 10;
	private int well_placed;
	private int misplaced;
	private String code;
	private String guess;

	public Mastermind(Options opt) {
		this.attempts = opt.attempts;
		this.code = opt.code;
		if (code == null) {
			this.code = generateCode();
		}
	}

	private String generateCode() {
		char[] pieces = {'1','2','3','4','5','6','7'};
		Random random = new Random();
		for (int i = random.nextInt(50); i > 0; i--) {
			int rand_a = random.nextInt(6);
			int rand_b = random.nextInt(6);
			
			char temp = pieces[rand_a];
			pieces[rand_a] = pieces[rand_b];
			pieces[rand_b] = temp;
		}
		String code = "";
		for (int i = 4; i > 0; i--) {
			code += pieces[i];
		}
		return code;
	}

	public void run() {
		render();
		while (attempts-- > 0 && well_placed != 4) {
			first_round = false;
			misplaced = 0;
			well_placed = 0;
			receiveGuess();
			update();
			render();
		}
	}

	private void receiveGuess() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String guess = scanner.nextLine();
			if (guess.length() != 4) {
				System.out.println("Invalid guess length");
				continue;
			}
			for (int i = 0; i < guess.length(); i++) {
				if (guess.charAt(i) < '1' || guess.charAt(i) > '7') {
					System.out.println("Invalid character in guess");
					break;
				}
				else if (i == 3) {
					this.guess = guess;
					return;
				}
			}
		}
	}

	private void update() {
		String copy = code;
		for (int i = 0; i < guess.length(); i++) {
			for (int j = 0; j < copy.length(); j++) {
				if (guess.charAt(i) == copy.charAt(j)) {
					if (i == j) {
						well_placed++;
					}
					else {
						misplaced++;
					}
					copy = copy.substring(0, j) + '-' +
						copy.substring(j + 1);
				}
			}
		}
	}

	private void render() {
		if (first_round) {
			System.out.println("Welcome to Mastermind!");
			System.out.println("You have " + attempts + " guesses to figure out the code");
			System.out.print(">> ");
			return;
		}
		if (well_placed == 4) {
			System.out.println("Congratulations, you've guessed my code!");
			System.out.println("You had " + attempts + " guesses remaining");
			return;
		}
		if (attempts == 0) {
			System.out.println("'Twas a valiant effort, but alas my code was not discovered");
			System.out.println("Out of pity, I will reveal that the code was: " + code);
			System.out.println("Better luck next time!");
			return;
		}
		System.out.println("Misplaced Pieces: " + misplaced);
		System.out.println("Well Placed Pieces: " + well_placed);
		System.out.println("Guesses Remaining: " + attempts);
	}
}
