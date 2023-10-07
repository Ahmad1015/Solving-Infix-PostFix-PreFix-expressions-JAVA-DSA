// Cannot deal with negatives
/*Empty Input: If the input expression is empty, your program should handle this gracefully1.

Invalid Characters: Your program should be able to handle any characters that are not valid operands or operators2. For example, alphabets or special characters other than ‘+’, ‘-’, ‘*’, ‘/’.

Insufficient Operands: In postfix expressions, every operator must have exactly two operands3. If an operator does not have enough operands, this is an error1.

Extra Operands: Similarly, if there are extra operands (numbers) that do not have corresponding operators, this is also an error1.

Division by Zero: If your expression involves division, you should check for division by zero errors1.

Number Format Exception: When trying to parse a string to a number (integer, float, double), a NumberFormatException can occur if the string does not contain a parsable number.

Stack Overflow/Underflow: If you’re using a stack data structure to evaluate the postfix expression, you might encounter stack overflow or underflow errors. This can happen if you try to pop an element from an empty stack (underflow), or if you try to push an element onto a full stack (overflow). */


import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Postfix Expression: ");
        String exp = input.nextLine();

        List arr = new Stack(exp.length());
        String[] s;
        if (exp.contains(","))
            s = exp.split(",");
        else
            s = exp.split(" ");

            
        evalutePostFix(s,arr);                                            
    }
    public static void evalutePostFix(String[] s,List arr){
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
                operand1 = arr.pop();
                flag_operator=false;
                flag_okay_to_push=true;
                }
            }
            else{
            System.exit(400);
            }
            if(flag_okay_to_push){
                try{
                    if(operator_ascii==42)
                        solution = operand1 * operand2;
                    else if(operator_ascii == 45)
                        solution = operand1 - operand2;
                    else if(operator_ascii == 43)
                        solution = operand1 + operand2;
                    else if(operator_ascii == 47)
                        solution = operand1 / operand2;
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