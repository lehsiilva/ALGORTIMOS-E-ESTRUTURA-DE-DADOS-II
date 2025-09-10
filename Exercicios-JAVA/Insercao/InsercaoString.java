
public class InsercaoString {
    public static void imprimir(String[]array,int n){
        for(int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void insertion(String[]array,int n){

        for(int i = 1; i < n; i++){
            String temp = array[i];
            int j = i - 1;
            while((j >= 0) && array[j].compareTo(temp) > 0){
                array[j+1] = array[j];
                j--;
            }

            array[j+1] = temp;
        }

        imprimir(array,n);
        
    }



    public static void main(String[] args) {

        String[] array={"casa","armario","domino"};
        int n = array.length;

        insertion(array,n);

    }
}
