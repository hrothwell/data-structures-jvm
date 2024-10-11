package com.github.hrothwell.datastructures.test

import com.github.hrothwell.datastructures.tree.IPoint
import com.github.hrothwell.datastructures.tree.TwoDTreeFactory
import com.github.hrothwell.datastructures.tree.TwoDTree
import kotlin.random.Random

object TwoDTreeTest {

  data class NeptuneCoordinate(
    val internalX: Int,
    val internalY: Int,
  ): IPoint {
    override fun compareTo(other: IPoint?): Int {
      return IPoint.xy_sorter.compare(this, other)
    }

    override fun getX(): Double {
      return internalX.toDouble()
    }

    override fun getY(): Double {
      return internalY.toDouble()
    }
  }

  fun buildTree(size: Long): TwoDTree {
    val start = System.currentTimeMillis()
    val points = (0..size).map {
      NeptuneCoordinate(
        internalX = Random.nextInt(300),
        internalY = Random.nextInt(300)
      )
    }.toTypedArray()

    val tree = TwoDTreeFactory.generate(points)

    val end = System.currentTimeMillis()

    println("building tree with size $size took ${end - start}ms")
    return tree
  }

  fun findNearestNTimes(n: Long, tree: TwoDTree) {
    val start = System.currentTimeMillis()
    repeat(n.toInt()) { i ->
      val find = NeptuneCoordinate(Random.nextInt(300), Random.nextInt(300))
      val point = tree.nearest(find)
      if(i % 1000 == 0) {
        println("nearest point to $find: $point")
      }
    }
    val end = System.currentTimeMillis()

    println("finding nearest $n times took ${end - start}ms")
  }
}
