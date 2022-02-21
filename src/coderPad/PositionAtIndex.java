package coderPad;

public class PositionAtIndex {

	public static void main(String[] args) {

		int[] array = { 2, 4, 8, 16, 32, 64 };

		printPosition(array);

	}

	public static void printPosition(int[] array) {

		for (int x : array) {

			if (x == array[array.length - 1])
				System.out.print(x);
			else
				System.out.print(x + ", ");
		}
		System.out.println();

		int k = 1;
		while (k < 20) {
			System.out.print('-');
			k++;
		}

		System.out.println();

		for (int i = 0; i <= array.length - 1; i++) {
			if (i < 3)
				System.out.print(i + "  ");
			else
				System.out.print(i + "   ");

		}
	}
}