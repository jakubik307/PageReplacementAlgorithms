package Algorithm;

import Requests.Request;

import java.util.ArrayList;

public class OPT extends Algorithm {
    ArrayList<Request> memory;

    @Override
    public void simulate(ArrayList<Request> originalQueue, int frames, int thrashingThreshold) {
        reset();
        deepCopyQueue(originalQueue);
        pageFaultsHistory = new boolean[originalQueue.size()];

        memory = new ArrayList<>(frames);

        for (int i = 0; i < queue.size(); i++) {
            Request request = queue.get(i);
            if (!memory.contains(request)) {
                if (memory.size() == frames) {
                    memory.remove(findOptimal(i));
                }
                memory.add(request);
                pageFaults++;
                pageFaultsHistory[i] = true;
            }
        }
        printResults(thrashingThreshold);
    }

    private int findOptimal(int startIndex) {
        int[] arr = new int[memory.size()];

        for (int i = 0; i < memory.size(); i++) {
            arr[i] = Integer.MAX_VALUE;
            Request request = new Request(memory.get(i).getPage(), memory.get(i).getId());
            for (int j = startIndex; j < queue.size(); j++) {
                if (queue.get(j).equals(request)) {
                    arr[i] = j;
                    break;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }

        return index;
    }
}
