val deg = graph.degrees //undirected graph so no ned for in or out 
val result = deg.filter {case ((movieId, num)) => num > 2}.filter {case ((movieId, num)) => movieId > 999}

val filmMap = verts.map { case ((id), name) => (id -> name) }.collect.toMap

val more_than_2_actors = result.map(x => (filmMap(x._1), x._2))
more_than_2_actors.foreach(println)
