/*
Додаткове завдання
Розклад студентів на тиждень (Тривимірний масив)
Сюжет: Університетська система розкладу для 3 груп на 5 навчальних днів по 4 пари на день.
Завдання:
Опишіть масив розмірністю [3][5][4] (Групи × Дні × Пари), де зберігаються назви предметів (або "Вільне вікно").
Знайдіть день із найбільшим навантаженням (найбільшою кількістю пар) для обраної групи.
Знайдіть день із незручним розкладом (день, де в розкладі є «вікна»)
Перевірте, чи є в розкладі заняття для “потоку (коли заняття з одного предмету відбуваються в кількох групах в один і той самий час).
 */

package PracticalTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bonus {

    static final int GROUPS = 3;
    static final int DAYS = 5;
    static final int LESSONS = 4;
    static final String FREE = "Вільне вікно";

    static final String[] GROUP_NAMES = {"ІПЗ-25", "ІПЗ-25-2", "ІПЗ-25-3"};
    static final String[] DAY_NAMES = {"Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця"};

    public static void main(String[] args) {
        String[][][] schedule = createSchedule();
        Scanner scanner = new Scanner(System.in);

        printSchedule(schedule);

        System.out.print("Введіть номер групи (1-" + GROUPS + "), для якої зробити аналіз: ");
        int groupIndex = scanner.nextInt() - 1;

        if (groupIndex < 0 || groupIndex >= GROUPS) {
            System.out.println("Невірний номер групи!");
        } else {
            findBusiestDay(schedule, groupIndex);
            System.out.println();
            findInconvenientDays(schedule, groupIndex);
        }

        System.out.println();
        checkStreamLessons(schedule);

        scanner.close();
    }

    static String[][][] createSchedule() {
        String[][][] s = new String[GROUPS][DAYS][LESSONS];

        for (int g = 0; g < GROUPS; g++)
            for (int d = 0; d < DAYS; d++)
                for (int l = 0; l < LESSONS; l++)
                    s[g][d][l] = FREE;

        s[0][0] = new String[]{"Математика", "Програмування", FREE, "Англійська"};
        s[0][1] = new String[]{"Фізика", FREE, FREE, "Бази даних"};
        s[0][2] = new String[]{"Програмування", "Математика", "Алгоритми", "Бази даних"};
        s[0][3] = new String[]{FREE, "Алгоритми", "Фізика", FREE};
        s[0][4] = new String[]{"Англійська", FREE, "Програмування", FREE};
        s[1][0] = new String[]{"Математика", "Фізика", "Програмування", FREE};
        s[1][1] = new String[]{FREE, "Фізика", "Англійська", "Бази даних"};
        s[1][2] = new String[]{"Програмування", "Математика", "Бази даних", "Алгоритми"};
        s[1][3] = new String[]{"Алгоритми", FREE, FREE, "Англійська"};
        s[1][4] = new String[]{"Англійська", FREE, "Програмування", FREE};
        s[2][0] = new String[]{FREE, "Математика", "Алгоритми", "Програмування"};
        s[2][1] = new String[]{"Фізика", "Бази даних", FREE, FREE};
        s[2][2] = new String[]{FREE, FREE, "Алгоритми", FREE};
        s[2][3] = new String[]{FREE, "Алгоритми", "Фізика", "Англійська"};
        s[2][4] = new String[]{"Англійська", "Математика", FREE, "Програмування"};

        return s;
    }

    static void printSchedule(String[][][] s) {
        for (int g = 0; g < GROUPS; g++) {
            System.out.println("Група " + GROUP_NAMES[g]);
            for (int d = 0; d < DAYS; d++) {
                String line = DAY_NAMES[d] + ": ";
                for (int l = 0; l < LESSONS; l++) {
                    line = line + "[" + (l + 1) + "] " + s[g][d][l] + "  ";
                }
                System.out.println(line);
            }
            System.out.println();
        }
    }

    static int countLessons(String[] day) {
        int count = 0;
        for (String lesson : day) {
            if (!lesson.equals(FREE)) count++;
        }
        return count;
    }

    static int countWindows(String[] day) {
        int first = -1, last = -1;
        for (int l = 0; l < day.length; l++) {
            if (!day[l].equals(FREE)) {
                if (first == -1) first = l;
                last = l;
            }
        }
        if (first == -1) return 0;

        int windows = 0;
        for (int l = first + 1; l < last; l++) {
            if (day[l].equals(FREE)) windows++;
        }
        return windows;
    }

    static void findBusiestDay(String[][][] s, int g) {
        int max = -1;
        int maxDay = -1;
        for (int d = 0; d < DAYS; d++) {
            int n = countLessons(s[g][d]);
            if (n > max) {
                max = n;
                maxDay = d;
            }
        }
        System.out.println("Найбільше навантаження у групи " + GROUP_NAMES[g] + ": "
                + DAY_NAMES[maxDay] + " (" + max + " пар)");
    }

    static void findInconvenientDays(String[][][] s, int g) {
        System.out.println("Дні з \"вікнами\" для групи " + GROUP_NAMES[g] + ":");
        boolean found = false;
        for (int d = 0; d < DAYS; d++) {
            int w = countWindows(s[g][d]);
            if (w > 0) {
                System.out.println(" - " + DAY_NAMES[d] + ": " + w + " вікно(а)");
                found = true;
            }
        }
        if (!found) {
            System.out.println(" Незручних днів (з вікнами) немає.");
        }
    }

    static void checkStreamLessons(String[][][] s) {
        System.out.println("Перевірка потокових занять:");
        boolean found = false;

        for (int d = 0; d < DAYS; d++) {
            for (int l = 0; l < LESSONS; l++) {
                boolean[] visited = new boolean[GROUPS];

                for (int g1 = 0; g1 < GROUPS; g1++) {
                    if (visited[g1]) continue;
                    String subject = s[g1][d][l];
                    visited[g1] = true;

                    if (subject.equals(FREE)) continue;

                    List<Integer> groupsWithSameSubject = new ArrayList<>();
                    groupsWithSameSubject.add(g1);

                    for (int g2 = g1 + 1; g2 < GROUPS; g2++) {
                        if (!visited[g2] && s[g2][d][l].equals(subject)) {
                            groupsWithSameSubject.add(g2);
                            visited[g2] = true;
                        }
                    }

                    if (groupsWithSameSubject.size() > 1) {
                        found = true;
                        StringBuilder sb = new StringBuilder();
                        sb.append(DAY_NAMES[d]).append(", пара ").append(l + 1)
                                .append(": \"").append(subject).append("\" одночасно у групах: ");
                        for (int i = 0; i < groupsWithSameSubject.size(); i++) {
                            sb.append(GROUP_NAMES[groupsWithSameSubject.get(i)]);
                            if (i < groupsWithSameSubject.size() - 1) sb.append(", ");
                        }
                        System.out.println(sb);
                    }
                }
            }
        }

        if (!found) {System.out.println(" Потокових занять у розкладі немає.");}
    }
}
