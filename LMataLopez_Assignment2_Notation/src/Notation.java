/**
 * 
 * @author Luiz
 * This is a tool to help convert mathematical expressions from infix to postfix notation and the other way around as well.
 * It can also be used to convert the expressions into postfix and evaluate them for their numerical value.
 * By using a stack and a queue it can do each individual operation one by one.
 *
 */
public class Notation {
	
	/**
	 * Function to convert any infix notation mathematical expression into postfix
	 * @param inFix A traditional infix mathematical expression
	 * @return A mathematical expression given in postfix format (operands before and operators after)
	 * @throws InvalidNotationFormatException When the format of the input expression is not comprehensible.
	 * @throws QueueOverflowException By default if the solution queue were ever to overflow
	 * @throws StackOverflowException By default if the stack ever reaches its maximum capacity
	 * @throws StackUnderflowException By default if the stack is asked to pop an empty stack.
	 */
    public static String convertInfixToPostfix(String inFix) throws InvalidNotationFormatException, QueueOverflowException, StackOverflowException, StackUnderflowException {
        MyQueue<Character> solution = new MyQueue<>();
        MyStack<Character> characters = new MyStack<>();
        char[] equation = inFix.toCharArray();
        for(char item:equation){
            if(Character.isDigit(item)){
                solution.enqueue(item);
            }

            if(item == '('){
                characters.push(item);
            }

            if(isOperator(item)){
                if(characters.isEmpty()){
                    throw new InvalidNotationFormatException();
                }
                while(isOperator(characters.top())) {
                    if (item == '+' || item == '-') {
                        char stackitem = characters.pop();
                        solution.enqueue(stackitem);
                    }
                    if ((characters.top() == '*' || characters.top() == '/') && (item == '*' || item == '/')) {
                        char stackitem = characters.pop();
                        solution.enqueue(stackitem);
                    }
                }
                characters.push(item);
            }

            if(item == ')'){
                while(characters.top() != '('){
                    char stackitem = characters.pop();
                    solution.enqueue(stackitem);
                }
                if(characters.top() == '('){
                    characters.pop();
                }
                else{
                    throw new InvalidNotationFormatException();
                }
            }
        }
        return solution.toString();
    }
    
    /**
     * Function to convert any postfix notation mathematical expression into infix notation, reverse operation of infixToPostfix
     * @param postFix A postfix mathematical expression
     * @return A traditional mathematical expression given in infix format (operators between operands)
     * @throws InvalidNotationFormatException When the format of the input expression is not comprehensible.
     * @throws StackOverflowException By default if the stack ever reaches its maximum capacity
	 * @throws StackUnderflowException By default if the stack is asked to pop an empty stack.
     */
    public static String convertPostfixToInfix(String postFix) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException {
        MyStack<String> characters = new MyStack<>();
        char[] equation = postFix.toCharArray();

        for(char item:equation){
            if (Character.isDigit(item) || item == '(') {
                characters.push(String.valueOf(item));
            }

            if(isOperator(item)){
                if(characters.size()<2){
                    throw new InvalidNotationFormatException();
                }
                else{
                    String s1 = characters.pop();
                    String s2 = characters.pop();
                    String complete = "(" + s2 + item + s1 + ")";
                    characters.push(complete);
                }
            }
        }
        if(characters.size() != 1){
            throw new InvalidNotationFormatException();
        }
        return characters.pop();
    }

    /**
     * Function that evaluates a postfix mathematical expression and returns a numerical value as a double
     * @param postFix A postfix mathematical expression
     * @return A double value which represents the evaluation of the input equation
     * @throws InvalidNotationFormatException When the format of the input expression is not comprehensible.
     * @throws StackOverflowException By default if the stack ever reaches its maximum capacity
	 * @throws StackUnderflowException By default if the stack is asked to pop an empty stack.
     */
    public static double evaluatePostfixExpression(String postFix) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException {
        MyStack<String> characters = new MyStack<>();
        char[] equation = postFix.toCharArray();

        for(char item:equation){
            if(item == ' '){
                continue;
            }

            if(Character.isDigit(item) || item == '('){
                characters.push(String.valueOf(item));
            }

            if(isOperator(item)){

                if(characters.size()<2){
                    throw new InvalidNotationFormatException();
                }
                else{
                    String a = characters.pop();
                    String b = characters.pop();
                    double first = Double.parseDouble(b);
                    double second = Double.parseDouble(a);
                    double answer;

                    switch(item){
                        case '+': answer = first+second;
                            break;
                        case '-': answer = first-second;
                            break;
                        case '*': answer = first*second;
                            break;
                        case '/': answer = first/second;
                            break;
                        default: answer = 0;

                    }
                    characters.push(Double.toString(answer));
                }

            }

        }
        if(characters.size() != 1){
            throw new InvalidNotationFormatException();
        }
        return Double.parseDouble(characters.pop());
    }
    
    /**
     * Function to determine whether a character is an operator
     * @param c any character that is part of a mathematical expression
     * @return true/false whether or not it is an operator
     */
    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}