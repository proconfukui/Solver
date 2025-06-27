package proconfukui;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class Field {
	int size;
	int[][] entities;
	private String format;
	
	Field(String path) {
		JsonNode problem = null;
		try {
			problem = new ObjectMapper().readTree(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		size = problem.get("problem").get("field").get("size").asInt();
		entities = new int[size][size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				entities[y][x] = problem.get("problem").get("field").get("entities").get(y).get(x).asInt();
			}
		}
		format = "%" + String.valueOf(size * size / 2).length() + "d ";
	}
	
//	Field(Field from) {
//		size = from.size;
//		for (int y = 0; y < size; y++) {
//			for (int x = 0; x < size; x++) {
//				entities[y][x] = from.entities[y][x];
//			}
//		}
//	}
	
	void print() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.printf(format, entities[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void rotate(int x, int y, int n) {
		if (n < 2 || x < 0 || x + n > size || y < 0 || y + n > size) {
			System.err.printf("導きが無効です(x: %d, y: %d, n: %d)\n", x, y, n);
			return;
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
	}
}

public class Main {
	public static void main(String[] args) {
		Field field = new Field("/Users/utautage/problem.json"); // Generatorで生成したJSONのパス
		field.print();
		field.rotate(0, 0, field.size);
		field.print();
	}
}
