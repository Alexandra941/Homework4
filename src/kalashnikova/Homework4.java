import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Homework4 {
    static int SIZE = 3;
    static final char DOT_EMPTY = '•';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static Character[][] map;
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    static boolean checkWin(char symb) {
        Character[] column = new Character[SIZE];
        //Проверка строк и столбцов.
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(column, null);

            if (arrAllMatch(map[i], symb)) {
                return true;
            }

            for(int j = 0; j < SIZE; j++) {
                column[j] = map[j][i];
            }
            if(arrAllMatch(column, symb)){
                return true;
            }
        }

        //Проверка диагоналей.
        Character[] diagonal1 = new Character[SIZE];
        Character[] diagonal2 = new Character[SIZE];
        for (int i = 0; i < SIZE; i++) {
            diagonal1[i] = map[i][i];
            diagonal2[i] = map[i][map.length - (i + 1)];
        }

        return arrAllMatch(diagonal1, symb) || arrAllMatch(diagonal2, symb);
    }

    static boolean arrAllMatch(Character[] arr, char symb) {
        return Arrays.stream(arr).allMatch(r -> r == symb);
    }

    static boolean isMapFull() {
        return Arrays.stream(map).allMatch(m -> Arrays.stream(m).allMatch(i -> i == DOT_EMPTY));
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new Character[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
