package bon;

import PracticalSix.VersionArray;

/*
за допомогою стека зліва на право
 */
public class Bonus<T> {
    static void main() {
        VersionArray<Integer> stack = new VersionArray<>(5);


        int a = 12345;
        while (a >= 1) {
            stack.push(a % 10);
            a /= 10;
        }

//        System.out.println(stack);
//
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }


    }


}
