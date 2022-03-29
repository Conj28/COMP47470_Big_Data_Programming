//Change the colum number to make it work
val inputFile = sc.textFile("asgn/netflix.csv")

def parseLine(line: String) = {
	val field = line.split(",")
	val country: String = field(5)
	country
}
val countries = inputFile.map(parseLine)
val filtered = countries.filter(x => x!="country")
val countryCount = filtered.flatMap(line => line.split(";")).
map(c => c.trim).
filter(c => c != "").
map(country => (country, 1)).
reduceByKey(_ + _);

val results = countryCount.collect()
results.sortBy(_._1).foreach(println)