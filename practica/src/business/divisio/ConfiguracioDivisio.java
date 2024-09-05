package business.divisio;

import business.Sabata;
import java.util.ArrayList;

public class ConfiguracioDivisio {
    private ArrayList<Sabata> botiga1;
    private ArrayList<Sabata> botiga2;
    private ArrayList<Sabata> sabatesRestants;

    /**
     * Constructor de la classe ConfiguracioDivisio
     * @param sabates ArrayList de sabates
     */
    public ConfiguracioDivisio(ArrayList<Sabata> sabates) {
        this.botiga1 = new ArrayList<>();
        this.botiga2 = new ArrayList<>();
        this.sabatesRestants = new ArrayList<>(sabates);
    }

    /**
     * Constructor de la classe ConfiguracioDivisio
     * @param botiga1 botiga 1
     * @param botiga2 botiga 2
     * @param sabatesRestants sabates restants
     */
    private ConfiguracioDivisio(ArrayList<Sabata> botiga1, ArrayList<Sabata> botiga2, ArrayList<Sabata> sabatesRestants) {
        this.botiga1 = new ArrayList<>(botiga1);
        this.botiga2 = new ArrayList<>(botiga2);
        this.sabatesRestants = new ArrayList<>(sabatesRestants);
    }

    /**
     * Metode per obtenir les combinacions de sabates
     * @return ArrayList de ConfiguracioDivisio
     */
    public ArrayList<ConfiguracioDivisio> getCombinacions() {
        ArrayList<ConfiguracioDivisio> combinacions = new ArrayList<>();
        if (sabatesRestants.isEmpty()) {
            return combinacions;
        }

        Sabata sabata = sabatesRestants.get(0);
        ArrayList<Sabata> restants = new ArrayList<>(sabatesRestants);
        restants.remove(0);

        ArrayList<Sabata> newBotiga1 = new ArrayList<>(botiga1);
        newBotiga1.add(sabata);
        combinacions.add(new ConfiguracioDivisio(newBotiga1, botiga2, restants));

        ArrayList<Sabata> newBotiga2 = new ArrayList<>(botiga2);
        newBotiga2.add(sabata);
        combinacions.add(new ConfiguracioDivisio(botiga1, newBotiga2, restants));

        return combinacions;
    }

    /**
     * Metode per obtenir si la configuracio es completa
     * @return boolean
     */
    public boolean isComplete() {
        return sabatesRestants.isEmpty();
    }

    /**
     * Metode per obtenir la diferencia de preus entre les dues botigues
     * @return diferencia de preus
     */
    public double getDiferencia() {
        double preuBotiga1 = getPreuBotiga1();
        double preuBotiga2 = getPreuBotiga2();

        if(preuBotiga1 > preuBotiga2) {
            return preuBotiga1 - preuBotiga2;
        } else {
            return preuBotiga2 - preuBotiga1;
        }
    }

    /**
     * Metode per obtenir la botiga 1
     * @return botiga 1
     */
    public ArrayList<Sabata> getBotiga1() {
        return botiga1;
    }

    /**
     * Metode per obtenir la botiga 2
     * @return botiga 2
     */
    public ArrayList<Sabata> getBotiga2() {
        return botiga2;
    }

    /**
     * Metode per obtenir el preu de la botiga 1
     * @return preu de la botiga 1
     */
    public double getPreuBotiga1() {
        double preuBotiga1 = 0;
        for (Sabata sabata : botiga1) {
            preuBotiga1 += sabata.getPreu();
        }
        return preuBotiga1;
    }

    /**
     * Metode per obtenir el preu de la botiga 2
     * @return preu de la botiga 2
     */
    public double getPreuBotiga2() {
        double preuBotiga2 = 0;
        for (Sabata sabata : botiga2) {
            preuBotiga2 += sabata.getPreu();
        }
        return preuBotiga2;
    }
}
