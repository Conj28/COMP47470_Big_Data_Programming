
#!/bin/bash

inputFile=nasa.csv
numcolumns=$(head -1 $inputFile | awk -F, '{print NF}')

cols_to_keep=""

for i in $(seq 1 $numcolumns); do 
        uniqVals=$(tail -n408 $inputFile | cut -d',' -f$i | uniq | wc -l)
        if [ $uniqVals -gt 1 ]; then
                cols_to_keep=${cols_to_keep}$i',';
        fi
done

#remove last comma
cols_to_keep=${cols_to_keep: : -1}

#create new dataset
cut -d"," -f $cols_to_keep $inputFile > removed_vals.csv
