package Algorithm;

import Request.Request;

import java.util.ArrayList;

public class ALRU extends Algorithm {
    Request[] memory;
    boolean[] secondChance;

    @Override
    public void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold) {
        reset();
        deepCopyQueue(originalQueue);

        memory = new Request[frames];
        secondChance = new boolean[frames];
        int pointer = 0;

        for (Request request : queue) {
            if (!isPageInMemory(request)) {
                if (pagesInMemory() == frames) {
                    int removalIndex = getRemovalIndex(pointer, frames);
                    memory[removalIndex] = request;
                    secondChance[removalIndex] = true;
                    pointer = removalIndex + 1;
                } else {
                    memory[pointer] = request;
                    secondChance[pointer] = true;
                    pointer++;
                }
                pageFaults++;
            } else giveSecondChance(request);
        }

        printResults();
    }

    private int getRemovalIndex(int startingPoint, int frames) {
        while (true) {
            startingPoint %= frames;
            if (secondChance[startingPoint]) {
                secondChance[startingPoint] = false;
                startingPoint++;
            } else break;
        }
        return startingPoint % frames;
    }

    private boolean isPageInMemory(Request r) {
        for (Request request : memory) if (r.equals(request)) return true;
        return false;
    }

    private int pagesInMemory() {
        int count = 0;
        for (Request request : memory) if (request != null) count++;
        return count;
    }

    private void giveSecondChance(Request r) {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i].equals(r)) {
                secondChance[i] = true;
                break;
            }
        }
    }
}
