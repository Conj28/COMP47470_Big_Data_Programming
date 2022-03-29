val inputFile = sc.textFile("asgn/netflix.csv")

def parseLine(line: String) = {
	val field = line.split(",")
	val day: String = field(6)
	day.trim
}

def getMonth(mon: String) = {
	val month = mon.split(" ").head
	month
}

val date = inputFile.map(parseLine)
val filtered = date.filter(x => x!="date_added")
val monthCount = filtered.map(getMonth).
map(mon => (mon, 1)).
reduceByKey(_ + _);

val results = monthCount.collect()
results.sortBy(_._2).reverse.foreach(println)