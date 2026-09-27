package bon;

public class Game {

    static final int ROWS = 10;
    static final int COLS = 20;

    // Рахуємо, скільки живих клітинок навколо клітинки (r, c)
    static int countNeighbors(boolean[][] grid, int r, int c) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // саму клітинку не рахуємо
                // «замкнене» поле: вийшли за край — з'являємось з протилежного боку
                int nr = (r + dr + ROWS) % ROWS;
                int nc = (c + dc + COLS) % COLS;
                if (grid[nr][nc]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Обчислюємо наступне покоління за правилами Конвея
    static boolean[][] nextGeneration(boolean[][] grid) {
        boolean[][] next = new boolean[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int n = countNeighbors(grid, r, c);
                if (grid[r][c]) {
                    // жива клітинка виживає, якщо має 2 або 3 сусідів
                    next[r][c] = (n == 2 || n == 3);
                } else {
                    // мертва клітинка оживає, якщо має рівно 3 сусідів
                    next[r][c] = (n == 3);
                }
            }
        }
        return next;
    }

    static void print(boolean[][] grid, int generation) {
        System.out.print("\033[H\033[2J"); // очистити консоль
        System.out.println("Покоління: " + generation);
        for (boolean[] row : grid) {
            StringBuilder sb = new StringBuilder();
            for (boolean cell : row) {
                sb.append(cell ? "█" : "·");
            }
            System.out.println(sb);
        }
        System.out.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        boolean[][] grid = new boolean[ROWS][COLS];

        // Початкова фігура — «глайдер» (літає по діагоналі)
        grid[1][2] = true;
        grid[2][3] = true;
        grid[3][1] = true;
        grid[3][2] = true;
        grid[3][3] = true;

        for (int gen = 0; gen < 200; gen++) {
            print(grid, gen);
            grid = nextGeneration(grid);
            Thread.sleep(150);
        }
    }
}