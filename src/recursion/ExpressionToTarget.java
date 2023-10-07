package recursion;
import java.util.ArrayList;
import java.util.Stack;

public class ExpressionToTarget {
    public static void main(String[] args) {
        System.out.println(generate_all_expressions("5478963212621", 54789632147L));
    }

    
    static ArrayList<String> generate_all_expressions(String s, Long target) {
        ArrayList<String> res = new ArrayList<String>();
        String strWithBlanks = "";
        for(char c : s.toCharArray()){
            strWithBlanks = strWithBlanks+c+"_";
        }
        strWithBlanks = strWithBlanks.substring(0,strWithBlanks.length()-1);
        helper(res, strWithBlanks, target);
        return res;
    }
    
    static void helper(ArrayList<String> res,String slate, Long target){
        if(slate.indexOf("_")==-1){
            long sum = evaluateExpr(slate);
            if(sum == target.longValue()){
                res.add(slate);
                return;
            }
        }
        int pos = slate.indexOf("_");
        if(pos>0){
            //join
            String joinStr=slate.substring(0,pos)+slate.substring(pos+1);
            helper(res,joinStr,target);
            
            //sum
            String plusStr=slate.substring(0,pos)+'+'+slate.substring(pos+1);
            helper(res,plusStr,target);

            //product
            String multiStr=slate.substring(0,pos)+'*'+slate.substring(pos+1);
            helper(res,multiStr,target);
        }
    }
    
    public static long evaluateExpr(String expression) {
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

    public static boolean isLessPrecedence(char incoming, char peekVal) {
        if (incoming == '*' && peekVal == '+' ) {
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
