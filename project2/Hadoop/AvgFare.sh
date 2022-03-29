#!/bin/bash 

hdfs dfs -cat /q7_p1/* >  p1.txt
hdfs dfs -cat /q7_p2/* > p2.txt

for i in $(seq 1 7); do
	day=$(cut -f1 p1.txt | head -n $i | tail -n 1)
	trips=$(cut -f2 p1.txt | head -n $i | tail -n 1)
	total=$(cut -f2 p2.txt | head -n $i | tail -n 1)
	avg=$((total/trips))
	echo $day  $avg
done   
