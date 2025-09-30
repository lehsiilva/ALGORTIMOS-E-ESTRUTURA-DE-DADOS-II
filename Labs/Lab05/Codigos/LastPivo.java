package Codigos;
/**
 * Algoritmo de ordenacao Quicksort
 * @author Max do Val Machado
 * @version 3 08/2020
 */
class LastPivo extends Geracao {

	/**
	 * Construtor.
	 */
   public LastPivo(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public LastPivo(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
   @Override
   public void sort() {
      QuickSortLastPivo(array,0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */
    private void QuickSortLastPivo(int[] array, int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[dir];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  QuickSortLastPivo(array,esq, j);
        if (i < dir)  QuickSortLastPivo(array,i, dir);
    }
}
