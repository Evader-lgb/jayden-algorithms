package 字符串.复习旧;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * <p>
 * <a href="https://leetcode.cn/problems/valid-parentheses/description/">链接</a>
 */
public class P20_有效的括号 {
    public static void main(String[] args) {
        boolean valid = isValid("(){}}{");
        System.out.println(valid);

        boolean valid2 = isValid("(){}");
        System.out.println(valid2);
//        isValid2("(){}}{");

        // 栈是空的时候不能弹栈，会报错
//        Stack<Character> stack = new Stack<>();
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());//EmptyStackException
    }

    public static boolean isValid(String s) {
        // 临界值处理
        if (s == null || s.length()==0){
            return true;
        }

        // 哈希表建立左右括号关联
        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        // TODO 少考虑了只有右括号的场景
        if (!map.containsKey(s.charAt(0))){
            return false;
        }

        // 需要一个栈，先进后出。
        Stack<Character> stack = new Stack<>();
        stack.add('?');

        // 哈希表key含当前字符则进，不含则栈顶跟当前字符比较
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.add(c);
            }else {
                Character peek = stack.peek();
                if ('?' != peek && c == map.get(peek)){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }

        // 判断栈是不是空的
        return stack.size() == 1;
    }


    /**
     * 这个方法主要学习的是addLast和removeLast，写的时候是不知道这2个方法的
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');

        char[] charArray = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        stack.add('?');
        if (charArray.length == 0 || !map.containsKey(charArray[0])){
            return false;
        }
        for (Character c : charArray) {
            if (map.containsKey(c)){
                stack.addLast(c);
            }else if(map.get(stack.removeLast()) != c ){
                return false;
            }
        }

        return stack.size() == 1;
    }
}
