package abhik26.java_programs.interview_questions;

/*
 * Recursion Problem
 */
public class TowerOfHanoi {

	public static void main(String[] args) {
		int numberOfDisks = 2;
		int movesCount = moveDisks(numberOfDisks, "1", "2", "3");
		System.out.println("Total number of moves: " + movesCount);
	}

	private static int moveDisks(final int numberOfDisks, final String sourcePole, final String helperPole,
			final String targetPole) {
		int movesCount = 0;

		if (numberOfDisks > 1) {
			movesCount += moveDisks(numberOfDisks - 1, sourcePole, targetPole, helperPole);
			movesCount += moveDisks(1, sourcePole, helperPole, targetPole);
			movesCount += moveDisks(numberOfDisks - 1, helperPole, sourcePole, targetPole);
		} else {
			System.out.println(String.format("Move from %s to %s", sourcePole, targetPole));
			movesCount++;
		}

		return movesCount;
	}
}
