import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void RandomIntegersFiller(int[] A) {
		Random rnd = new Random();
		int r;
		for (int i = 0; i < A.length; i++) {
			r = rnd.nextInt(10000);
			A[i] = r;
		}
	}

	public static void EqualIntegersFiller(int[] A) {
		for (int i = 0; i < A.length; i++) {
			A[i] = 1000;
		}
	}

	public static void IncreasingIntegersFiller(int[] A) {
		for (int i = 0; i < A.length; i++) {
			A[i] = i;
		}
	}

	public static void DecreasingIntegersFiller(int[] A) {
		int count = 0;
		for (int i = A.length; i > 0; i--) {
			A[count] = i;
			count++;
		}
	}

	public static void main(String[] args) {

		SortingClass s = new SortingClass();

		int A1000[] = new int[1000];
		int A10000[] = new int[10000];
		int A100000[] = new int[100000];

		EqualIntegersFiller(A100000);
		//RandomIntegersFiller(A10000);
		//IncreasingIntegersFiller(A10000);
		//DecreasingIntegersFiller(A10000);

		long startTime = System.nanoTime();
		s.INTROSORT(A100000);
		long estimatedTime = System.nanoTime() - startTime;
		float msTime = (float) estimatedTime / 1000000;
		System.out.println("Time :" + estimatedTime);
		System.out.println("Time :" + msTime);

	}
}
