package fileReader;

import business.Sabata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private double maxPreu = 0;
    private double maxPes = 0;
    private int maxTallas = 0;
    private int minTallas = 0;
    public FileReader() { }

    /**
     * Metode que retorna un objecte Sabata a partir d'un string csv
     * @param csv String amb les dades de la sabata
     * @return Sabata amb les dades del csv
     */
    private Sabata dataFromFile(String csv) {
        String[] data = csv.split(";");
        String nom = data[0];
        double preu = Double.parseDouble(data[1].replace(",", "."));
        int minTalla = Integer.parseInt(data[2]);
        int maxTalla = Integer.parseInt(data[3]);
        double pes = Double.parseDouble(data[4].replace(",", "."));
        double nota = Double.parseDouble(data[5].replace(",", "."));

        if(preu > maxPreu) maxPreu = preu;
        if(pes > maxPes) maxPes = pes;
        if(maxTalla > maxTallas) maxTallas = maxTalla;
        if(minTalla < minTallas) minTallas = minTalla;

        return new Sabata(nom, preu, minTalla, maxTalla, pes, nota);
    }

    /**
     * Metode que retorna un ArrayList de Sabates a partir d'un fitxer
     * @param path Path del fitxer
     * @return ArrayList de Sabates
     */
    public ArrayList<Sabata> reader(String path){
        ArrayList<Sabata> sabatas = new ArrayList<>();

        try{
            List<String> data = Files.readAllLines(Path.of(path));
            data.remove(0);
            for(String s : data){
                sabatas.add(dataFromFile(s));
            }
            return sabatas;
        } catch(IOException e){
            return sabatas;
        }
    }
}
