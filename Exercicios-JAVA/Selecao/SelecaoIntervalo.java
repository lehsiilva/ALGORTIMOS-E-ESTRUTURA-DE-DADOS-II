public class SelecaoIntervalo {
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

    public static void select(int[] array, int n, int inicio, int fim){
        
        for(int i = inicio; i <= fim; i++){
            int menor = i;
            for(int j = i+1; j <= fim; j++){
                if(array[menor] > array[j]){
                    menor = j;
                }
            }
            if (menor != i) {
                swap(array, menor, i);
                
            }
        }
        
        imprimir(array,n);
    }

    public static void main(String[] args) {

        int[] array={2,5,4,1,3};
        int n = array.length;

        int inicio = 1;
        int fim = 3;

        select(array,n, inicio,fim);

    }
}
