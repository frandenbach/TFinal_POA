public class Problema1 {

    public static void solveP1(int[] l, int[] h) {
        int n = Math.max(l.length, h.length);

        int[][] matriz = new int[n][2];
        matriz[0][0] = 0;
        matriz[0][1] = h[0];
        matriz[0][2] = 0;

        for (int i = 1; i < n; i++) {
            matriz[i][0] = Math.max(matriz[i - 1][0], matriz[i - 1][1] - l[i]);
            matriz[i][1] = Math.max(matriz[i - 1][0] + h[i], matriz[i - 1][1]);
        }

        int maxValue = Math.max(matriz[n - 1][0], matriz[n - 1][1]);
        System.out.println("Valor máximo do plano: " + maxValue);

        int[] tasks = new int[n];
        int choice = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (choice == 0) {
                if (i == 1) {
                    choice = 1;
                }
            } else if (choice == 1) {
                choice = 2;
            }

            tasks[i] = choice;
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Semana " + (i + 1) + ": ");
            if (tasks[i] == 0) {
                System.out.println("Fazer nada");
            } else if (tasks[i] == 1) {
                System.out.println("Escolher alta dificuldade");
            } else {
                System.out.println("Escolher baixa dificuldade");
            }
        }
    }

    public static void main(String[] args) {
        int[] l = {0, 1, 10, 10}; // Nada na primeira semana
        int[] h = {5, 50, 5, 1};
        solveP1(l, h);
    }
}
