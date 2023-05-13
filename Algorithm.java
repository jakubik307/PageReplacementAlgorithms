import java.util.ArrayList;

public abstract class Algorithm {
    protected ArrayList<Request> queue;
    protected int pageFaults;
    protected int thrashing;

    public Algorithm() {
        this.queue = new ArrayList<>();
        this.pageFaults = 0;
        this.thrashing = 0;
    }

    public abstract void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold);

    protected void printResults() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("-".repeat(this.getClass().getSimpleName().length()));
        System.out.println("Page faults: " + pageFaults);
        System.out.println("Thrashing: " + thrashing);
    }

    protected void deepCopyQueue(ArrayList<Request> originalQueue) {
        for (Request request : originalQueue) {
            queue.add(new Request(request.getPage(), request.getId()));
        }
    }

    protected void reset() {
        queue.clear();
        pageFaults = 0;
        thrashing = 0;
    }
}

