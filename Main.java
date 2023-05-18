import Algorithm.*;
import Requests.Request;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Parameters
        int frames = 5;
        int thrashingThreshold = 5;
        ArrayList<Request> queue = Generator.generateRequestQueue(100000, 3, 5, 1, 1000, 10);
        test(queue, frames, thrashingThreshold);
    }

    private static void test(ArrayList<Request> queue, int frames, int thrashingThreshold) {
        Algorithm algorithm;

        //FIFO
        algorithm = new FIFO();
        algorithm.simulate(queue, frames, thrashingThreshold);

        //OPT
        algorithm = new OPT();
        algorithm.simulate(queue, frames, thrashingThreshold);

        //LRU
        algorithm = new LRU();
        algorithm.simulate(queue, frames, thrashingThreshold);

        //ALRU
        algorithm = new ALRU();
        algorithm.simulate(queue, frames, thrashingThreshold);

        //RAND
        algorithm = new RAND();
        algorithm.simulate(queue, frames, thrashingThreshold);
    }

    //TODO powiedzieć o błędzie we wskazówkach do ALRU
}