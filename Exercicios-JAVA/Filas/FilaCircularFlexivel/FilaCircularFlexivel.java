
public class FilaCircularFlexivel {
    class Celula{
        private int valor;
        private Celula proximo;

        public Celula(int valor){
            this.valor = valor;
            this.proximo = null;
        }

        public int getValor(){
            return valor;
        }
    }

    private Celula topo;

    public FilaCircularFlexivel(){
        this.topo = null;
    }


}
