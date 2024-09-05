package business.enviament;

import business.Caixa;
import business.Sabata;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class ConfiguracioEnviament {
    private ArrayList<Caixa> caixes;
    private ArrayList<Sabata> sabatesRestants;
    private static final int MAX_PAIRS = 6;
    private static final double MAX_PRICE = 1000.0;

    /**
     * Constructor de la classe ConfiguracioEnviament
     * @param sabates Sabates
     */
    public ConfiguracioEnviament(ArrayList<Sabata> sabates) {
        this.caixes = new ArrayList<>();
        this.caixes.add(new Caixa());
        this.sabatesRestants = new ArrayList<>(sabates);
    }

    /**
     * Constructor de la classe ConfiguracioEnviament
     * @param configuracio ConfiguracioEnviament
     */
    private ConfiguracioEnviament(ConfiguracioEnviament configuracio) {
        this.caixes = new ArrayList<>();
        for (Caixa caixa : configuracio.caixes) {
            this.caixes.add(new Caixa(caixa));
        }
        this.sabatesRestants = new ArrayList<>(configuracio.sabatesRestants);
    }

    /**
     * Mètode que retorna les combinacions possibles
     * @return Combinacions
     */
    public Iterable<ConfiguracioEnviament> getCombinacions() {
        Collection<ConfiguracioEnviament> combinacions = new HashSet<>();
        if (sabatesRestants.isEmpty()) return combinacions;

        Sabata sabata = sabatesRestants.get(0);

        for (Caixa caixa : caixes) {
            if (caixa.getCapacitat() < MAX_PAIRS && caixa.possiblePreu(sabata) <= MAX_PRICE) {
                ConfiguracioEnviament newConfig = new ConfiguracioEnviament(this);
                newConfig.caixes.get(caixes.indexOf(caixa)).afegirSabata(sabata);
                newConfig.sabatesRestants.remove(sabata);
                combinacions.add(newConfig);
            }
        }

        if (combinacions.isEmpty()) {
            ConfiguracioEnviament newConfig = new ConfiguracioEnviament(this);
            Caixa novaCaixa = new Caixa();
            novaCaixa.afegirSabata(sabata);
            newConfig.caixes.add(novaCaixa);
            newConfig.sabatesRestants.remove(sabata);
            combinacions.add(newConfig);
        }

        return combinacions;
    }

    /**
     * Mètode que comprova si és una possible solució
     * @return Solució
     */
    public boolean possibleSolucio() {
        if (!sabatesRestants.isEmpty()) {
            return false;
        }

        for (Caixa caixa : caixes) {
            if (!caixa.esValid()) {
                return false;
            }
        }

        return true;
    }


    /**
     * Mètode que retorna el preu total
     * @return Preu
     */
    public double getPreu() {
        double totalPreu = 0;

        for (Caixa caixa : caixes) {
            totalPreu += caixa.getPreuActual();
        }

        return totalPreu;
    }

    /**
     * Mètode que retorna les caixes
     * @return Caixes
     */
    public ArrayList<Caixa> getCaixes() {
        return caixes;
    }
}
