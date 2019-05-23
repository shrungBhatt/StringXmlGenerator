
import java.util.ArrayList;
import java.util.Collections;

public class BadNumbersTest {


    public int goodSegment(int[] badNumbers, int lowerLimit, int upperLimit) {
        ArrayList<Integer> badNumbersList = new ArrayList<>();
        for (int i = 0; i < badNumbers.length; i++) {
            badNumbersList.add(badNumbers[i]);
        }

        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Integer> diffs = new ArrayList<>();

        int maxIterationBetweenRange = upperLimit - lowerLimit;
        Segment segment = new Segment();
        int counter = lowerLimit;
        for (int i = 0; i <= maxIterationBetweenRange + 1; i++) {
            segment.l = lowerLimit;
            if (badNumbersList.contains(counter)) {
                segment.m = counter;
                segment.diff = segment.m - segment.l;
                segments.add(segment);
                diffs.add(segment.diff);
                segment = new Segment();
                counter++;
                lowerLimit = counter;
            } else {
                segment.m = counter;
                if (counter >= upperLimit) {
                    segment.diff = segment.m - segment.l;
                    segments.add(segment);
                    diffs.add(segment.diff);
                    counter++;
                    continue;
                }
                counter++;
            }

        }



        return Collections.max(diffs);
    }

    private class Segment {
        private int l = 0;
        private int m = 0;
        private int diff = 0;

        Segment() {

        }

        Segment(int l, int m, int diff) {
            this.l = l;
            this.m = m;
            this.diff = diff;
        }
    }

}
