import Algorithm.*;
import Requests.Request;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Parameters
        int frames = 8;
        int thrashingThreshold = 5;
        ArrayList<Request> queue = Generator.generateRequestQueue(10000000, 5, 8,
                1, 100, 20);
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
}