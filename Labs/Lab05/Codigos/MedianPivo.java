
/**
 * Algoritmo de ordenacao Quicksort
 * @author Max do Val Machado
 * @version 3 08/2020
 */
class MedianPivo extends Geracao {

	/**
	 * Construtor.
	 */
   public MedianPivo(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public MedianPivo(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
   @Override
   public void sort() {
      QuickSortMedianPivo(array,0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */
    private void QuickSortMedianPivo(int[] array, int esq, int dir) {
        int i = esq, j = dir;
        int pivo1 = array[esq];
        int pivo2 = array[(dir+esq)/2];
        int pivo3 = array[dir];
        int pivo;

        if((pivo1 > pivo2 && pivo1 < pivo3) || (pivo1 < pivo2 && pivo1 > pivo3)){
         pivo = pivo1;
        }
        else if((pivo2 > pivo1 && pivo2 < pivo3) || (pivo2 < pivo1 && pivo2 > pivo3)){
         pivo = pivo2;
        }
        else{pivo = pivo3;}

        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  QuickSortMedianPivo(array,esq, j);
        if (i < dir)  QuickSortMedianPivo(array,i, dir);
    }
}
