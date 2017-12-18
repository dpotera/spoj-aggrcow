package pl.potera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int t = scanner.nextInt();
        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(), c = scanner.nextInt();
            ArrayList<Integer> stalls = new ArrayList<>(n);
            for (int j = 0; j < n; j++) stalls.add(j, scanner.nextInt());
            stalls.sort(Integer::compareTo);
            ArrayList<Integer> cows = new ArrayList<>(n);
            cows.add(stalls.get(0));
            cows.add(stalls.get(stalls.size()-1));
            c -= 2;
            while (c > 0) {
                int end = stalls.size() -1;
                int dist = distance(stalls.get(0), stalls.get(end));
                int value = stalls.get(0) + dist / (c + 1);
                int index = getClosestValueIndex(stalls, value);
                cows.add(stalls.get(index));
                stalls.remove(index);
                c -= 1;
            }
            cows.sort(Integer::compareTo);
            System.out.println(minimalDistance(cows));

        }
    }

    private static int minimalDistance(ArrayList<Integer> arrayList) {
        int result = Integer.MAX_VALUE;
        for (int i=1; i<arrayList.size(); i++) {
            int dist = distance(arrayList.get(i-1), arrayList.get(i));
            if (dist < result)
                result = dist;
        }
        return result;
    }

    private static int getClosestValueIndex(List<Integer> array, int value) {
        int start = 0, end = array.size()-1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (distance(array.get(middle), value) <= distance(array.get(middle + 1), value)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return start;
    }

    private static int distance(int from, int to) {
        return Math.abs(from - to);
    }
}
