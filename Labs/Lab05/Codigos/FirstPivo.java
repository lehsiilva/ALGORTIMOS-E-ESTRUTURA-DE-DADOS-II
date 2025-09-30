
/**
 * Algoritmo de ordenacao Quicksort
 * @author Max do Val Machado
 * @version 3 08/2020
 */
class FirstPivo extends Geracao {

	/**
	 * Construtor.
	 */
   public FirstPivo(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public FirstPivo(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
   @Override
   public void sort() {
      QuickSortFirstPivo(array,0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */
    private void QuickSortFirstPivo(int[] array, int esq, int dir) {
        int i = esq+1, j = dir;
        int pivo = array[esq];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  QuickSortFirstPivo(array,esq, j);
        if (i < dir)  QuickSortFirstPivo(array,i, dir);
    }
}
