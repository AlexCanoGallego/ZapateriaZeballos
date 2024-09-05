package business.enviament;

import business.Caixa;
import business.Sabata;
import java.util.ArrayList;

public class BacktrackingEnviament {
    private double millorPreu;
    private ArrayList<Caixa> millorSolucio;

    /**
     * Constructor de la classe BacktrackingEnviament
     */
    public BacktrackingEnviament() {
        this.millorPreu = Double.MAX_VALUE;
        this.millorSolucio = new ArrayList<>();
    }

    /**
     * Metode que executa l'algorisme Backtracking
     * @param sabates Sabates
     */
    public void backtracking(ArrayList<Sabata> sabates) {
        if (sabates.isEmpty()) {
            System.out.println("There are no shoes to process.");
            return;
        }
        ConfiguracioEnviament rootConfiguracio = new ConfiguracioEnviament(sabates);
        backtrack(rootConfiguracio, 0);
        showResults();
    }

    /**
     * Metode que executa l'algorisme Backtracking
     * @param configuracio ConfiguracioEnviament
     * @param preuActual Preu actual
     */
    private void backtrack(ConfiguracioEnviament configuracio, double preuActual) {
        if (preuActual >= this.millorPreu) {
            return;
        }
        if (configuracio.possibleSolucio()) {
            double preu = configuracio.getPreu();
            if (preu < this.millorPreu) {
                this.millorPreu = preu;
                this.millorSolucio = new ArrayList<>(configuracio.getCaixes());
            }
        }
        else {
            for (ConfiguracioEnviament combinacions : configuracio.getCombinacions()) {
                double suguentPreu = combinacions.getPreu();
                if (suguentPreu < this.millorPreu) {
                    backtrack(combinacions, suguentPreu);
                }
            }
        }
    }

    /**
     * Metode que mostra els resultats
     */
    private void showResults() {
        if (millorSolucio.isEmpty()) {
            System.out.println("No valid solution found.");
            return;
        }

        int countCaixa = 0;
        for (Caixa caixa : millorSolucio) {
            caixa.actualitzarPreusSabates();
            System.out.println("\nCaixa: " + countCaixa + " | Preu total: " + caixa.getPreuActual() + "€");
            for (Sabata sabata : caixa.getSabates()) {
                System.out.println("\t* - " + sabata.getNom() + " = " + sabata.getPreu() + "€ | rebaixa = " + sabata.getPreuRebaixat() + "€");
            }
            countCaixa++;
        }
        System.out.println("\nPreu total de totes les capces: " + millorPreu + "€");
    }
}
