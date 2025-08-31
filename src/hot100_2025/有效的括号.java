package hot100_2025;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class 有效的括号 {
    public static void main(String[] args) {
        isValid("(){}}{");
//        isValid2("(){}}{");
    }

    public static boolean isValid(String s) {
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

    public static boolean isValid2(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');

        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }
}
