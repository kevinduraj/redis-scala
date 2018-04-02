import redis.clients.jedis.Jedis

import scala.io.Source

/**
  * Redis Database
  */
class RedisSingle() {

  def test_put_get(): Unit = {

    val jedis = new Jedis("localhost");
    jedis.set("key", "value");
    val value = jedis.get("key");
    println(value)

  }

  /**
    * Insert Session Id
    * @param session_id
    * @param dt
    */
  def insertSessionId(session_id: String, dt: String): Unit = {

    val jedis = new Jedis("localhost");
    jedis.set(session_id, dt);

  }

  /**
    * Get Session Id
    * @param session_id
    */
  def getSessionId(session_id: String): Unit = {

    val jedis = new Jedis("localhost")
    val value = jedis.get(session_id)
    println(value)

  }

  /**
    * Insert data from a flat file
    * @param filename
    */
  def flatFileInserts(filename: String): Unit = {

    println(s"Reading Filename = $filename")

    //val buffer = io.Source.fromFile("data/links.dat")

    for (line <- Source.fromFile(filename).getLines) {

      val col = line.split("\\,").map(_.trim)
      //println(s"${col(0)}|${col(1)}")

      if (col.length == 2) {

        if ((col(0).length() > 2) && (col(1).length() > 2)) {
            println(col(0) + " ... " + col(1))
            insertSessionId(col(0), col(1))

        } else {
          println(s"Column is too short = $line")
        }
      }
    }
  }
}
