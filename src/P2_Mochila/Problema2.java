package P2_Mochila;

import java.util.PriorityQueue;
import java.util.Queue;

public class Problema2 {

    // Criamos a classe Nodo aninhada para árvore de branch and bound
    static class Nodo {
        int nivel;
        int pesoAtual;
        int valorAtual;
        boolean[] selecAux;

        Nodo(int nivel, int pesoAtual, int valorAtual, boolean[] selecAux) {
            this.nivel = nivel;
            this.pesoAtual = pesoAtual;
            this.valorAtual = valorAtual;
            // Cria uma cópia da array selecAux para evitar referências compartilhadas
            this.selecAux = new boolean[selecAux.length];
            for (int i = 0; i < selecAux.length; i++) {
                this.selecAux[i] = selecAux[i];
            }
        }
    }

    static int valorOtimo;
    static boolean[] selecionados;

    // Método principal para resolver o problema da mochila
    public static void solveP2(int n, int[] wi, int[] vi, int W) {
        valorOtimo = 0;
        selecionados = new boolean[n];

        // Chama o algoritmo de Branch and Bound
        branchAndBound(n, wi, vi, W);

        // Imprime os resultados com seus pesos e valores
        System.out.println("\n>>>>>>> RESULTADO MOCHILA (Com Nodo) <<<<<<<\n\nValor ótimo: " + valorOtimo + "\n");
        System.out.println("\u001b[1;3mItens selecionados:\u001b[0m");
        for (int i = 0; i < n; i++) {
            if (selecionados[i]) {
                System.out.println("Peso = " + wi[i] + ", Valor = " + vi[i]);
            }
        }
    }

    // Algoritmo de Branch and Bound
    private static void branchAndBound(int n, int[] wi, int[] vi, int W) {
        // Cria o nó raiz
        Nodo raiz = new Nodo(0, 0, 0, new boolean[n]);
        
        // Usa uma PriorityQueue para manter os nós ordenados pelo valor relativo
        Queue<Nodo> pq = new PriorityQueue<>((a, b) ->
                Double.compare((double) b.valorAtual / b.pesoAtual, (double) a.valorAtual / a.pesoAtual));

        // Adiciona o nó raiz à fila de prioridade
        pq.add(raiz);

        // Loop característico do algoritmo Branch and Boiund
        while (!pq.isEmpty()) {
            // Analiza então como nodo na cabeça da fila
            Nodo nodoAtual = pq.poll();
            int nivel = nodoAtual.nivel;
            int pesoAtual = nodoAtual.pesoAtual;
            int valorAtual = nodoAtual.valorAtual;
            boolean[] selecAux = nodoAtual.selecAux;

            // Verifica se chegou à folha da árvore
            if (nivel == n) {
                // Atualiza a solução ótima se necessário e adiciona em selecionados
                if (valorAtual > valorOtimo && pesoAtual <= W) {
                    valorOtimo = valorAtual;
                    for (int i = 0; i < n; i++) {
                        selecionados[i] = selecAux[i];
                    }
                }
            } else {
                // Ramificação com o item na mochila
                if (pesoAtual + wi[nivel] <= W) {
                    selecAux[nivel] = true;
                    Nodo filhoComItem = new Nodo(nivel + 1, pesoAtual + wi[nivel], valorAtual + vi[nivel], selecAux);
                    pq.add(filhoComItem);
                    selecAux[nivel] = false;
                }
                // Ramificação sem o item na mochila
                Nodo filhoSemItem = new Nodo(nivel + 1, pesoAtual, valorAtual, selecAux);
                pq.add(filhoSemItem);
            }
        }
    }

    // Método main
    public static void main(String[] args) {
        int W = 11;
        int[] wi = {5, 6, 2, 1, 7};
        int[] vi = {18, 22, 6, 1, 28};
        int n = wi.length;

        double startTime = System.currentTimeMillis();

        solveP2(n, wi, vi, W);

        double endTime = System.currentTimeMillis();
        double executeTime = (endTime - startTime) / 1000.0;

        // Imprime o tempo de execução calculado
        System.out.println("\nTempo de Execução: " + executeTime + " segundos\n");
    }
}
