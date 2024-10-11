import com.github.hrothwell.test.TwoDTreeTest

object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    val t = TwoDTreeTest.buildTree(10_000) // 33ms to build it with 10K points
    TwoDTreeTest.findNearestNTimes(10_000, t)
//    KDTreeTest.test(10_000_000) // SLOW like 25 seconds slow
  }
}
