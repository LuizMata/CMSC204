public class Notation {
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
    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}
