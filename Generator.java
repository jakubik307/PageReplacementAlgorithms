import Requests.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Generator {

    public static ArrayList<Request> generateRequestQueue(int simulationSize, int minLocalitySize, int maxLocalitySize, int minLocalityLength, int maxLocalityLength, int pages) throws IllegalArgumentException {
        if (!isCorrect(simulationSize, minLocalitySize, maxLocalitySize, minLocalityLength, maxLocalityLength, pages))
            throw new IllegalArgumentException();

        ArrayList<Request> queue = new ArrayList<>(simulationSize);
        Random random = new Random();
        int createdRequests = 0;

        //Inclusive max values
        maxLocalitySize++;
        maxLocalityLength++;

        while (createdRequests < simulationSize) {
            int localitySize = random.nextInt(minLocalitySize, maxLocalitySize);
            int localityLength = random.nextInt(minLocalityLength, maxLocalityLength);

            List<Integer> randomPages = generateLocalityArray(pages, localitySize);

            for (int i = 0; i < localityLength; i++) {
                Request request = new Request(randomPages.get(random.nextInt(localitySize)), createdRequests);
                queue.add(request);
                createdRequests++;
            }
        }
        //return first simulationSize elements
        return new ArrayList<>(queue.subList(0, simulationSize));
    }

    private static boolean isCorrect(int simulationSize, int minLocalitySize, int maxLocalitySize, int minLocalityLength, int maxLocalityLength, int pages) {
        if (simulationSize < 0 || pages < 0) return false;
        else if (minLocalitySize <= 0 || minLocalitySize > pages) return false;
        else if (maxLocalitySize <= 0 || maxLocalitySize < minLocalitySize || maxLocalitySize > pages) return false;
        else if (minLocalityLength <= 0 || minLocalityLength > simulationSize) return false;
        else if (maxLocalityLength <= 0 || maxLocalityLength < minLocalityLength || maxLocalityLength > simulationSize)
            return false;
        return true;
    }

    private static ArrayList<Integer> generateLocalityArray(int maxPages, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>(maxPages);
        for (int i = 1; i <= maxPages; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int i = 0; i < size; i++) {
            result.add(numbers.get(i));
        }

        return result;
    }
}
