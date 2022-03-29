val deg = graph.degrees
val result = deg.filter {case ((movieId, num)) => movieId < 999}
val filmMap = verts.map { case ((id), name) => (id -> name) }.collect.toMap
val most_actors = result.map(x => (filmMap(x._1), x._2))
most_actors.sortBy(_._2, ascending=false).take(5).foreach(println)
