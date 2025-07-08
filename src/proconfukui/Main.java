package proconfukui;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//class Field {
//	final int size;
//	final int[][] entities;
//	
//	private ArrayList<int[]> history = new ArrayList<int[]>();
//	private final String format;
//	
//	Field(String path) {
//		JsonNode problem = null;
//		try {
//			problem = new ObjectMapper().readTree(new File(path));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		size = problem.get("problem").get("field").get("size").asInt();
//		entities = new int[size][size];
//		for (int y = 0; y < size; y++) {
//			for (int x = 0; x < size; x++) {
//				entities[y][x] = problem.get("problem").get("field").get("entities").get(y).get(x).asInt();
//			}
//		}
//		format = "%" + String.valueOf(size * size / 2).length() + "d ";
//	}
//	
//	void print() {
//		System.out.println("手数:" + history.size());
//		for (int y = 0; y < size; y++) {
//			for (int x = 0; x < size; x++) {
//				System.out.printf(format, entities[y][x]);
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//	
//	void rotate(int x, int y, int n) {
//		if (n < 2 || x < 0 || x + n > size || y < 0 || y + n > size) {
//			System.err.printf("導きが無効です(x: %d, y: %d, n: %d)\n", x, y, n);
//			return;
//		}
//		int[][] memo = new int[n][n];
//		for(int dy = 0; dy < n; dy++) {
//			for(int dx = 0; dx < n; dx++) {
//				memo[dy][dx] = entities[y + n - 1 - dx][x + dy];
//			}
//		}
//		for(int dy = 0; dy < n; dy++) {
//			for(int dx = 0; dx < n; dx++) {
//				entities[y + dy][x + dx] = memo[dy][dx];
//			}
//		}
//		history.add(new int[] {x, y, n});
//	}
//}

public class Main {
//	static Field field = new Field("/Users/utautage/problem.json"); // Generatorで生成したJSONのパス
	// pairCoordinates[n-1][0]: 値がnのエンティティ1つ目のX
	// pairCoordinates[n-1][1]: 
	// pairCoordinates[n-1][2]: 
	// pairCoordinates[n-1][3]: 
	static int[][] entities;
	static int[][] pairCoordinates;
	static int size;
	
	static void loadField() {
		JsonNode problem = null;
		try {
			problem = new ObjectMapper().readTree(new File("/Users/utautage/problem.json")); // Generatorで生成したJSONのパス
		} catch (IOException e) {
			e.printStackTrace();
		}
		size = problem.get("problem").get("field").get("size").asInt();
		pairCoordinates = new int[size * size / 2][4];
		
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				int number = problem.get("problem").get("field").get("entities").get(y).get(x).asInt();
				entities[y][x] = number;
				if (pairCoordinates[number][0] == -1) {
					
				} else {
					
				}
			}
		}
	}
	
	static void printField() {
		
	}
	
//	static void matchPair(int pointX, int pointY) {
//		int targetX = 0, targetY = 0;
//		findPair: for (targetY = 0; targetY < field.size; targetY++) {
//			for (targetX = 0; targetX < field.size; targetX++) {
//				if (targetX == pointX && targetY == pointY) {
//					continue;
//				}
//				if (field.entities[targetY][targetX]==field.entities[pointY][pointX]) {
//					break findPair;
//				}
//			}
//		}
//		int maxN = field.size - pointX - 1;
//		if ()
//		if (pointX == targetX + 1) {
//			field.rotate(pointX + 1, pointY, field.size - pointX - 1);
//			field.print();
//		}
//	}
	
	
	public static void main(String[] args) {
		loadField();
		printField();
//		format = "%" + String.valueOf(size * size / 2).length() + "d ";
//		field.print();
//		matchPair(0, 0);
		// (targetX, targetY) を pointX + 1に寄せる
//		if (targetX != pointX + 1) {
//			field.rotate(pointX + 1, pointY, field.size - pointX - 1);
//			field.print();
//		}
		
	}
}
