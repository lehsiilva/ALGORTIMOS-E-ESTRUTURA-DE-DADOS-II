public class Algebra {

    // Operações booleanas
    public static int e(int a, int b) { 
        return (a == 1 && b == 1) ? 1 : 0; // AND lógico
    }
    public static int ou(int a, int b) { 
        return (a == 1 || b == 1) ? 1 : 0; // OR lógico
    }
    public static int nao(int a) { 
        return (a == 1) ? 0 : 1; // NOT lógico
    }

    // Avalia a expressão booleana
    public static int avalia(String expressao, int[] vals) {
        String temp = "";
        // Substitui variáveis pelos valores correspondentes
        for (int i = 0; i < expressao.length(); i++) {
            char ch = expressao.charAt(i);
            if (ch >= 'A' && ch <= 'Z') temp += (char)(vals[ch - 'A'] + '0'); // A-Z
            else temp += ch; // mantém operadores
        }

        int pos = 0;
        // Enquanto houver operadores na expressão
        while (temp.indexOf("not") != -1 || temp.indexOf("and") != -1 || temp.indexOf("or") != -1) {
            pos = temp.indexOf("not");
            if (pos != -1) {
                int v = temp.charAt(pos + 4) - '0'; // valor após "not"
                int r = nao(v);
                temp = temp.substring(0, pos) + r + temp.substring(pos + 6);
                continue; // continua para o próximo operador
            }
            pos = temp.indexOf("and");
            if (pos != -1) {
                int v1 = temp.charAt(pos + 4) - '0'; // valor à esquerda
                int v2 = temp.charAt(pos + 7) - '0'; // valor à direita
                int r = e(v1, v2);
                temp = temp.substring(0, pos) + r + temp.substring(pos + 9);
                continue;
            }
            pos = temp.indexOf("or");
            if (pos != -1) {
                int v1 = temp.charAt(pos + 3) - '0';
                int v2 = temp.charAt(pos + 6) - '0';
                int r = ou(v1, v2);
                temp = temp.substring(0, pos) + r + temp.substring(pos + 8);
            }
        }

        return temp.charAt(0) - '0'; // retorna resultado final 1/0
    }

    public static void main(String[] args) {
        int n = MyIO.readInt(); 

        while (n > 0) {
            int[] vals = new int[26]; // valores A-Z
            for (int i = 0; i < n; i++) vals[i] = MyIO.readInt();

            String expr = MyIO.readLine(); 
            int resp = avalia(expr, vals); 

            MyIO.println(resp); 

            n = MyIO.readInt(); 
        }
    }
}
