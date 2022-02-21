package coderPad;

public class BinSearch {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6};
        int[] array2 = {2, 4, 5, 1, 3, 7};
        
        System.out.println(binSearch(array, 4));
        System.out.println(sortedArray(array2));
    }
    
    //INCOMPLETE ALGORITHM ***

    public static int[] sortedArray(int[] array){

        // Sorting BubbleSort!!!
        boolean sorted;
        do{
            sorted = false;
            for (int i = 0; i < array.length - 1; i++){
                if(array[i] > array[i + 1]){
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = true;
                }
            }
        }while (sorted);
        
        return array;
    }

    public static boolean binSearch(int[] array, int num) {

        int l = array[0];
        int r = array[array.length - 1];
        int median;

        while (r >= l) {
            median = (l + r) / 2;

            if (num == median) {
                System.out.print("found: " + num + " = ");
                return true;
            } else {
                if (num < median) {
                    r = median - 1;
                } else {
                    l = median + 1;
                }
            }
            System.out.println("iteration");
        }
        return false;
    }
}