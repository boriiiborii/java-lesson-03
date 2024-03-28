package kr.easw.lesson3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SnakeGameWithoutTails {

    private static final int BOARD_SIZE = 10;
    // 0 - 빈 타일
    // 1 - 스네이크 블럭
    // 2 - 아이템
    private static final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    private static final Random RANDOM = new Random();

    private static int score = 0;

    private static SnakeLocation location = new SnakeLocation(0, 0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!hasItemOnBoard())
                placeRandomItem();
            printBoard();
            System.out.print("[우측 (r) | 좌측 (l) | 위 (u) | 아래 (d) | 종료 (0) ] : ");
            if (!nextDirection(scanner.next())) {
                System.out.println("게임 오버!");
                System.out.printf("점수: %d\n", score);
                break;
            }
        }
    }

    private static boolean nextDirection(String keyword) {
        int newX = location.getX();
        int newY = location.getY();

        switch (keyword) {
            case "r":
                newY++;
                break;
            case "l":
                newY--;
                break;
            case "u":
                newX--;
                break;
            case "d":
                newX++;
                break;
            case "0":
                return false;
            default:
                System.out.println("제대로 입력하세요");
                return true;
        }

        // 보드의 범위
        if (newX >= 0 && newX < BOARD_SIZE && newY >= 0 && newY < BOARD_SIZE) {
            // 아이템을 먹었는지
            if (board[newX][newY] == 2) {
                score++;
                board[newX][newY] = 0; // 아이템 칸 빈 타일로 변경
            }
            // 스네이크의 위치 업데이트
            location = new SnakeLocation(newX, newY);
        }
        return true;
    }


    private static void printBoard() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (location.getX() == x && location.getY() == y) {
                    System.out.print("◼ ");
                    continue;
                }
                switch (board[x][y]) {
                    case 0:
                        System.out.print("・ ");
                        break;
                    case 1:
                        System.out.print("◼");
                        break;
                    case 2:
                        System.out.print("* ");
                        break;
                }
            }
            System.out.println();
        }
    }

    private static void placeRandomItem() {
        // for문의 조건으로 랜덤을 넣을 경우, 계속 비교하여 최종 결과값이 바뀌므로 변수로 선언합니다.
        int toPlace = (int) (RANDOM.nextDouble() * 10);
        for (int i = 0; i < toPlace; i++) {
            int retry = 0;
            while (retry < 5) {
                SnakeLocation locate = new SnakeLocation((int) (RANDOM.nextDouble() * (BOARD_SIZE-1)), (int) (RANDOM.nextDouble() * (BOARD_SIZE-1)));
                if (board[locate.getX()][locate.getY()] != 0) {
                    retry++;
                    continue;
                }
                board[locate.getX()][locate.getY()] = 2;
                break;
            }
        }
    }

    private static boolean hasItemOnBoard() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[x][y] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class SnakeLocation {
        private final int x;
        private final int y;

        public SnakeLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public SnakeLocation adjust(int x, int y) {
            return new SnakeLocation(this.x + x, this.y + y);
        }
    }
}