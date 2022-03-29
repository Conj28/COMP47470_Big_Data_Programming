#!/bin/bash

tail -n408 nasa.csv > tempNasa.csv

while read -r var
do

    id=$(echo $var | cut -d"," -f7)
    lat=$(echo $var | cut -d"," -f9)
    long=$(echo $var | cut -d"," -f10)
    month=$(echo $var | cut -d"," -f2)
    sec=$(echo $var | cut -d"," -f6)

    mongo asgn1 --eval 'db.nasa.insert({sample_id: '$id', sample_latitude:'$lat', sample_longitude: '$long', sample_month: '$month', sample_seconds: '$sec'})'
    

done < tempNasa.csv

rm tempNasa.csv