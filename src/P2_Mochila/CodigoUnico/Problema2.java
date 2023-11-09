package P2_Mochila.CodigoUnico;
import java.util.ArrayList;

public class Problema2 {
    
    public static class Item {
        int peso;
        int valor;

        public Item(int peso, int valor) {
            this.peso = peso;
            this.valor = valor;
        }
    }

    public static void resolveMochila(int n, int[] wi, int[] vi, int W) {
        int[][] itens = new int[n + 1][W + 1];
        ArrayList<Item> selecionados = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wi[i - 1] <= w) {
                    itens[i][w] = Math.max(itens[i - 1][w], vi[i - 1] + itens[i - 1][w - wi[i - 1]]);
                } else {
                    itens[i][w] = itens[i - 1][w];
                }
            }
        }

        int i = n;
        int w = W;
        while (i > 0 && w > 0) {
            if (itens[i][w] != itens[i - 1][w]) {
                selecionados.add(new Item(wi[i - 1], vi[i - 1]));
                w -= wi[i - 1];
            }
            i--;
        }

        System.out.println("\n>>>>>>> RESULTADO MOCHILA <<<<<<<\n\nValor ótimo: " + itens[n][W] + "\n");
        System.out.println("Itens selecionados:\n");
        for (Item item : selecionados) {
            System.out.println("• Peso: " + item.peso + " kg, Valor: " + item.valor + "R$\n");
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] wi = {1, 2, 5, 6, 7};
        int[] vi = {1, 6, 18, 22, 28};
        int W = 11;

        resolveMochila(n, wi, vi, W);
    }
}
