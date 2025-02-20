package towerDefense.maps;

import java.util.Random;

/**
 * Currently doesn't work as good as I would like it to.
 * It's a bit buggy.
 */
public class MapGenerator {
    private final int[][] map;
    public MapGenerator() {
        this.map = this.path();
    }
    public int[][] getMap() {
        return this.map;
    }

    private int[][] path() {
        int[][] matrix = new int[15][20];
        Random random = new Random();

        int row = random.nextInt(0, matrix.length);
        int col = 0;

        do {
            int direction = random.nextInt(0, 3);
            int count = random.nextInt(2, 6);
            if (col != matrix[row].length - 2) {
                switch (direction) {
                    case 0 -> {
                        while (col + count < matrix[row].length - 1 && count > 0) {
                            matrix[row][col] = 1;
                            col++;
                            count--;
                        }
                    }
                    case 1 -> {
                        if (matrix[row - 1][col] == 0) {
                            while (row - count > 0 && count > 0) {
                                matrix[row][col] = 1;
                                row--;
                                count--;
                            }
                        }
                    }
                    case 2 -> {
                        if (matrix[row + 1][col] == 0) {
                            while (row + count < matrix.length - 1 && count > 0) {
                                matrix[row][col] = 1;
                                row++;
                                count--;
                            }
                        }
                    }
                }
            } else if (col == matrix[row].length - 2 && direction == 0) {
                matrix[row][col] = 1;
                col++;

            }
        } while (col < matrix[row].length - 1);

        for (int[] bs : matrix) {
            for (int b : bs) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

        return matrix;
    }
}
