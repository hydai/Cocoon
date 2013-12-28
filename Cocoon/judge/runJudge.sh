#!bin/bash
# Program:
#	Running judge system
# Author: hydai
# Time: 2013/12/26

STRICT="NO"
TIME_LIMIT=1000
MEMORY_LIMIT=131072
CC=clang
PROBLEM_ID=1
RUNTIME_ID=1
usage() {
	echo "Usage: $0 [argument] [option]";
	echo "argument:";
	echo "    -h              : Print help message"
	echo "    -p PROBLEM_ID   : Problem index"
	echo "    -r RUN_ID       : Runtime ID"
	echo "    -t TIME_LIMIT   : Time limit in ms (default: 1000)"
	echo "    -m MEMORY_LIMIT : Memory limit in kb (default: 131072)"
	echo "    -c [C|CPP]      : Indicate C/CPP (default: C)"
	echo "option:"
	echo "    -s              : Is strict judge (default: torien judge"
	exit 1;
}

# get input arguments
while getopts "hst:m:c:p:r:" ARGS; do
    case "${ARGS}" in
        h)
			usage
			;;
        s)
			STRICT="YES"
			echo "Use strict judge"
            ;;
		t)
			TIME_LIMIT=${OPTARG}
			echo "Time limit "${TIME_LIMIT}
			;;
		m)
			MEMORY_LIMIT=${OPTARG}
			echo "Memory limit "${MEMORY_LIMIT}
			;;
		c)
			CC=${OPTARG}
			echo "CC = "${CC}
			;;
		p)
			PROBLEM_ID=${OPTARG}
			echo "Problem id = "${PROBLEM_ID}
			;;
		r)
			RUNTIME_ID=${OPTARG}
			echo "Runtime id = "${RUNTIME_ID}
			;;
        ?)
            usage
            ;;
    esac
done
shift $((OPTIND-1))

#Start judge
mkdir "$RUNTIME_ID"
cp judge.sh "$RUNTIME_ID"

if [ $CC = "C" ]
then
	cp ${RUNTIME_ID}.c $RUNTIME_ID
elif [ $CC = "CPP" ]
then
	cp ${RUNTIME_ID}.cpp $RUNTIME_ID
fi

cd "$RUNTIME_ID"
if [ $STRICT = "YES" ]
then
	sh judge.sh -s -t ${TIME_LIMIT} -m ${MEMORY_LIMIT} -c ${CC} -p ${PROBLEM_ID} -r ${RUNTIME_ID}
elif [ $STRICT = "NO" ]
then
	sh judge.sh -t ${TIME_LIMIT} -m ${MEMORY_LIMIT} -c ${CC} -p ${PROBLEM_ID} -r ${RUNTIME_ID}
fi

cp ${RUNTIME_ID}.txt ../
cd ..
rm -rf "$RUNTIME_ID"
echo "========================================================="
cat ${RUNTIME_ID}.txt
echo "========================================================="
exit 0

#End of script
