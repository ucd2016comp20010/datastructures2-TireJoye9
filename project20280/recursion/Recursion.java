package project20280.recursion;

public class Recursion {

    int Tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 0;
        }
        if (n == 2)
        {
            return 1;
        }
        return Tribonacci(n - 1) + Tribonacci(n - 2) + Tribonacci(n - 3);
    }
    //mCcARTHY 91
    int TheMcCarthy(int n)
    {
        if (n == 91)
        {
            return 91;
        }
        if (n > 100)
        {
           return TheMcCarthy(n - 10);
        }
        return TheMcCarthy(TheMcCarthy(n + 11));
    }

    static public void main(String[] args) {
        Recursion test = new Recursion();
       // System.out.println(test.Tribonacci(9));
        System.out.println(test.TheMcCarthy(9));
    }
}

