import redis.clients.jedis.{HostAndPort, Jedis, JedisCluster}

import scala.io.Source

class RedisCluster(IP: String) {

  val node = new HostAndPort(IP, 6379)

  /**
    * Testing Cluster Connection
    */
  def test_cluster_connection(): Unit = {

        val cluster = new JedisCluster(node)
        cluster.set("key", "value")
        val result = cluster.get("key")
        println(result)
        cluster.close()
  }

  /**
    * Insert data from a flat file
    * @param filename
    */
  def flatFileInserts(filename: String): Unit = {

    println(s"Reading Filename = $filename")

    val cluster = new JedisCluster(node)

    //val buffer = io.Source.fromFile("data/links.dat")
    for (line <- Source.fromFile(filename).getLines) {

      val col = line.split("\\,").map(_.trim)
      if (col.length == 2) {

        if ((col(0).length() > 2) && (col(1).length() > 2)) {
          println(col(0) + " ... " + col(1))
          cluster.set(col(0), col(1))
        } else {
          println(s"Column is too short = $line")
        }
      }
    }

    cluster.close()
  }

  /**
    * Get Session Id
    * @param session_id
    */
  def getSessionId(session_id: String): String = {

    val cluster = new JedisCluster(node)
    val value = cluster.get(session_id)
    cluster.close()
    println(value)
    value

  }
  
}
