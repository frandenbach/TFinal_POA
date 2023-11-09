package P2_Mochila;
import java.util.ArrayList;

public class MochilaSolver {

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

        System.out.println("\n>>>>>>> SOLUÇÃO MOCHILA <<<<<<<\n\nValor ótimo: " + itens[n][W] + "\n");
        System.out.println("\u001b[1;3mItens selecionados:\u001b[0m\n");
        for (Item item : selecionados) {
            System.out.println("• Peso: " + item.peso + " kg, Valor: " + item.valor + "R$");
        }
    }
}
