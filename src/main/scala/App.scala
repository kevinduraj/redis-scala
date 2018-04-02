/**
  * App Class for querying Cassandra
  */
object App {

  var IP = "127.0.0.1"
  var filename = ""

  /**
    * Get command line parameters
    * @param args
    */
  private def get_command_line_parameters(args: Array[String]) = {

    if (args.size > 1) {
      println("args: " + args(0))
      IP = args(0).toString
      filename = args(1).toString

    }

  }


  /**
    * Main Method of App Class
    * @param args
    */
  def main(args: Array[String]) {

    get_command_line_parameters(args)

//    val redis = new Redis1()
//    redis.test_put_get()
//    redis.flatFileInserts(filename)
//    redis.getSessionId("C5EB7AE9-D7A0-4206-8C3A-12F061B76A9A")

      val redis2 = new RedisCluster(IP)
      redis2.test_cluster_connection()
      redis2.flatFileInserts(filename)

      println("------------------------------------------------------------------------")
      println("Datetime = " + redis2.getSessionId("05319493-00aa-4d6e-b1ce-9389340adba2"))
      println("Datetime = " + redis2.getSessionId("05319493-00aa-4d6e-b1ce-9389340adba2"))
      println("------------------------------------------------------------------------")
  }

}
