public class BadNum {

    public static void main(String[] args) {
        BadNumbersTest badNumbersTest = new BadNumbersTest();

//        int[] badNumbers = {5, 4, 2, 15};
//        int[] badNumbers = {37, 7, 22, 15, 49, 60};
        int[] badNumbers = {9,5,14,25,98,68,48,32,2};

        int maxRange = badNumbersTest.goodSegment(
                badNumbers,
                10,
                69
        );

        System.out.println("Longest range is " + maxRange);

    }

}
