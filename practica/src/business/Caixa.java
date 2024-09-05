package business;

import java.util.ArrayList;
import java.util.HashMap;

public class Caixa {
    private ArrayList<Sabata> sabates;
    private int capacitat;
    private double preuActual;
    private int sabataNen;
    private int sabataMarca;
    private int sabataMala;
    private int sabataBona;
    private ArrayList<String> marques;
    private HashMap<String, Integer> marcaCount;

    /**
     * Constructor de la classe Caixa
     */
    public Caixa(){
        this.sabates = new ArrayList<>();
        this.capacitat = 0;
        this.preuActual = 0;
        this.sabataNen = 0;
        this.sabataMarca = 0;
        this.sabataMala = 0;
        this.sabataBona = 0;
        this.marques = new ArrayList<>();
        this.marcaCount = new HashMap<>();
    }

    /**
     * Constructor de la classe Caixa
     * @param caixa Caixa
     */
    public Caixa(Caixa caixa){
        this.sabates = new ArrayList<>(caixa.sabates);
        this.capacitat = caixa.capacitat;
        this.preuActual = caixa.preuActual;
        this.sabataNen = caixa.sabataNen;
        this.sabataMarca = caixa.sabataMarca;
        this.sabataMala = caixa.sabataMala;
        this.sabataBona = caixa.sabataBona;
        this.marques = new ArrayList<>(caixa.marques);
        this.marcaCount = new HashMap<>(caixa.marcaCount);
    }

    /**
     * Metode per afegir una sabata a la caixa
     * @param sabata Sabata
     */
    public void afegirSabata(Sabata sabata) {
        this.sabates.add(sabata);
        this.capacitat++;
        String marca = sabata.getBrandNom();
        this.marcaCount.put(marca, this.marcaCount.getOrDefault(marca, 0) + 1);
        if (sabata.getMaxTalla() < 35) {
            this.sabataNen++;
        }
        if (sabata.getPuntuacio() < 5) {
            this.sabataMala++;
        }
        if (sabata.getPuntuacio() > 8) {
            this.sabataBona++;
        }
        calcularPreuActual();
    }

    /**
     * Metode per treure una sabata de la caixa
     * @param sabata Sabata
     */
    public void treureSabata(Sabata sabata) {
        this.sabates.remove(sabata);
        this.capacitat--;
        String marca = sabata.getBrandNom();
        if (this.marcaCount.containsKey(marca)) {
            int count = this.marcaCount.get(marca) - 1;
            if (count == 0) {
                this.marcaCount.remove(marca);
            } else {
                this.marcaCount.put(marca, count);
            }
        }
        if (sabata.getMaxTalla() < 35) {
            this.sabataNen--;
        }
        if (sabata.getPuntuacio() < 5) {
            this.sabataMala--;
        }
        if (sabata.getPuntuacio() > 8) {
            this.sabataBona--;
        }
        calcularPreuActual();
    }

    /**
     * Metode per obtenir les sabates de la caixa
     * @return Sabates
     */
    public ArrayList<Sabata> getSabates(){
        return this.sabates;
    }

    /**
     * Metode per obtenir la capacitat de la caixa
     * @return Capacitat
     */
    public int getCapacitat(){
        return this.capacitat;
    }

    /**
     * Metode per obtenir el preu actual de la caixa
     * @return Preu actual
     */
    public double getPreuActual(){
        calcularPreuActual();
        return this.preuActual;
    }

    /**
     * Metode per saber si la caixa es valida
     * @return Valid o no
     */
    public boolean esValid(){
        return this.capacitat <= 6 && this.preuActual <= 1000;
    }

    /**
     * Metode per calcular el preu actual de la caixa
     */
    private void calcularPreuActual() {
        this.preuActual = 0;
        for (Sabata sabata : this.sabates) {
            double preu = sabata.getPreu();
            String marca = sabata.getBrandNom();
            if (this.marcaCount.get(marca) != null && this.marcaCount.get(marca) > 1) {
                preu *= 0.80; // 20% discount
            }
            if (this.sabataNen > 1 && sabata.getMaxTalla() < 35) {
                preu *= 0.65; // 35% discount
            }
            if (this.sabataMala > 2 && sabata.getPuntuacio() < 5) {
                preu *= 0.60; // 40% discount
            }
            if (this.sabataBona > 2 && sabata.getPuntuacio() > 8) {
                preu *= 1.20; // 20% surcharge
            }
            this.preuActual += preu;
        }
    }

    /**
     * Metode per actualitzar els preus de les sabates
     */
    public void actualitzarPreusSabates() {
        this.preuActual = 0;
        for (Sabata sabata : this.sabates) {
            double preu = sabata.getPreu();
            String marca = sabata.getBrandNom();
            if (this.marcaCount.get(marca) != null && this.marcaCount.get(marca) > 1) {
                preu *= 0.80; // 20% discount
                sabata.setPreuRebaixat(preu);
            }
            if (this.sabataNen > 1 && sabata.getMaxTalla() < 35) {
                preu *= 0.65; // 35% discount
                sabata.setPreuRebaixat(preu);
            }
            if (this.sabataMala > 2 && sabata.getPuntuacio() < 5) {
                preu *= 0.60; // 40% discount
                sabata.setPreuRebaixat(preu);
            }
            if (this.sabataBona > 2 && sabata.getPuntuacio() > 8) {
                preu *= 1.20; // 20% surcharge
                sabata.setPreuRebaixat(preu);
            }
            this.preuActual += preu;
        }
    }

    /**
     * Metode per obtenir el preu possible de la caixa
     * @param auxSabata Sabata
     * @return Preu possible
     */
    public double possiblePreu(Sabata auxSabata) {
        afegirSabata(auxSabata);
        double preuActual = 0;
        for (Sabata sabata : this.sabates) {
            double preu = sabata.getPreu();
            String marca = sabata.getBrandNom();
            if (this.marcaCount.get(marca) != null && this.marcaCount.get(marca) > 1) {
                preu *= 0.80; // 20% discount
            }
            if (this.sabataNen > 1 && sabata.getMaxTalla() < 35) {
                preu *= 0.65; // 35% discount
            }
            if (this.sabataMala > 2 && sabata.getPuntuacio() < 5) {
                preu *= 0.60; // 40% discount
            }
            if (this.sabataBona > 2 && sabata.getPuntuacio() > 8) {
                preu *= 1.20; // 20% surcharge
            }
            preuActual += preu;
        }
        treureSabata(auxSabata);
        return preuActual;
    }
}
