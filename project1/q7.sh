#!/bin/bash
if [ $# -ne 2 ]; then
    echo "Please provide two arguments Colum name and row number"
else

numcolumns=$(head -1 $inputFile | awk -F, '{print NF}')

for i in $(seq 1 $numcolumns); do 
        col_name=$(head -n1 nasa.csv | cut -d"," -f$i)
        if [ '$col_name' -eq '$1' ]; then 
            echo "ahhhhhh";
        break;
        fi
done
fi