package com.github.hrothwell.datastructures.test;

import com.github.hrothwell.datastructures.tree.IPoint;
import com.github.hrothwell.datastructures.tree.TwoDTreeFactory;
import com.github.hrothwell.datastructures.tree.TwoDTree;

import java.util.Random;


public class TwoDTreeTest {


  static class Coordinate implements IPoint {

    double x;
    double y;

    public Coordinate(double x, double y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(IPoint other) {
      return IPoint.xy_sorter.compare(this, other);
    }

    @Override
    public double getX() {
      return x;
    }

    @Override
    public double getY() {
      return y;
    }

    @Override
    public String toString(){
      return String.format("(%s, %s)", x, y);
    }
  }

  public static TwoDTree buildTree(int size) {
    Random random = new Random();
    long start = System.currentTimeMillis();
    Coordinate[] points = new Coordinate[size];

    for(int i = 0; i < size; i++) {
      points[i] = new Coordinate(random.nextDouble(500.0), random.nextDouble(500.0));
    }


    TwoDTree tree = TwoDTreeFactory.generate(points);

    long end = System.currentTimeMillis();

    System.out.format("building tree with size %s took %s ms \n", size, end - start);
    return tree;
  }

  public static void findNearestNTimes(long n, TwoDTree tree) {
    Random random = new Random();
    long start = System.currentTimeMillis();
    for(int i = 0; i < n; i++) {
      Coordinate find = new Coordinate(random.nextDouble(300.0), random.nextDouble(300.0));
      IPoint point = tree.nearest(find);
      if(i % 100 == 0) {
        System.out.format("nearest point to %s : %s \n", find, point);
      }
    }
    long end = System.currentTimeMillis();

    System.out.format("finding nearest point in tree %s times took %s ms \n", n, end - start);
  }
}
