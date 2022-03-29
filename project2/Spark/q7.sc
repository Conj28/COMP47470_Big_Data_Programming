//Change the colum number to make it work
var inputFile = sc.textFile("prac/netflix3.csv")

def parseLines(line: String) = {
	val field = line.split(",")
	val desc: String = field(7)
	desc
}

def getDecade(input: String) = {
val year = input.toInt
var res=""

if(year < 1930) res = "1920s"
else if(year < 1940) res = "1930s"
else if(year < 1950) res = "1940s"
else if(year < 1960) res = "1950s"
else if(year < 1970) res = "1960s"
else if(year < 1980) res = "1970s"
else if(year < 1990) res = "1980s"
else if(year < 2000) res = "1990s"
else if(year < 2010) res = "2000s"
else if(year < 2020) res = "2010s"
else if(year < 2030) res = "2020s"
res
}

var years = inputFile.map(parseLines)
var filters = years.filter(x => x!="release_year")
var yearCount = filters.map(getDecade).
map(desc => (desc, 1)).
reduceByKey(_ + _);

var resultsQ7 = yearCount.collect()
resultsQ7.sortBy(_._2).reverse.foreach(println)