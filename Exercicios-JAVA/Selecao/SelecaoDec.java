public class SelecaoDec {
    
    public static void imprimir(int[]array,int n){
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void swap(int[]array, int maior, int i){
        int temp = array[maior];
        array[maior] = array[i];
        array[i] = temp;

    }

    public static void select(int[] array, int n){

        for(int i = 0; i < (n-1); i++){
            int maior = i;
            for(int j = i+1; j < n; j++){
                if(array[j] > array[maior]){
                    maior = j;
                }
            }

            if (maior != i) {
                swap(array, maior, i);
            }
        }

        imprimir(array,n);
    }

    public static void main(String[] args) {

        int[] array={2,5,4,1,3};
        int n = array.length;

        select(array,n);

    }
}
