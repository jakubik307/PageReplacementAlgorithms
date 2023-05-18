package Algorithm;

import Requests.Request;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class FIFO extends Algorithm {
    Deque<Request> memory;

    @Override
    public void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold) {
        reset();
        deepCopyQueue(originalQueue);
        pageFaultsHistory = new boolean[originalQueue.size()];

        memory = new ArrayDeque<>(frames);

        for (int i = 0; i < queue.size(); i++) {
            Request request = queue.get(i);
            if (!memory.contains(request)) {
                if (memory.size() == frames) {
                    memory.poll();
                }
                memory.add(request);
                pageFaults++;
                pageFaultsHistory[i] = true;
            }
        }

        printResults(thrashingThreshold);
    }
}
