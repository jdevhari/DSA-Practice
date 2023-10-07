import java.util.ArrayList;
import java.util.Stack;

public class Eval {
    public static void main(String[] args) {
        System.out.println(generate_all_expressions("5478963212621", 54789632147L));
        //System.out.println(ev("2+2*3"));
    }

    
    static ArrayList<String> generate_all_expressions(String s, Long target) {
        ArrayList<String> res = new ArrayList<String>();
        String upd = "";
        for(char c : s.toCharArray()){
            upd = upd+c+"_";
        }
        upd = upd.substring(0,upd.length()-1);
        helper(res, upd, target);
        return res;
    }
    
    static void helper(ArrayList<String> res,String s, Long target){
        if(s.indexOf("_")==-1){
            long sum = ev(s);
            if(sum == target.longValue()){
                res.add(s);
                return;
            }
        }
        int pos = s.indexOf("_");
        if(pos>0){
            //join
            String joinStr=s.substring(0,pos)+s.substring(pos+1);
            helper(res,joinStr,target);
            
            //plus
            String plusStr=s.substring(0,pos)+'+'+s.substring(pos+1);
            helper(res,plusStr,target);

            //multi
            String multiStr=s.substring(0,pos)+'*'+s.substring(pos+1);
            helper(res,multiStr,target);
        }
    }
    
    public static long ev(String expression) {
        Stack<Long> operands = new Stack<Long>();
        Stack<Character> operators = new Stack<Character>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            if (Character.isDigit(ch)) {
                long num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + Character.getNumericValue(expression.charAt(i));
                    i++;
                }
                i--;
                operands.push(num);
            }
            else if (ch == '+' || ch == '*') {
                while (!operators.empty() && isLessPrecedence(ch, operators.peek())) {
                    operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.push(ch);
            }
        }
         
        while (!operators.empty()) {
            operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()));
        }
         
        return operands.pop();
    }

    public static boolean isLessPrecedence(char op1, char op2) {
        if (op1 == '*' && op2 == '+' ) {
            return false;
        }
        else {
            return true;
        }
    }
     
    public static long applyOperation(char op, long b, long a) {
        switch (op) {
            case '+':
                return a + b;
            case '*':
                return a * b;
        }
        return 0;
    }
}
