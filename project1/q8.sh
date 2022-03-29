#!/bin/bash
cp nasa.csv q8Nasa.csv

while read -r var;
do 
    word=$(echo $var | cut -d, -f1)
    replace_with=$(echo $var | cut -d, -f2)
    sed -i s/$word/$replace_with/g q8Nasa.csv
done < dict.txt
