package abhik26.java_programs.dsa_interview_questions;

public class MaxSubArraySum {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 7, -4, 3, 2, -10, 9, 1 };

		long sum = maxSubArraySum(arr, arr.length);
		System.out.println(sum);
	}

	public static long maxSubArraySum(int[] arr, int n) {
		long maxSum = 0;
		long sum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			sum = Math.max(arr[i], arr[i] + sum);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}

}
