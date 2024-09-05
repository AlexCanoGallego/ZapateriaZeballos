package controller;

import business.*;
import business.enviament.BacktrackingEnviament;
import business.enviament.BranchAndBoundEnviament;
import business.divisio.BacktrackingDivisio;
import business.divisio.GreedyDivisio;
import fileReader.FileReader;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private FileReader fileReader;
    private ArrayList<Sabata> sabatas;
    private BacktrackingEnviament backtrackingEnviament;
    private BranchAndBoundEnviament branchAndBoundEnviament;
    private BacktrackingDivisio backtrackingDivisio;
    private GreedyDivisio greedyDivisio;

    /**
     * Constructor de la classe Controller
     * @param fileReader Classe que llegeix els fitxers
     * @param backtrackingEnviament Classe que fa l'enviament de caixes amb backtracking
     * @param branchAndBoundEnviament Classe que fa l'enviament de caixes amb branch and bound
     * @param backtrackingDivisio Classe que fa la divisió d'inventari amb backtracking
     * @param greedyDivisio Classe que fa la divisió d'inventari amb greedy
     */
    public Controller(FileReader fileReader, BacktrackingEnviament backtrackingEnviament, BranchAndBoundEnviament branchAndBoundEnviament, BacktrackingDivisio backtrackingDivisio, GreedyDivisio greedyDivisio) {
        this.scanner = new Scanner(System.in);
        this.fileReader = fileReader;
        this.sabatas = new ArrayList<>();
        this.backtrackingEnviament = backtrackingEnviament;
        this.branchAndBoundEnviament = branchAndBoundEnviament;
        this.backtrackingDivisio = backtrackingDivisio;
        this.greedyDivisio = greedyDivisio;
    }

    /**
     * Mètode que executa el programa
     */
    public void run(){
        int option = 0;
        int fileToRead = 0;
        int exit = 0;

        System.out.println("HI! Welcome to Zapatería Zeballos again!");
        while(exit == 0){
            while(option < 1 || option > 4){
                System.out.println("\nWhat do you want to do?");
                System.out.println("\t1 - Enviament de caixes (backtracking)");
                System.out.println("\t2 - Enviament de caixes (branch and bound)");
                System.out.println("\t3 - Divisió d’inventar (backtracking)");
                System.out.println("\t4 - Divisió d’inventar (greedy)\n");

                System.out.print("Select the option you want to execute: ");
                option = askForInt();
            }
            while(fileToRead < 1 || fileToRead > 6){
                System.out.println("\nWhat file do you want to sort?");
                System.out.println("\t1 - XXS");
                System.out.println("\t2 - XS");
                System.out.println("\t3 - S");
                System.out.println("\t4 - M");
                System.out.println("\t5 - L");
                System.out.println("\t6 - XXXL\n");

                System.out.print("Select the file you want to read: ");
                fileToRead = askForInt();
                sabatas = readFile(fileToRead);
                long start = System.currentTimeMillis();
                makeOption(option, sabatas);
                long end = System.currentTimeMillis();

                System.out.println("\nTemps d'execució: " + (end-start) + " ms");
            }
            System.out.println("Bye!");
            exit = 1;

        }
    }

    /**
     * Mètode que demana un enter
     * @return Enter introduït per l'usuari
     */
    public int askForInt() {
        while(true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: This is not an Int!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    /**
     * Mètode que llegeix un fitxer
     * @param fileToRead Fitxer a llegir
     * @return Llista de sabates
     */
    public ArrayList<Sabata> readFile(int fileToRead){
        switch(fileToRead){
            case 1:
                return fileReader.reader("data/datasetXXS.txt");
            case 2:
                return fileReader.reader("data/datasetXS.txt");
            case 3:
                return fileReader.reader("data/datasetS.txt");
            case 4:
                return fileReader.reader("data/datasetM.txt");
            case 5:
                return fileReader.reader("data/datasetL.txt");
            case 6:
                return fileReader.reader("data/datasetXXXL.txt");
            default:
                return null;
        }
    }

    /**
     * Mètode que executa una opció
     * @param option Opció a executar
     * @param sabates Llista de sabates
     */
    public void makeOption(int option, ArrayList<Sabata> sabates){

        switch (option){
            case 1:
                backtrackingEnviament.backtracking(sabates);
                break;
            case 2:
                branchAndBoundEnviament.branchAndBound(sabates);
                break;
            case 3:
                backtrackingDivisio.backtracking(sabates);
                break;
            case 4:
                greedyDivisio.greedy(sabates);
                break;
        }
    }
}
