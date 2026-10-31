package PracticalSix;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("радар"));              // true
        System.out.println(isPalindrome("level"));              // true
        System.out.println(isPalindrome("hello"));              // false
        System.out.println(isPalindrome("А роза упала на лапу Азора")); // true
        System.out.println(isPalindrome("Java"));               // false
    }

    public static boolean isPalindrome(String text) {
        String s = text.toLowerCase().replace(" ", "");

        VersionArray<Character> stack = new VersionArray<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            char fromStack = stack.pop();
            if (fromStack != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}