package Algorithm;

import Request.Request;

import java.util.ArrayList;
import java.util.Random;

public class RAND extends Algorithm {
    ArrayList<Request> memory;

    @Override
    public void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold) {
        Random random = new Random();

        reset();
        deepCopyQueue(originalQueue);

        memory = new ArrayList<>(frames);

        for (Request request : queue) {
            if (!memory.contains(request)) {
                if (memory.size() == frames) {
                    memory.remove(random.nextInt(frames));
                }
                memory.add(request);
                pageFaults++;
            }
        }

        printResults();
    }
}
