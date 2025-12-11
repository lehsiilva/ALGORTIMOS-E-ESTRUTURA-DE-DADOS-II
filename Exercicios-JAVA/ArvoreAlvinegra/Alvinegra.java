class NoAN {
  public boolean cor;
  public int elemento;
  public NoAN esq, dir;

  public NoAN() {
    this(-1);
  }

  public NoAN(int elemento) {
    this(elemento, false, null, null);
  }

  public NoAN(int elemento, boolean cor) {
    this(elemento, cor, null, null);
  }

  public NoAN(int elemento, boolean cor, NoAN esq, NoAN dir) {
    this.cor = cor;
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

public class Alvinegra {
   private NoAN raiz; // Raiz da arvore.

   public Alvinegra() {
      raiz = null;
   }

   
   public boolean pesquisar(int elemento) {
      return pesquisar(elemento, raiz);
   }

  
   private boolean pesquisar(int elemento, NoAN i) {
      boolean resp;
      if (i == null) {
         resp = false;
      } else if (elemento == i.elemento) {
         resp = true;
      } else if (elemento < i.elemento) {
         resp = pesquisar(elemento, i.esq);
      } else {
         resp = pesquisar(elemento, i.dir);
      }
      return resp;
   }

   boolean isNoTipo4(NoAN i){
   return (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true);
   }
  
   public void caminharCentral() {
      System.out.print("[ ");
      caminharCentral(raiz);
      System.out.println("]");
   }


   private void caminharCentral(NoAN i) {
      if (i != null) {
         caminharCentral(i.esq); // Elementos da esquerda.
         System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
         caminharCentral(i.dir); // Elementos da direita.
      }
   }

   
   public void caminharPre() {
      System.out.print("[ ");
      caminharPre(raiz);
      System.out.println("]");
   }

   private void caminharPre(NoAN i) {
      if (i != null) {
         System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
         caminharPre(i.esq); // Elementos da esquerda.
         caminharPre(i.dir); // Elementos da direita.
      }
   }


   public void caminharPos() {
      System.out.print("[ ");
      caminharPos(raiz);
      System.out.println("]");
   }

   private void caminharPos(NoAN i) {
      if (i != null) {
         caminharPos(i.esq); // Elementos da esquerda.
         caminharPos(i.dir); // Elementos da direita.
         System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
      }
   }


   public void inserir(int elemento) throws Exception {
      // Se a arvore estiver vazia
      if (raiz == null) {
         raiz = new NoAN(elemento);

      // Senao, se a arvore tiver um elemento
      } else if (raiz.esq == null && raiz.dir == null) {
         if (elemento < raiz.elemento) {
            raiz.esq = new NoAN(elemento);
           
         } else {
            raiz.dir = new NoAN(elemento);
            
         }

      // Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null) {
         if (elemento < raiz.elemento) {
            raiz.esq = new NoAN(elemento);
         
         } else if (elemento < raiz.dir.elemento) {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = elemento;

         } else {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = raiz.dir.elemento;
            raiz.dir.elemento = elemento;
            
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null) {
         if (elemento > raiz.elemento) {
            raiz.dir = new NoAN(elemento);
            

         } else if (elemento > raiz.esq.elemento) {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = elemento;
            

         } else {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = raiz.esq.elemento;
            raiz.esq.elemento = elemento;
            
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, a arvore tem tres ou mais elementos
      } else {
         inserir(elemento, null, null, null, raiz);
      }
      raiz.cor = false;
   }

   private void inserir(int elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
      if (i == null) {
         if (elemento < pai.elemento) {
            i = pai.esq = new NoAN(elemento, true);
         } else {
            i = pai.dir = new NoAN(elemento, true);
         }
         if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
         }
      } else {
         // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
               i.cor = false;
            } else if (pai.cor == true) {
               balancear(bisavo, avo, pai, i);
            }
         }
         if (elemento < i.elemento) {
            inserir(elemento, avo, pai, i, i.esq);
         } else if (elemento > i.elemento) {
            inserir(elemento, avo, pai, i, i.dir);
         } else {
            throw new Exception("Erro inserir (elemento repetido)!");
         }
      }
   }

   private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
      // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if (pai.cor == true) {
         // 4 tipos de reequilibrios e acoplamento
         if (pai.elemento > avo.elemento) { // rotacao a esquerda ou direita-esquerda
            if (i.elemento > pai.elemento) {
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }
         } else { // rotacao a direita ou esquerda-direita
            if (i.elemento < pai.elemento) {
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }
         if (bisavo == null) {
            raiz = avo;
         } else if (avo.elemento < bisavo.elemento) {
            bisavo.esq = avo;
         } else {
            bisavo.dir = avo;
         }
         // reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
         
      } // if(pai.cor == true)
   }

   private NoAN rotacaoDir(NoAN no) {
      System.out.println("Rotacao DIR(" + no.elemento + ")");
      NoAN noEsq = no.esq;
      NoAN noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private NoAN rotacaoEsq(NoAN no) {
      System.out.println("Rotacao ESQ(" + no.elemento + ")");
      NoAN noDir = no.dir;
      NoAN noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private NoAN rotacaoDirEsq(NoAN no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private NoAN rotacaoEsqDir(NoAN no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }
}

class Main {
   public static void main(String[] args) throws Exception {
      Alvinegra arvore = new Alvinegra();

      /*
       * arvore.inserir(1);
       * arvore.inserir(2);
       * arvore.inserir(3);
       * arvore.mostrarPre();
       * 
       * arvore = new Alvinegra();
       * arvore.inserir(1);
       * arvore.inserir(3);
       * arvore.inserir(2);
       * arvore.mostrarPre();
       * 
       * arvore = new Alvinegra();
       * arvore.inserir(2);
       * arvore.inserir(1);
       * arvore.inserir(3);
       * arvore.mostrarPre();
       * 
       * arvore = new Alvinegra();
       * arvore.inserir(2);
       * arvore.inserir(3);
       * arvore.inserir(1);
       * arvore.mostrarPre();
       * 
       * arvore = new Alvinegra();
       * arvore.inserir(3);
       * arvore.inserir(1);
       * arvore.inserir(2);
       * arvore.mostrarPre();
       * 
       * arvore = new Alvinegra();
       * arvore.inserir(3);
       * arvore.inserir(2);
       * arvore.inserir(1);
       * arvore.mostrarPre();
       */
      arvore.inserir(4);
      arvore.caminharPre();
      arvore.inserir(35);
      arvore.caminharPre();
      arvore.inserir(10);
      arvore.caminharPre();
      arvore.inserir(13);
      arvore.caminharPre();
      arvore.inserir(3);
      arvore.caminharPre();
      arvore.inserir(30);
      arvore.caminharPre();
      arvore.inserir(15);
      arvore.caminharPre();
      arvore.inserir(12);
      arvore.caminharPre();
      arvore.inserir(7);
      arvore.caminharPre();
      arvore.inserir(40);
      arvore.caminharPre();
      arvore.inserir(20);
      arvore.caminharPre();

      /*
       * arvore = new Alvinegra();
       * Random gerador = new Random();
       * gerador.setSeed(4);
       * for(int i = 0; i < 4000; i++){
       * int elemento;
       * do {
       * elemento = ((int)Math.abs(gerador.nextInt())) % 100000;
       * } while (arvore.pesquisar(elemento) == true);
       * arvore.inserir(elemento);
       * arvore.mostrarPre();
       * }
       */
   }
}