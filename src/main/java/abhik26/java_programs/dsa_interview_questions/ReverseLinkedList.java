package abhik26.java_programs.dsa_interview_questions;

public class ReverseLinkedList {

	private static class LinkedListNode {
		Integer data;
		LinkedListNode next;
	}

	public static void main(String[] args) {
		LinkedListNode head = new LinkedListNode();
		head.data = 1;
		head.next = null;
		LinkedListNode prev = head;

		for (int i=2; i<6; i++) {
			LinkedListNode node = new LinkedListNode();
			node.data = i;
			prev.next = node;
			prev = node;
		}

		System.out.println("\nLinked list before reversal:");
		printLinkedList(head);

		head = reverseLinkedList(head);
		System.out.println("Linked list after reversal:");
		printLinkedList(head);

		System.out.println();
	}

	private static LinkedListNode reverseLinkedList(LinkedListNode head) {
		if (head != null && head.next != null) {
			LinkedListNode next = head.next;
			LinkedListNode newHead = reverseLinkedList(head.next);

			next.next = head;
			head.next = null;
			head = newHead;
		}

		return head;
	}

	private static <T> void printLinkedList(LinkedListNode head) {
		LinkedListNode node = head;

		while (node != null) {
			System.out.print(node.data);

			if (node.next != null) {
				System.out.print(" -> ");
			}

			node = node.next;
		}

		System.out.println();
	}
}
