package P1_Cronograma;

public class Problema1 {
    
    // Interface principal solveP1
    public static void solveP1(int[] l, int[] h) {
        // Optamos por definir o tamanho de n pelo maior cumprimento entre as entradas
        int n = Math.max(l.length, h.length);
        int[] values = new int[n];
        int[] choices = new int[n];
        // Criamos uma lista auxiliar de escolhas que armazena os dados das escolhas feitas
        int[] escolhas = new int[n];
        int choice;

        // Inicialia a lista de valores com o maior valor dentre os primeiros
        values[0] = Math.max(l[0], h[0]);

        // Acumula o valores dentre o maior de cada iteração
        for (int i = 1; i < n; i++) {
            values[i] = Math.max(h[i] + (i > 1 ? values[i - 2] : 0), values[i - 1] + l[i]);
        }

        // Imprime o valor máximo, que é o último adicionado em values
        int maxValue = values[n - 1];
        System.out.println("\n>>>>>>> RESULTADO CRONOGRAMA <<<<<<<\n\nValor máximo do plano: " + maxValue + "\n");

        // Faz uma iteração em que adiciona o número relativo às escolhas na lista choices,
        // fazendo com que tome as escolhas apropriadas, caso o valor da escolha de alta
        // dificuldade some o mesmo no valor máximo do que a escolha de baixa dificuldade
        // da vez, ele adiciona o de baixa, caso o valor máximo aumente com a escolha alta,
        // ele substitui o valor anterior por 0 e opta pela escolha de alta dificuldade.
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

            // Aramazena então as informações das escolhas tomadas
            escolhas[i] = (choice == 1) ? h[i] : l[i];
        }

        // Imprime o planejamento, com 1 sendo de alta dificuldade, 2 de baixa e 0 fazer nada e os valores relativos as escolhas tomadas.
        System.out.print("\u001b[1;3mPlanejamento para as próximas semanas:\u001b[0m\n");
        for (int i = 0; i < n; i++) {
            System.out.print("Semana " + (i + 1) + ": ");
            if (choices[i] == 1) {
                System.out.println("Escolher alta dificuldade, Valor da Tarefa: " + escolhas[i]);
            } else if (choices[i] == 2) {
                System.out.println("Escolher baixa dificuldade, Valor da Tarefa: " + escolhas[i]);
            } else {
                System.out.println("Fazer nada");
            }
        }
    }

    public static void main(String[] args) {
        int[] l = {10, 1, 10, 10};
        int[] h = {5, 50, 5, 1};

        double startTime = System.currentTimeMillis();

        solveP1(l, h);

        double endTime = System.currentTimeMillis();
        double executeTime = (endTime - startTime) / 1000.0;

        // Imprime o tempo de execução
        System.out.println("\nTempo de Execução: " + executeTime + " segundos\n");
    }
}
