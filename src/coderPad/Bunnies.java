package coderPad;

public class Bunnies {

    public static void main(String[] args) {

        int bunnies = Integer.parseInt(args[0]);

        System.out.println(bunnies + " bunnies have " + bunnyEars(bunnies) + " ears");

    }

    public static int bunnyEars(int n){
        int ears = 2;

        if (n == 1)
            return 2;
        if (n < 1)
            return 0;
        else
           return bunnyEars(n-1) + ears;
    }
}