public class Bubble {
    public static void imprimir(int[]array,int n){
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void bubbleSort(int[]array, int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        imprimir(array,n);
    }

    public static void main(String[] args) {

        int[] array={2,5,4,1,3};
        int n = array.length;

        bubbleSort(array,n);

    }
}
