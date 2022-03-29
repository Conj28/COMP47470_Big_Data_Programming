val numverts = graph.numVertices
//long = 1747
val numEdges = graph.numEdges
//Long = 1817

val numMovies = graph.vertices.filter{case(v) => v._1 > 999}.count
//Long = 1586

val numActors = graph.vertices.filter{case(v) => v._1 < 999}.count
//Long = 161