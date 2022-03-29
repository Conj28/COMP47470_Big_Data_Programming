
val kb_movies = graph.triplets.filter{triplet => triplet.srcAttr == "Kevin Bacon (I)"}.foreach(println)
//can use the count to find out the total number = 57