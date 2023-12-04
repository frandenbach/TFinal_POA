package P2_Mochila;

public class Problema2 {

    static int valorOtimo;
    static boolean[] selecionados;

    public static void solveP2(int n, int[] wi, int[] vi, int W) {
        valorOtimo = 0;
        selecionados = new boolean[n];
        
        branchAndBound(0, 0, 0, n, wi, vi, W, new boolean[n]);

        System.out.println("\n>>>>>>> RESULTADO MOCHILA <<<<<<<\n\nValor ótimo: " + valorOtimo + "\n");

        System.out.println("\u001b[1;3mItens selecionados:\u001b[0m");
        for (int i = 0; i < n; i++) {
            if (selecionados[i]) {
                System.out.println("Peso = " + wi[i] + ", Valor = " + vi[i]);
            }
        }
    }

    private static void branchAndBound(int nivel, int pesoAtual, int valorAtual, 
                                        int n, int[] wi, int[] vi, int W, boolean[] selecAux) {
        if (nivel == n) {
            if (valorAtual > valorOtimo && pesoAtual <= W) {
                valorOtimo = valorAtual;
                for (int i = 0; i < n; i++) {
                    selecionados[i] = selecAux[i];
                }
            }
            return;
        }

        if(pesoAtual + wi[nivel] <= W) {
            selecAux[nivel] = true;
            branchAndBound(nivel + 1, pesoAtual + wi[nivel], valorAtual + vi[nivel], n, wi, vi, W, selecAux);
            selecAux[nivel] = false;
        }

        branchAndBound(nivel + 1, pesoAtual, valorAtual, n, wi, vi, W, selecAux);
    }

    public static void main(String[] args) {
        int W = 11;
        int[] wi = {5, 6, 2, 1, 7};
        int[] vi = {18, 22, 6, 1, 28};
        int n = wi.length;

        double startTime = System.currentTimeMillis();

        solveP2(n, wi, vi, W);

        double endTime = System.currentTimeMillis();
        double executeTime = (endTime - startTime) / 1000.0;

        System.out.println("\nTempo de Execução: " + executeTime + " segundos\n");
    }
}

