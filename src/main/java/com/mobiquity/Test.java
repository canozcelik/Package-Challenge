package com.mobiquity;

import static java.lang.Math.max;

public class Test {
  static int knapSack(int W, int wt[], int val[], int n) {
    int i, w;
    int K[][] = new int[n + 1][W + 1];

    for (i = 0; i <= n; i++) {
      for (w = 0; w <= W; w++) {
        if (i == 0 || w == 0) K[i][w] = 0;
        else if (wt[i - 1] <= w) K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
        else K[i][w] = K[i - 1][w];
      }
    }

    int res = K[n][W];
    System.out.println(res);

    w = W;
    for (i = n; i > 0 && res > 0; i--) {

      // either the result comes from the top
      // (K[i-1][w]) or from (val[i-1] + K[i-1]
      // [w-wt[i-1]]) as in Knapsack table. If
      // it comes from the latter one/ it means
      // the item is included.
      if (res == K[i - 1][w]) continue;
      else {

        // This item is included.
        System.out.print(wt[i - 1] + " ");

        // Since this weight is included its
        // value is deducted
        res = res - val[i - 1];
        w = w - wt[i - 1];
      }
    }

    return K[n][W];
  }

  public static void main(String args[]) {
    int val[] = new int[] {29, 74, 16, 55, 52, 75, 74, 35, 78};
    int wt[] = new int[] {85, 14, 3, 26, 63, 76, 60, 93, 89};
    ;
    int W = 75;
    int n = val.length;
    System.out.println(knapSack(W, wt, val, n));
  }
}
