import java.util.Stack;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        //System.out.println("Enter Expression: ");
        //String exp = input.nextLine();
        //exp = exp.trim();
        String exp = "( 1 + 2 ) * ( 3 + 4 )";
        exp = exp.trim();
        List arr = new StackList(exp.length());
        String[] s;
        if (exp.contains(","))
            s = exp.split(",");
        else
            s = exp.split(" ");
        
        if(!isValid(s)){                                                                  
            System.out.println("Invalid Input - Exiting with Code 1");
            System.exit(1);
        }
        try{
            // Do the evaluation by function call
            //float result = postfixEvaluation(s,arr);
            //float result2 = prefixEvaluation(s, arr); 
            //System.out.println("Postfix Evaluation: "+result);
            //System.out.println("Prefix Evaluation: "+result2);
            System.out.println(infixToPostfix(s, arr));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }                                       
    }

    public static Boolean isValid(String[] expression){                   
        if(expression.length==0)                                                            // Handling Empty Input
            return false;

        int operators=0;
        int operands=0;
        for(String subset : expression){                                                    // Handling Invalid Characters and operators
            try{
                Integer.parseInt(subset);
                operands++;
                continue;
            }
            catch(NumberFormatException e){
            }
            if(!subset.equals("+") && !subset.equals("-") && !subset.equals("*") && !subset.equals("/") && !subset.equals("(") && !subset.equals(")") && !subset.equals("{") && !subset.equals("}") && !subset.equals("[") && !subset.equals("]"))
                    return false;
                else
                    if(!subset.equals("(") && !subset.equals(")") && !subset.equals("{") && !subset.equals("}") && !subset.equals("[") && !subset.equals("]"))
                        operators++;
    
                                
        }
        
        if(operators+1 != operands)                                                     // Handling invalid number of operators and operands
            return false;
        int openBracket=0,closedBracket=0,openBraces=0,closedbraces=0,openSquareBracket=0,closedSquareBracket=0;
        for(String subset : expression){
            try{
                Integer.parseInt(subset);
            }
            catch(NumberFormatException e ){
                
                int operator = subset.charAt(0);
                int operator_ascii = (int) operator;
                if(operator_ascii == 40){openBracket+=1;}
                if(operator_ascii == 41){closedBracket+=1;}
                if(operator_ascii == 123){openBraces+=1;}
                if(operator_ascii == 125){closedbraces+=1;}
                if(operator_ascii == 91){openSquareBracket+=1;}
                if(operator_ascii == 93){closedSquareBracket+=1;}
                
            }
        }
        if(openBracket != closedBracket || openBraces != closedbraces || openSquareBracket != closedSquareBracket)
                    return false;
        return true;
        
    }

    /**
     * 
     * Here's how it works:
     * 1. Reverse the prefix expression: Since stack uses LIFO (Last In First Out) property, we need to reverse the prefix expression.
     * 2. Scan the reversed expression: Scan the reversed expression from left to right.
     *    - Operand: If the scanned character is an operand, push it onto the stack.
     *    - Operator: If the scanned character is an operator, pop two elements from the stack, perform the operation and push the result back onto the stack.
     * 3. At the end of the scanning, the stack will contain the final result of the prefix expression.
     */
    public static float prefixEvaluation(String[] s, List arr) throws Exception{
        if(s[s.length-1].equals("+") || s[s.length-1].equals("-") || s[s.length-1].equals("*") || s[s.length-1].equals("/"))
            throw new Exception("Invalid Expression for PreFix");
                                                                                // Reversing the Order of Prefix expression by using built in Stack
        Stack<String> stack = new Stack<>();
        for(String element: s){ 
            stack.push(element);
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = stack.pop();
        }
        return operations(true, s,arr);
    }

    public static float postfixEvaluation (String[] s,List arr) throws Exception{
        if(!s[s.length-1].equals("+") && !s[s.length-1].equals("-") && !s[s.length-1].equals("*") && !s[s.length-1].equals("/"))
            throw new Exception("Invalid Expression for PostFix");
        return operations(false,s,arr);
    }

    //  Important note : In postfix , we first pop operand 2 , in prefix we first pop operand 1
    public static float operations(boolean flag,String[] s,List arr) throws Exception{
        int num=0,operand1=0,operand2=0,operator_ascii=0,solution=0;
        char operator;
        boolean flag_num=false;
        boolean flag_operator=false; 
        boolean flag_okay_to_push = false;

        for(int i=0;i<s.length;i++){
            try{                                    // Checking for integer
                num = Integer.parseInt(s[i]);
                flag_num = true;
            }
            catch(NumberFormatException e){
                operator = s[i].charAt(0);
                operator_ascii = (int) operator;
                flag_operator = true;
            }
            if(flag_num){
                arr.push(num);
                flag_num = false;
            }
            else if (flag_operator){
                if(arr.isEmpty())
                    System.out.println(arr.toString());
                else{
                    if(flag)
                        operand1 = arr.pop();
                    else
                        operand2 = arr.pop();
                if(arr.isEmpty())
                        throw new Exception("Only 1 variable left in StackList");
                if(flag)
                    operand2 = arr.pop();
                else
                    operand1 = arr.pop();
                flag_operator=false;
                flag_okay_to_push=true;
                }
            }
            else{
            System.exit(1);
            }
            if(flag_okay_to_push){
                try{
                    if(operator_ascii==42)
                        solution = operand1 * operand2;
                    else if(operator_ascii == 45)
                        solution = operand1 - operand2;
                    else if(operator_ascii == 43)
                        solution = operand1 + operand2;
                    else if(operator_ascii == 47){
                        try{
                            solution = operand1 / operand2;
                        }
                        catch(ArithmeticException e){
                            throw new Exception("Divison by Zero");
                        }
                    }
                        
                    else
                        throw new Exception("Invalid Operator");
                }
                catch(Exception e){
                    String str = "Caught an exception: " + e.getMessage();
                    throw new Exception(str);
                }
                arr.push(solution);
                flag_okay_to_push=false;
            }
            
        }
        return arr.pop();
    }

    public static String infixToPostfix(String[] s,List arr) throws Exception{
        int num=0,operator_ascii=0;
        char operator;
        boolean flag_operator=false; 
        
        String postFix="";

        for(int i=0;i<s.length;i++){
            try{                                    // Checking for integer
                num = Integer.parseInt(s[i]);
                postFix = postFix+" "+ num;
                flag_operator = false;
                continue;
            }
            catch(NumberFormatException e){
                operator = s[i].charAt(0);
                operator_ascii = (int) operator;
                flag_operator = true;
            }

            if (flag_operator){
                int temp=0;
                if(operator_ascii==40)                      // add support for other brackets here later
                    {
                        arr.push(operator_ascii);
                        flag_operator = false;
                        continue;}
                else{
                    try{
                        temp = arr.pop();           // Getting the top of stack
                        arr.push(temp);
                    }
                    catch(Exception e){
                        arr.push(operator_ascii);
                        temp = arr.pop();           // Getting the top of stack
                        arr.push(temp);
                        continue;
                    }
                }
                

                if(temp == 40 )
                    arr.push(operator_ascii);
                else{
                    if ((temp == 42 || temp == 47) && (operator_ascii == 42 || operator_ascii == 47)){
                        arr.pop();
                        postFix = postFix+ " " + (char)temp;
                        arr.push(operator_ascii);
                    }
                    else if ((temp == 43 || temp == 45) && (operator_ascii == 43 || operator_ascii == 45)){
                        arr.pop();
                        postFix = postFix + " " + (char)temp;
                        arr.push(operator_ascii);
                    }
                    else if ((temp == 43 || temp == 45) && (operator_ascii == 42 || operator_ascii == 47)){
                        arr.push(operator_ascii);
                        continue;
                    }
                    else if(operator_ascii == 41){
                        while(true){
                            int temp2 = arr.pop();
                            if (temp2 == 40)
                                break;
                            else{
                                postFix = postFix + " " + (char)temp2;
                            }
                        }
                    }
                }
                
                
                
                
                
                flag_operator = false;



            }

    }
    if(!arr.isEmpty()){
        while(true){
            try{
                int temp = arr.pop();
                postFix = postFix + " " + (char)temp;
            }
            catch(Exception e){
                break;
            }
                
        }
    }
    return postFix.trim();

}

}

abstract class List{
    abstract boolean push(int element);
    abstract int pop();
    abstract boolean isEmpty();
    abstract boolean isFull();
}
class StackList extends List{
    int top;
    int N;                  // Total Size of Array;
    int arr[];

    public StackList(){
        top=0;
        N=5;
        arr = new int[N];
    }
    public StackList(int size){
        top = 0;
        N = size;
        arr = new int[N];
    }
    public boolean push(int element){
        if(top==N){
            System.out.println("StackList is Full- Cannot Push");
            return false;
        }
        else{
            arr[top]=element;
            top++;
            return true;
        }
    }
    public int pop(){
        int element_to_return = arr[top-1];
        arr[top-1] = 0;
        top--;
        return element_to_return;
    }
    public boolean isEmpty(){
        if(top == 0)
            return true;
        else
            return false;
    }
    public boolean isFull(){
            return top==N;
    }
    @Override
    public String toString(){
        String stringElement = "";
        for(int i=0;i<top;i++){
            stringElement = stringElement+" "+String.valueOf(arr[i]);
        }
        return stringElement+=" -> null";
    }

}