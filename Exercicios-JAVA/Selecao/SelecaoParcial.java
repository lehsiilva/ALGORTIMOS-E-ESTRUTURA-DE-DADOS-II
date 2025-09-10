public class SelecaoParcial {
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

    public static void selection(int[] array, int n, int pcl){
        for(int i = 0; i < (pcl-1); i++){
            int menor = i;
            for(int j = i+1; j < pcl; j++){
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

        int[] array={5,2,4,1,3};
        int n = array.length;
        int pcl = 2;

        selection(array,n, pcl);

    }
}
