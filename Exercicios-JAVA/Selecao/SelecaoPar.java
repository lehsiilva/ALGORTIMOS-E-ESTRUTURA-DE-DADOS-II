public class SelecaoPar {
    public static void imprimir(int[]array,int n){
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void swap(int[]array, int menor, int i){
        int temp = array[menor];
        array[menor] = array[i];
        array[i] = temp;

    }

    public static void selection(int[] array, int n){
        for(int i = 0; i < (n-1); i++){
            if(array[i]%2 == 0){
                int menor = i;
                
                for(int j = i+1; j < n; j++){
                    if(array[menor] > array[j] && array[j]%2 == 0){
                        menor = j;
                    }
                }
                if (menor != i) {
                    swap(array, menor, i);
                }
            }
        }

        imprimir(array,n);
    }

    public static void main(String[] args) {

        int[] array={4,5,2,1,3};
        int n = array.length;

        selection(array,n);

    }
}
