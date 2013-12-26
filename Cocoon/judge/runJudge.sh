#!bin/bash
# Program:
#	Running judge system
# Author: hydai
# Time: 2013/12/26

mkdir "$1"
cp judge.sh "$1"
cd "$1"
sh judge.sh
cp result.txt ../
cd ..
rm -rf "$1"
exit 0

#End of script
