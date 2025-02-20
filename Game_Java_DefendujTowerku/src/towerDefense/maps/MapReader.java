package towerDefense.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads the map from a file.
 */
public class MapReader {
    public static int[][] readMatrixFromFile(String filename) {
        int[][] map = new int[15][20];
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine() && i < map.length) {
                String line = scanner.nextLine();
                String[] tokens = line.trim().split("\\s+");
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Map doesn't exist.");
        }

        return map;
    }
}
