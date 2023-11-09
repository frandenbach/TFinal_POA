package P1_Cronograma.CodigoUnico;
public class Problema1 {

    public static void resolvePlanejamento(int[] l, int[] h) {
        int n = Math.max(l.length, h.length);
        int[] values = new int[n];
        int[] choices = new int[n];
        int choice;

        values[0] = Math.max(l[0], h[0]);

        for (int i = 1; i < n; i++) {
            values[i] = Math.max(h[i] + (i > 1 ? values[i - 2] : 0), values[i - 1] + l[i]);
        }

        int maxValue = values[n - 1];
        System.out.println("Valor máximo do plano: " + maxValue);

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
        resolvePlanejamento(l, h);
    }
}
