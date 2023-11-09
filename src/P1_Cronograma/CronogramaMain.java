package P1_Cronograma;

public class CronogramaMain {

    public static void main(String[] args) {
        int[] l = {10, 1, 10, 10};
        int[] h = {5, 50, 5, 1};
        System.out.println("\n>>>>>>> SOLUÇÃO CRONOGRAMA <<<<<<<\n");
        CronogramaSolver.resolveCronograma(l, h);
        System.out.println("\n");
    }
}
