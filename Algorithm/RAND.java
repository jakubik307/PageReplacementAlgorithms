package Algorithm;

import Requests.Request;

import java.util.ArrayList;
import java.util.Random;

public class RAND extends Algorithm {
    ArrayList<Request> memory;

    @Override
    public void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold) {
        Random random = new Random();

        reset();
        deepCopyQueue(originalQueue);
        pageFaultsHistory = new boolean[originalQueue.size()];

        memory = new ArrayList<>(frames);

        for (int i = 0; i < queue.size(); i++) {
            Request request = queue.get(i);
            if (!memory.contains(request)) {
                if (memory.size() == frames) {
                    memory.remove(random.nextInt(frames));
                }
                memory.add(request);
                pageFaults++;
                pageFaultsHistory[i] = true;
            }
        }

        printResults(thrashingThreshold);
    }
}
