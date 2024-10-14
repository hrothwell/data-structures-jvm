package com.github.hrothwell.datastructures;

import com.github.hrothwell.datastructures.test.TwoDTreeTest;
import com.github.hrothwell.datastructures.tree.TwoDTree;

class Main {
  public static void main(String[] args) {
    TwoDTree t = TwoDTreeTest.buildTree(10_000); // 33ms
    TwoDTreeTest.findNearestNTimes(10_000, t); // 21ms
//    TwoDTreeTest.buildTree(10_000_000); // SLOW like 25 seconds

    /**
     * TODO look at some of the tests provided from original source
     */
  }
}
