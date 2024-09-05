package business;

public class Sabata {
    private String nom;
    private double preu;
    private int minTalla;
    private int maxTalla;
    private double pes;
    private double puntuacio;
    private double maximSale;
    private double preuRebaixat;

    /**
     * Constructor de Sabata
     * @param nom Nom de la sabata
     * @param preu Preu de la sabata
     * @param minTalla Talla minima de la sabata
     * @param maxTalla Talla maxima de la sabata
     * @param pes Pes de la sabata
     * @param puntuacio Puntuacio de la sabata
     */
    public Sabata(String nom, double preu, int minTalla, int maxTalla, double pes, double puntuacio) {
        this.nom = nom;
        this.preu = preu;
        this.minTalla = minTalla;
        this.maxTalla = maxTalla;
        this.pes = pes;
        this.puntuacio = puntuacio;
        this.maximSale = 0;
        this.preuRebaixat = preu;
    }

    /**
     * Metode que retorna el nom de la sabata
     * @return Nom de la sabata
     */
    public String getNom() {
        return nom;
    }

    /**
     * Metode que calcula el maxim de sale de la sabata
     * @param maxPreu Preu maxim de les sabates
     * @param maxPes Pes maxim de les sabates
     * @param maxTallas Talla maxim de les sabates
     */
    public void calculMaximSale(double maxPreu, double maxPes, double maxTallas) {
        double pPreu = 0.3;
        double pPuntuacio = 0.4;
        double pPes = 0.1;
        double pTalla = 0.2;

        double nPreu = (this.preu / maxPreu) * 10;
        double nPes = (this.pes / maxPes) * 10;
        double nTalla = ((this.maxTalla - this.minTalla) / maxTallas) * 10;

        this.maximSale = (nPreu * pPreu) + (this.puntuacio * pPuntuacio) + (nPes * pPes) + (nTalla * pTalla);
    }

    /**
     * Metode que retorna el maxim de sale de la sabata
     * @return Maxim de sale de la sabata
     */
    public double getMaximSale() {
        return maximSale;
    }

    /**
     * Metode que retorna el preu de la sabata
     * @return Preu de la sabata
     */
    public double getPreu() {
        return preu;
    }

    /**
     * Metode que retorna la puntuacio de la sabata
     * @return Puntuacio de la sabata
     */
    public double getPuntuacio() {
        return puntuacio;
    }

    /**
     * Metode que retorna la talla màxima de la sabata
     * @return maxTalla
     */
    public int getMaxTalla() {
        return maxTalla;
    }

    /**
     * Metode que retorna el nom de la marca de la sabata
     * @return Nom de la marca de la sabata
     */
    public String getBrandNom() {
        return nom.split(" ")[0];
    }

    /**
     * Metode que afegueix un preu rebaixat a la sabata
     * @param preuRebaixat Preu rebaixat de la sabata
     */
    public void setPreuRebaixat(double preuRebaixat) {
        if(this.preuRebaixat > preuRebaixat) this.preuRebaixat = preuRebaixat;
    }

    /**
     * Metode que retorna el preu rebaixat de la sabata
     * @return Preu rebaixat de la sabata
     */
    public double getPreuRebaixat() {
        return preuRebaixat;
    }

    /**
     * Metode que afegueix un preu a la sabata
     * @param preu Preu de la sabata
     */
    public void setPreu(double preu) {
        this.preu = preu;
    }
}
