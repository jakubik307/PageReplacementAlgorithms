import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Algorithm algorithm;

        //Create 1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5 request array
        ArrayList<Request> queue2 = new ArrayList<>();
        queue2.add(new Request(1, 1));
        queue2.add(new Request(2, 2));
        queue2.add(new Request(3, 3));
        queue2.add(new Request(4, 4));
        queue2.add(new Request(1, 5));
        queue2.add(new Request(2, 6));
        queue2.add(new Request(5, 7));
        queue2.add(new Request(1, 8));
        queue2.add(new Request(2, 9));
        queue2.add(new Request(3, 10));
        queue2.add(new Request(4, 11));
        queue2.add(new Request(5, 12));


        //Parameters
        int frames = 4;
        int thrashingThreshold = 5;
        ArrayList<Request> queue = Generator.generateRequestQueue(1000, 3, 5, 20, 30, 5);

        //FIFO
        algorithm = new FIFO();
        algorithm.simulate(queue2, frames, thrashingThreshold);
    }
}