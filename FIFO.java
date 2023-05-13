import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class FIFO extends Algorithm {
    Deque<Request> memory;

    @Override
    public void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold) {
        reset();
        deepCopyQueue(originalQueue);

        memory = new ArrayDeque<>(frames);

        for (Request request : queue) {
            if (!memory.contains(request)) {
                if (memory.size() == frames) {
                    memory.poll();
                }
                memory.add(request);
                pageFaults++;
            } else {
                memory.remove(request);
                memory.add(request);
            }
        }

        printResults();
    }
}
