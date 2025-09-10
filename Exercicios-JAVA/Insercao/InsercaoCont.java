
public class InsercaoCont {
    public static void imprimir(int[]array,int n){
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void insertion(int[]array,int n){
        int cont = 0;
        for(int i = 1; i < n; i++){
            int temp = array[i];
            int j = i - 1;
            while((j >= 0) && (array[j] > temp)){
                array[j+1] = array[j];
                cont++;
                j--;
            }

            array[j+1] = temp;
        }
        System.out.println("Contador = " + cont);
        imprimir(array,n);
        
    }



    public static void main(String[] args) {

        int[] array={2,5,4,1,3};
        int n = array.length;

        insertion(array,n);

    }
}
