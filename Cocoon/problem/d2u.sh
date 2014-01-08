#!/bin/bash
for i in $(seq 1 20)
do
	dos2unix ./${i}/input.txt ./${i}/input.txt
	dos2unix ./${i}/answer.txt ./${i}/answer.txt
done
