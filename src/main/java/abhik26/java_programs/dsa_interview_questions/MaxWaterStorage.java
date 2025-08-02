package abhik26.java_programs.dsa_interview_questions;

/*
 * Two pointer approach
 */
public class MaxWaterStorage {

	public static void main(String[] args) {
		int[] poleHeights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
		int maxStorageArea = getMaxWaterStorage(poleHeights);
		System.out.println("Max storage area: " + maxStorageArea);
	}

	public static int getMaxWaterStorage(int[] poleHeights) {
		int maxStorageArea = 0;

		int i = 0;
		int j = poleHeights.length - 1;

		while (i < j) {
			int storageArea = Math.min(poleHeights[i], poleHeights[j]) * (j - i);

			if (storageArea > maxStorageArea) {
				maxStorageArea = storageArea;
			}

			if (poleHeights[i] < poleHeights[j]) {
				i++;
			} else {
				j--;
			}
		}

		return maxStorageArea;
	}
}
