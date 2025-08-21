/**
 * Algoritmo de ordenacao por selecao
 * @author Max do Val Machado
 * @version 3 08/2020
 */

class Selecao extends Geracao {

	/**
	 * Construtor.
	 */
	public Selecao(){
		super();
	}


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
	public Selecao(int tamanho){
		super(tamanho);
	}

	/**
	 * Algoritmo de ordenacao por selecao.
	 */
	@Override
	public void sort() {
		
		int contComp = 0;

		for (int i = 0; i < (n - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < n; j++){
				contComp++;
				if (array[menor] > array[j]){
					menor = j;
				}
			}
		
			swap(menor, i);
			
		}

		System.out.println(contComp);
	}
}
