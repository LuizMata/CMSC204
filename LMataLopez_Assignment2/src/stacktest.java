public class stacktest {

    public static void main(String args[]) throws StackOverflowException {
        char c1 = '+';
        int a=1;
        int b=2;
        String example = String.valueOf(a+c1+b);
        System.out.println(example);
    }

}
