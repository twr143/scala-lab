package sep2020

/**
  * Created by Ilya Volynin on 20.09.2020 at 17:25.
  */
object VacuumCleaner {

   // This is the robot's control interface.
    // You should not implement it, or speculate about its implementation
    class Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        def move(): Boolean = true

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        def turnLeft(): Unit = {}
        def turnRight(): Unit = {}

        // Clean the current cell.
        def clean(): Unit = {}
    }


  object Solution {
      def cleanRoom(robot: Robot): Unit = {

      }
  }
}
