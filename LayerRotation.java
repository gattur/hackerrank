package hackerank;

import java.util.ArrayList;
import java.util.List;

class Node {
	Node(int score) {
		this.value = score;
	}
	int value;
	Node left;
	Node right;
}

public class LayerRotation {
	static int finale[][];
	public static void main(String[] args) {

		int matrix[][] = { { 1, 2, 3, 4 }, { 7, 8, 9, 10 }, { 13, 14, 15, 16 }, { 19, 20, 21, 22 },
				{ 25, 26, 27, 28 } };
		finale = new int[matrix.length][matrix[0].length];
		matrixRotation(matrix, 7);
	}

	static void matrixRotation(int[][] matrix, int r) {

		int times = Math.min(matrix.length, matrix[0].length) / 2;
		List<Node> l = new ArrayList<>(times);
		for (int i = 0; i < times; i++) {
			adder(i, matrix, l, r);
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(finale[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void adder(int index, int[][] matrix, List<Node> l, int r) {
		Node head = null;
		Node tail = null;
		Node main_head = null;
		for (int i = index; i < (matrix.length - (index)); i++) {
			if (index == i) {
				Node n = new Node(matrix[index][index]);
				head = n;
				tail = n;
				main_head = head;
				for (int j = index + 1; j < (matrix[0].length - (2 * index) + index); j++) {
					Node temp = new Node(matrix[index][j]);
					tail.right = temp;
					temp.left = tail;
					tail = tail.right;
				}
			} else if (i == matrix.length - (index) - 1) {
				Node n = new Node(matrix[i][index]);
				n.right = head;
				head.left = n;
				head = head.left;
				for (int j = (matrix[0].length - (index)) - 1; j > index; j--) {
					Node temp = new Node(matrix[i][j]);
					tail.right = temp;
					temp.left = tail;
					tail = tail.right;
				}
			} else {
				Node n_l = new Node(matrix[i][index]);
				Node n_r = new Node(matrix[i][matrix[0].length - (index) - 1]);
				head.left = n_l;
				n_l.right = head;
				head = head.left;
				tail.right = n_r;
				n_r.left = tail;
				tail = tail.right;
			}
		}
		head.left = tail;
		tail.right = head;
		int rotations = Math.floorMod(r,
				((matrix.length - 2 - (2 * index)) * 2) + ((matrix[0].length - (2 * index)) * 2));
		while (rotations != 0) {
			main_head = main_head.right;
			rotations = rotations - 1;
		}
		head = main_head;
		tail = main_head;
		for (int i = index; i < (matrix.length - (index)); i++) {
			if (index == i) {
				for (int j = index; j < (matrix[0].length - (2 * index) + index); j++) {
					finale[i][j] = tail.value;
					tail = tail.right;
				}
				head = head.left;
			} else if (i == matrix.length - (index) - 1) {
				for (int j = (matrix[0].length - (index)) - 1; j >= index; j--) {
					finale[i][j] = tail.value;
					tail = tail.right;
				}
			} else {
				finale[i][index] = head.value;
				finale[i][matrix[0].length - index - 1] = tail.value;
				head = head.left;
				tail = tail.right;
			}
		}
	}
}