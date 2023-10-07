/* 
Stack Overflow/Underflow: If you’re using a stack data structure to evaluate the postfix expression, you might encounter  underflow errors. This can happen if you try to pop an element from an empty stack (underflow) */


import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Postfix Expression: ");
        String exp = input.nextLine();
        exp = exp.trim();
        List arr = new Stack(exp.length());
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
            // Do function calls here    
            evalutePostFix(s,arr);    
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
                int num = Integer.parseInt(subset);
                operands++;
                continue;
            }
            catch(NumberFormatException e){
            }
            if(!subset.equals("+") && !subset.equals("-") && !subset.equals("*") && !subset.equals("/"))
                    return false;
                else
                    operators++;        
        }
        
        if(operators+1 != operands)                                                     // Handling invalid number of operators and operands
            return false;
        
        if(expression[expression.length-1].equals("+")|| expression[expression.length-1].equals("-")|| expression[expression.length-1].equals("*")||expression[expression.length-1].equals("/"))
            return true;
        else 
            return false;
        
    }

    public static void evalutePostFix (String[] s,List arr) throws Exception{
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
                operand2 = arr.pop();
                if(arr.isEmpty())
                        throw new Exception("Only 1 variable left in Stack");
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
                    System.out.println("Caught an exception: " + e.getMessage());
                }
                arr.push(solution);
                flag_okay_to_push=false;
            }
            
        }
        System.out.println("Final answer is : "+arr.pop()); 
    
    }
}

abstract class List{
    abstract boolean push(int element);
    abstract int pop();
    abstract boolean isEmpty();
    abstract boolean isFull();
}
class Stack extends List{
    int top;
    int N;                  // Total Size of Array;
    int arr[];
    public Stack(){
        top=0;
        N=5;
        arr = new int[N];
    }
    public Stack(int size){
        top = 0;
        N = size;
        arr = new int[N];
    }
    public boolean push(int element){
        if(top==N){
            System.out.println("Stack is Full- Cannot Push");
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