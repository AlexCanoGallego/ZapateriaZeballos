import controller.Controller;
import fileReader.FileReader;
import business.enviament.BacktrackingEnviament;
import business.enviament.BranchAndBoundEnviament;
import business.divisio.BacktrackingDivisio;
import business.divisio.GreedyDivisio;

public class Main {

    public static void main(String[] args){
        BacktrackingEnviament backtrackingEnviament = new BacktrackingEnviament();
        BranchAndBoundEnviament branchAndBoundEnviament = new BranchAndBoundEnviament();
        BacktrackingDivisio backtrackingDivisio = new BacktrackingDivisio();
        GreedyDivisio greedyDivisio = new GreedyDivisio();
        FileReader fileReader = new FileReader();
        Controller controller = new Controller(fileReader, backtrackingEnviament, branchAndBoundEnviament, backtrackingDivisio, greedyDivisio);
        controller.run();
    }

}
