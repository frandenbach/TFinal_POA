package P2_Mochila;

public class MainMochila {

    public static void main(String[] args) {
        int n = 5;
        int[] wi = {1, 2, 5, 6, 7};
        int[] vi = {1, 6, 18, 22, 28};
        int W = 11;

        MochilaSolver.resolveMochila(n, wi, vi, W);
        System.out.print("\n");
    }
}
