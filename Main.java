public class Main {
	public static void main(String[] args) {
		Options opt = new Options();
		opt = opt.parseInput(args);

		Mastermind game = new Mastermind(opt);
		game.run();
	}
}
