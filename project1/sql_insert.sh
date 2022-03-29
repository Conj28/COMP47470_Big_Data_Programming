#!/bin/bash

tail -n408 nasa.csv > tempNasa.csv

mysql -h localhost -D"asgn1" -e "Truncate table table1"
mysql -h localhost -D"asgn1" -e "Truncate table table2"

while read -r var
do

    id=$(echo $var | cut -d"," -f7)
    lat=$(echo $var | cut -d"," -f9)
    long=$(echo $var | cut -d"," -f10)
    month=$(echo $var | cut -d"," -f2)

    alt=$(echo $var | cut -d"," -f11)
    sec=$(echo $var | cut -d"," -f6)

    mysql -h localhost -D"asgn1" -e "insert into table1 (sample_id, sample_latitude, sample_longitude, sample_month) values('$id' ,$lat, $lat, $long, $month)"
    mysql -h localhost -D"asgn1" -e "insert into table2 (sample_id, sample_altitude, sample_seconds) values('$id' ,$alt, $sec)"

done < tempNasa.csv

echo  $(mysql -h localhost -D"asgn1" -e"Select * from table2")

rm tempNasa.csv