package business.divisio;

import business.Sabata;
import java.util.ArrayList;

public class GreedyDivisio {

    /**
     * Metode per resoldre el problema de la divisio de sabates amb l'algorisme greedy
     */
    public void greedy(ArrayList<Sabata> sabates) {
        if (sabates.isEmpty()) {
            System.out.println("There are no shoes to process.");
            return;
        }

        ArrayList<Sabata> botiga1 = new ArrayList<>();
        ArrayList<Sabata> botiga2 = new ArrayList<>();
        double totalPreu1 = 0;
        double totalPreu2 = 0;

        for (Sabata sabata : sabates) {
            if (totalPreu1 <= totalPreu2) {
                botiga1.add(sabata);
                totalPreu1 += sabata.getPreu();
            } else {
                botiga2.add(sabata);
                totalPreu2 += sabata.getPreu();
            }
        }

        showResults(botiga1, botiga2);
    }

    /**
     * Metode per mostrar els resultats de la divisio de sabates
     * @param botiga1 botiga 1
     * @param botiga2 botiga 2
     */
    private void showResults(ArrayList<Sabata> botiga1, ArrayList<Sabata> botiga2) {
        double preuBotiga1 = getTotalPreu(botiga1);
        double preuBotiga2 = getTotalPreu(botiga2);

        System.out.println("\n\nBotiga 1 = " + preuBotiga1 + "€");
        for (Sabata sabata : botiga1) {
            System.out.println("\t* - " + sabata.getNom() + " = " + sabata.getPreu() + "€");
        }

        System.out.println("\n\nBotiga 2 = " + preuBotiga2 + "€");
        for (Sabata sabata : botiga2) {
            System.out.println("\t* - " + sabata.getNom() + " = " + sabata.getPreu() + "€");
        }

        System.out.println("\nDiferència de preus: " + getDiferencia(preuBotiga1, preuBotiga2) + "€");
    }

    /**
     * Metode per obtenir la diferencia de preus entre les dues botigues
     * @param preuBotiga1 preu de la botiga 1
     * @param preuBotiga2 preu de la botiga 2
     * @return diferencia de preus
     */
    private double getDiferencia(double preuBotiga1, double preuBotiga2) {
        if(preuBotiga1 > preuBotiga2) {
            return preuBotiga1 - preuBotiga2;
        } else {
            return preuBotiga2 - preuBotiga1;
        }
    }

    /**
     * Metode per obtenir el preu total de les sabates
     * @param botiga botiga
     * @return preu total
     */
    private double getTotalPreu(ArrayList<Sabata> botiga) {
        double preuTotal = 0;
        for(Sabata sabata : botiga) {
            preuTotal += sabata.getPreu();
        }
        return preuTotal;
    }
}
