package business.divisio;

import business.Sabata;
import java.util.ArrayList;
import java.util.List;

public class BacktrackingDivisio {
    private double millorDiferencia;
    private ConfiguracioDivisio millorSolucio;

    /**
     * Constructor de la classe BacktrackingDivisio
     */
    public BacktrackingDivisio() {
        this.millorDiferencia = Double.MAX_VALUE;
        this.millorSolucio = null;
    }

    /**
     * Metode per executar el backtracking
     * @param sabates ArrayList de sabates
     */
    public void backtracking(ArrayList<Sabata> sabates) {
        if (sabates.isEmpty()) {
            System.out.println("There are no shoes to process.");
            return;
        }
        ConfiguracioDivisio rootConfiguracio = new ConfiguracioDivisio(sabates);
        backtrack(rootConfiguracio);
        showResults();
    }

    /**
     * Metode per resoldre el problema de la divisio de sabates amb l'algorisme backtracking
     * @param configuracio ConfiguracioDivisio
     */
    private void backtrack(ConfiguracioDivisio configuracio) {
        if (configuracio.isComplete()) {
            double diferencia = configuracio.getDiferencia();
            if (diferencia < this.millorDiferencia) {
                this.millorDiferencia = diferencia;
                this.millorSolucio = configuracio;
            }
        } else {
            for (ConfiguracioDivisio combinacions : configuracio.getCombinacions()) {
                backtrack(combinacions);
            }
        }
    }

    /**
     * Metode per mostrar els resultats de la divisio de sabates
     */
    private void showResults() {
        if (millorSolucio == null) {
            System.out.println("No valid solution found.");
            return;
        }

        System.out.println("\n\nBotiga 1 = " + millorSolucio.getPreuBotiga1() + "€");
        for (Sabata sabata : millorSolucio.getBotiga1()) {
            System.out.println("\t* - " + sabata.getNom() + " = " + sabata.getPreu() + "€");
        }

        System.out.println("\n\nBotiga 2 = " + millorSolucio.getPreuBotiga2() + "€");
        for (Sabata sabata : millorSolucio.getBotiga2()) {
            System.out.println("\t* - " + sabata.getNom() + " = " + sabata.getPreu() + "€");
        }

        System.out.println("\nDiferència mínima de preus: " + millorDiferencia + "€");
    }
}
