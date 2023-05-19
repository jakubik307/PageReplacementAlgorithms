package Algorithm;

import Requests.Request;

import java.util.ArrayList;

public abstract class Algorithm {
    protected ArrayList<Request> queue;
    protected boolean[] pageFaultsHistory;
    protected int pageFaults;

    public Algorithm() {
        this.queue = new ArrayList<>();
        this.pageFaults = 0;
    }

    public abstract void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold);

    protected void printResults(int thrashingThreshold) {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("-".repeat(this.getClass().getSimpleName().length()));
        System.out.println("Page faults: " + pageFaults);
        System.out.println("Thrashing: " + calculateThrashing(thrashingThreshold));
        System.out.println();
    }

    protected void deepCopyQueue(ArrayList<Request> originalQueue) {
        for (Request request : originalQueue) {
            queue.add(new Request(request.getPage(), request.getId()));
        }
    }

    protected void reset() {
        queue.clear();
        pageFaults = 0;
    }

    private int calculateThrashing(int threshold) {
        int result = 0;
        int top = 0;
        int bot = 0;
        for (int i = 0; i < pageFaultsHistory.length; i++) {
            boolean b = pageFaultsHistory[i];
            if (b) top++;
            if (i % 5 == 4) {
                if (top + bot >= threshold) result++;
                bot = top;
                top = 0;
            }
        }
        return result;
    }
}

