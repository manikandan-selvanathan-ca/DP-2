public class PaintHouse {

    //TC:O(MN) where M is number of rows of costs and N is number of columns of costs.
    //SC: O(1) We are just using three constant variable to calculcate the previous values
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
        int n = costs.length;
        int red = costs[n - 1][0];
        int blue = costs[n - 1][1];
        int green = costs[n - 1][2];

        for (int i = n - 2; i >= 0; i--) {
            int tempRed = red;
            int tempBlue = blue;
            int tempGreen = green;

            int redPrevious = Math.min(tempBlue, tempGreen);
            red = costs[i][0] + redPrevious;

            int bluePrevious = Math.min(tempRed, tempGreen);
            blue = costs[i][1] + bluePrevious;

            int greePrevious = Math.min(tempRed, tempBlue);
            green = costs[i][2] + greePrevious;
        }
        return Math.min(red, Math.min(blue, green));
    }

    public int minCostBF(int[][] costs) {
        int case1 = helper(costs, 0, 0, 0);
        int case2 = helper(costs, 0, 0, 1);
        int case3 = helper(costs, 0, 0, 2);
        return Math.min(case1, Math.min(case2, case3));
    }

    //TC:O(2^N) Where N is number of rows 
    //SC:O(2^N) For recursion call stack.
    private int helper(int[][] costs, int min, int row, int lastColor) {

        // base
        if (row == costs.length)
            return min;

        // logic
        int case1 = Integer.MAX_VALUE;
        int case2 = Integer.MAX_VALUE;
        int case3 = Integer.MAX_VALUE;
        if (lastColor == 0) {
            case1 = Math.min(helper(costs, min + costs[row][1], row + 1, 1),
                    helper(costs, min + costs[row][2], row + 1, 2));
        }
        if (lastColor == 1) {
            case2 = Math.min(helper(costs, min + costs[row][0], row + 1, 0),
                    helper(costs, min + costs[row][2], row + 1, 2));
        }
        if (lastColor == 2) {
            case3 = Math.min(helper(costs, min + costs[row][0], row + 1, 0),
                    helper(costs, min + costs[row][1], row + 1, 1));
        }
        return Math.min(case1, Math.min(case2, case3));
    }

    public static void main(String[] args) {
        PaintHouse paintHouse = new PaintHouse();
        int[][] matrix = new int[][] { { 17, 2, 17 }, { 16, 16, 5 }, { 14, 3, 19 } };
        int result = paintHouse.minCost(matrix);
        System.out.println("The result: " + result);
    }
}