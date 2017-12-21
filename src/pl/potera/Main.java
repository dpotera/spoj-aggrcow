package pl.potera;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] results = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(), c = scanner.nextInt();
            int[] points = new int[n];
            for (int j = 0; j < n; j++) points[j] = scanner.nextInt();
            Arrays.sort(points);
            results[i] = getMaximumDistance(points, c);
        }
        for (int r : results) System.out.println(r);
    }

    private static int getMaximumDistance(int[] array, int cows) {
        int start = 1, end = array[array.length-1];
        while (start < end-1) {
            int middle = (start + end) / 2;
            if (checkDistance(array, cows, middle)) {
                start = middle;
            } else {
                end = middle - 1;
            }
        }
        return checkDistance(array, cows, end) ? end : start;
    }

    private static boolean checkDistance(int[] array, int cows, int distance) {
        int lastPoint = array[0];
        cows--;
        for (int i=1; i<array.length; i++) {
            if (array[i] - lastPoint >= distance) {
                lastPoint = array[i];
                if (--cows == 0) return true;
            }
        }
        return false;
    }
}
