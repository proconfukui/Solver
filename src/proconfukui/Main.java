package proconfukui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	static int[][] entities;
	// pairCoordinates[n][0]: 値がnのエンティティ1つ目のX
	// pairCoordinates[n][1]: 値がnのエンティティ1つ目のY
	// pairCoordinates[n][2]: 値がnのエンティティ2つ目のX
	// pairCoordinates[n][3]: 値がnのエンティティ2つ目のY
	static int[][] pairCoordinates;
	static int size;
	static int maxNumber;
	static ArrayList<int[]> history = new ArrayList<int[]>();
	
	static void loadField() {
		JsonNode problem = null;
		try {
			problem = new ObjectMapper().readTree(new File("/Users/utautage/problem.json")); // Generatorで生成したJSONのパス
		} catch (IOException e) {
			e.printStackTrace();
		}
		size = problem.get("problem").get("field").get("size").asInt();
		maxNumber = size * size / 2;
		entities = new int[size][size];
		pairCoordinates = new int[maxNumber][4];
		for (int number = 0; number < maxNumber; number++) {
			pairCoordinates[number][0] = -1;
		}
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				int number = problem.get("problem").get("field").get("entities").get(y).get(x).asInt();
				entities[y][x] = number;
				if (pairCoordinates[number][0] == -1) {
					pairCoordinates[number][0] = x;
					pairCoordinates[number][1] = y;
				} else {
					pairCoordinates[number][2] = x;
					pairCoordinates[number][3] = y;
				}
			}
		}
	}
	
	static void printPairCoordinates(){
		for (int number = 0; number < maxNumber; number++) {
			System.out.printf("%3d: (%2d, %2d), (%2d, %2d)\n", number, pairCoordinates[number][0], pairCoordinates[number][1], pairCoordinates[number][2], pairCoordinates[number][3]);
		}
		System.out.println();
	}
	
	static void printField() {
		System.out.println("手数:" + history.size());
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.printf("%3d ", entities[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void rotateField(int x, int y, int n) {
		if (n < 2 || x < 0 || x + n > size || y < 0 || y + n > size) {
			System.err.printf("導きが無効です(x: %d, y: %d, n: %d)\n", x, y, n);
			return;
		}
		
		for (int number = 0; number < maxNumber; number++) {
			if (x <= pairCoordinates[number][0] && pairCoordinates[number][0] < x + n && y <= pairCoordinates[number][1] && pairCoordinates[number][1] < y + n) {
				int dx = pairCoordinates[number][0] - x;
				int dy = pairCoordinates[number][1] - y;
				pairCoordinates[number][0] = x + dy;
				pairCoordinates[number][1] = y + n - 1 - dx;
			}
			if (x <= pairCoordinates[number][2] && pairCoordinates[number][2] < x + n && y <= pairCoordinates[number][3] && pairCoordinates[number][3] < y + n) {
				int dx = pairCoordinates[number][2] - x;
				int dy = pairCoordinates[number][3] - y;
				pairCoordinates[number][2] = x + dy;
				pairCoordinates[number][3] = y + n - 1 - dx;
			}
		}
		
		int[][] memo = new int[n][n];
		for(int dy = 0; dy < n; dy++) {
			for(int dx = 0; dx < n; dx++) {
				memo[dy][dx] = entities[y + n - 1 - dx][x + dy];
			}
		}
		for(int dy = 0; dy < n; dy++) {
			for(int dx = 0; dx < n; dx++) {
				entities[y + dy][x + dx] = memo[dy][dx];
			}
		}
		
		history.add(new int[] {x, y, n});
	}

	public static void main(String[] args) {
		loadField();
		printField();
		printPairCoordinates();
		
		int pointX = 0, pointY = 0;
		int number = entities[0][0];
		if (pairCoordinates[number][0] != pointX || pairCoordinates[number][1] != pointY) {
			int temp = pairCoordinates[number][2];
			pairCoordinates[number][0] = pairCoordinates[number][2];
			pairCoordinates[number][2] = temp;
			temp = pairCoordinates[number][3];
			pairCoordinates[number][1] = pairCoordinates[number][3];
			pairCoordinates[number][3] = temp;
		}
		
		if (pairCoordinates[number][2] == pointX && pairCoordinates[number][3] == pointY + 1) {
			
		}
	}
}
