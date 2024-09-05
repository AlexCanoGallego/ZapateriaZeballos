package business.enviament;

import business.Caixa;
import business.Sabata;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BranchAndBoundEnviament {
    private double millorPreu;
    private ArrayList<Caixa> millorSolucio;

    /**
     * Constructor de la classe BranchAndBoundEnviament
     */
    public BranchAndBoundEnviament() {
        this.millorPreu = Double.MAX_VALUE;
        this.millorSolucio = new ArrayList<>();
    }

    /**
     * Metode que executa l'algorisme Branch and Bound
     * @param sabates Sabates
     */
    public void branchAndBound(ArrayList<Sabata> sabates) {
        if (sabates.isEmpty()) {
            System.out.println("There are no shoes to process.");
            return;
        }
        ConfiguracioEnviament rootConfiguracio = new ConfiguracioEnviament(sabates);
        PriorityQueue<ConfiguracioEnviament> pq = new PriorityQueue<>(Comparator.comparingDouble(ConfiguracioEnviament::getPreu));
        pq.add(rootConfiguracio);

        while (!pq.isEmpty()) {
            ConfiguracioEnviament configuracio = pq.poll();
            double preuActual = configuracio.getPreu();

            if (preuActual >= this.millorPreu) {
                continue;
            }

            if (configuracio.possibleSolucio()) {
                double preu = configuracio.getPreu();
                if (preu < this.millorPreu) {
                    this.millorPreu = preu;
                    this.millorSolucio = new ArrayList<>(configuracio.getCaixes());
                }
            } else {
                for (ConfiguracioEnviament combinacions : configuracio.getCombinacions()) {
                    double suguentPreu = combinacions.getPreu();
                    if (suguentPreu < this.millorPreu) {
                        pq.add(combinacions);
                    }
                }
            }
        }
        showResults();
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
