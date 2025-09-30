package Codigos;
import java.util.Random;

import Geracao;

/**
 * Algoritmo de ordenacao Quicksort
 * @author Max do Val Machado
 * @version 3 08/2020
 */
class RandomPivo extends Geracao {

	/**
	 * Construtor.
	 */
   public RandomPivo(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public RandomPivo(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
   @Override
   public void sort() {
      QuickSortRandomPivo(array,0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */
    private void QuickSortRandomPivo(int[] array, int esq, int dir) {
        int i = esq, j = dir;
        Random rand = new Random();
        int k = esq + Math.abs(rand.nextInt() % (dir - esq + 1));
        int pivo = array[k];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  QuickSortRandomPivo(array,esq, j);
        if (i < dir)  QuickSortRandomPivo(array,i, dir);
    }
}
