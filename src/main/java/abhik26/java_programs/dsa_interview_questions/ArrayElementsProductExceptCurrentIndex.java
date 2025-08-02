package abhik26.java_programs.dsa_interview_questions;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/*
 * Dynamic Programming
 */
public class ArrayElementsProductExceptCurrentIndex {

	public static void main(String[] args) {
		int arraySize = 18;
		int[] nums = new int[arraySize];

		for (int i = 0; i < arraySize; i++) {
			nums[i] = new Random().nextInt(8) + 2;
		}

		long startTime = System.currentTimeMillis();
		long[] result = getElementsProduct(nums);
		long endTime = System.currentTimeMillis();
		long timeTaken = (endTime - startTime);
		System.out.println("nums: " + Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println("products: " + Arrays.stream(result).boxed().collect(Collectors.toList()));
		System.out.println("Time taken in milli-seconds: " + timeTaken);
	}

	private static long[] getElementsProduct(int[] nums) {
		long[] result = new long[nums.length];
		Long[] leftProducts = new Long[nums.length];
		Long[] rightProducts = new Long[nums.length];

		for (int i = 0; i < nums.length; i++) {
			long leftProduct = getLeftElementsProduct(nums, leftProducts, i);
			long rightProduct = getRightElementsProduct(nums, rightProducts, i);

			Long product = leftProduct * rightProduct;
			result[i] = product;
		}

		return result;
	}

	private static long getLeftElementsProduct(int[] nums, Long[] leftProducts, int i) {
		long result;
		final int index = i - 1;

		if (i == 0) {
			result = 1;
		} else if (leftProducts[index] != null) {
			result = leftProducts[index];
		} else {
			long leftProduct = getLeftElementsProduct(nums, leftProducts, index);
			leftProducts[index] = leftProduct * nums[index];
			result = leftProducts[index];
		}

		return result;
	}

	private static Long getRightElementsProduct(int[] nums, Long[] rightProducts, int i) {
		long result;
		final int index = i + 1;

		if (i == nums.length - 1) {
			result = 1;
		} else if (rightProducts[index] != null) {
			result = rightProducts[index];
		} else {
			long rightProduct = getRightElementsProduct(nums, rightProducts, index);
			rightProducts[index] = rightProduct * nums[index];
			result = rightProducts[index];
		}

		return result;
	}
}
