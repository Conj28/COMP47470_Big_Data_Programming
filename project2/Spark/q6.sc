//Change the colum number to make it work
val inputFile = sc.textFile("asgn/netflix.csv")

def parseLine(line: String) = {
	val field = line.split(",")
	val desc: String = field(12)
	desc
}

def removeVals(str: String) = {
    val res1 = str.replaceAll("[^a-zA-Z0-9]","").toLowerCase()
    res1
}

val descriptions = inputFile.map(parseLine)
val descriptionCount = descriptions.flatMap(line => line.split(" ")).
map(removeVals).
map(desc => (desc, 1)).
reduceByKey(_ + _);

val results = descriptionCount.collect()
results.sortBy(_._2).reverse.foreach(println)
//not reversed as list to big to view top so bottom is the max