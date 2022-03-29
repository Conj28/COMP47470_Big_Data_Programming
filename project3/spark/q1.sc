import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.util.IntParam
import org.apache.spark.graphx._
import org.apache.spark.graphx.util.GraphGenerators

case class Film(actor:String, actorID:Long, movie:String, movieID:Long)

//create a film object(includes movies and actors)
def parseFilm(str: String): Film = {
	val line = str.split(",")
	Film(line(0), line(1).toLong, line(2), line(3).toLong)
}

var textRDD = sc.textFile("/asgn3/bacon.csv")
val header = textRDD.first()
textRDD = textRDD.filter(row => row != header)
val filmRDD = textRDD.map(parseFilm).cache()
val verts = filmRDD.flatMap(film => Seq((film.actorID, film.actor),(film.movieID, film.movie))).distinct

val connections = filmRDD.map(film => (film.actorID,film.movieID)).distinct
val edges = connections.map {case (actorID, movieID) =>Edge(actorID, movieID,1) }
//default vertex
val default = "default"

val graph = Graph(verts, edges, default)
