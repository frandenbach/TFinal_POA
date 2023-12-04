package P1_Cronograma;

public class Problema1 {

    public static void solveP1(int[] l, int[] h) {
        int n = Math.max(l.length, h.length);
        int[] values = new int[n];
        int[] choices = new int[n];
        int choice;

        values[0] = Math.max(l[0], h[0]);

        for (int i = 1; i < n; i++) {
            values[i] = Math.max(h[i] + (i > 1 ? values[i - 2] : 0), values[i - 1] + l[i]);
        }

        int maxValue = values[n - 1];
        System.out.println("Valor máximo do plano: " + maxValue + "\n");

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                choice = (h[i] > l[i]) ? 1 : 2;
            } else {
                if (values[i] == values[i - 1] + l[i]) {
                    choice = 2;
                } else {
                    choice = 1;
                    choices[i - 1] = 0;
                }
            }
            choices[i] = choice;
        }

        System.out.print("\u001b[1;3mPlanejamento para as próximas semanas:\u001b[0m\n");
        for (int i = 0; i < n; i++) {
            System.out.print("Semana " + (i + 1) + ": ");
            if (choices[i] == 1) {
                System.out.println("Escolher alta dificuldade");
            } else if (choices[i] == 2) {
                System.out.println("Escolher baixa dificuldade");
            } else {
                System.out.println("Fazer nada");
            }
        }
    }

    public static void main(String[] args) {
        int[] l = {10, 1, 10, 10};
        int[] h = {5, 50, 5, 1};

        System.out.println("\n>>>>>>> RESULTADO CRONOGRAMA <<<<<<<\n");
        double startTime = System.currentTimeMillis();

        solveP1(l, h);

        double endTime = System.currentTimeMillis();
        double executeTime = (endTime - startTime) / 1000.0;

        System.out.println("\nTempo de Execução: " + executeTime + " segundos\n");
    }
}
