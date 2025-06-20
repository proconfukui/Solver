package proconfukui;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	static final String path = "/Users/utautage/problem.json"; // Generatorで生成したJSONのパス
	public static void main(String[] args) {
		try {
			JsonNode problem = new ObjectMapper().readTree(new File(path));
			int size = problem.get("problem").get("field").get("size").asInt();
			int[][] field = new int[size][size];
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					field[y][x] = problem.get("problem").get("field").get("entities").get(y).get(x).asInt();
				}
			}
			showField(field, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void showField(int[][] field, int moveCount) {
		System.out.println("手数:" + moveCount);
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[0].length; x++) {
				System.out.print(field[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
