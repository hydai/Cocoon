#!bin/bash
# Judge system for C
# Author: hydai
# Time: 2013/12/26

STRICT=
TIME_LIMIT=1000
MEMORY_LIMIT=131072
CC=clang
PROBLEM_ID=1
RUNTIME_ID=1
PROBLEM_PATH=../../problem/${PROBLEM_ID}/
ANTISKILL_EXEC=../../antiskill
RESULT_TXT=${RUNTIME_ID}.txt
COMPILE_TXT=compile_message.txt
COMPILE_SOURCE=code.c
RUNTIME_FILE=code
RUNTIME_RESULT=state.txt
INPUT="$PROBLEM_PATH"input.txt
OUTPUT=output.txt
ANSWER="$PROBLEM_PATH"answer.txt
RUNTIME_ERROR="ProgramKilled"
TIME_LIMIT_EXCEED="TimeLimitExceeded"
MEMORY_LINIT_EXCEED="MemoryLimitExceeded"
CHECK_ANSWER_EXEC=../answerJudge
CHECK_ANSWER_FILE=final.txt
ACCEPT="Accept"
WRONG_ANSWER="WrongAnswer"
TEMP_FILE=tmp.txt


# get input arguments
while getopts "hst:m:c:p:r:" ARGS; do
    case "${ARGS}" in
        s)
			STRICT="-s"
            ;;
		t)
			TIME_LIMIT=${OPTARG}
			;;
		m)
			MEMORY_LIMIT=${OPTARG}
			;;
		c)
			if [ ${OPTARG} = CPP ]
			then
				CC="clang++"
			else
				CC="clang"
			fi
			;;
		p)
			PROBLEM_ID=${OPTARG}
			;;
		r)
			RUNTIME_ID=${OPTARG}
			;;
    esac
done
shift $((OPTIND-1))

PROBLEM_PATH=../../problem/${PROBLEM_ID}/
INPUT="$PROBLEM_PATH"input.txt
ANSWER="$PROBLEM_PATH"answer.txt
RESULT_TXT=${RUNTIME_ID}.txt
COMPILE_SOURCE=${RUNTIME_ID}
RUNTIME_FILE=${RUNTIME_ID}
if [ ${CC} = "clang++" ]
then
	COMPILE_SOURCE=${COMPILE_SOURCE}.cpp
else
	COMPILE_SOURCE=${COMPILE_SOURCE}.c
fi


echo "Creat "$RESULT_TXT
touch $RESULT_TXT

echo "Compile ""$COMPILE_SOURCE"
${CC} -static "$COMPILE_SOURCE" -o "$RUNTIME_FILE" > "$COMPILE_TXT" 2>&1

echo "Check "$RUNTIME_FILE
if [ ! -f ./$RUNTIME_FILE ]
then
    echo "Compile Error"
    echo "Compile Error\n" > $TEMP_FILE
    cat $TEMP_FILE $COMPILE_TXT > $RESULT_TXT
    exit 1
fi

echo "Run "$RUNTIME_FILE" with antiskill"
"$ANTISKILL_EXEC" $RUNTIME_FILE -i $INPUT -o $OUTPUT > $RUNTIME_RESULT
STATE=$(sed -n 1p $RUNTIME_RESULT)
if [ "$STATE" = "$RUNTIME_ERROR" ]
then
    echo "Runtime Error"
    echo "Runtime Error\nCheck your pointer or array\n" > $RESULT_TXT
    exit 1
elif [ $STATE = "$TIME_LIMIT_EXCEED" ]
then
    echo "Time Limit Exceeded"
    echo "Time Limit Exceeded\nCheck your loop or algorithm\n" > $RESULT_TXT
    exit 1
elif [ $STATE = "$MEMORY_LINIT_EXCEED" ]
then
    echo "Memory Limit Exceeded"
    echo "Memory Limit Exceeded\nCheck your memory usage\n" > $RESULT_TXT
    exit 1
fi

echo "Check if your answer is correct or not"
$CHECK_ANSWER_EXEC ${STRICT} $ANSWER $OUTPUT > $CHECK_ANSWER_FILE
STATE=$(sed -n 1p $CHECK_ANSWER_FILE)
if [ $STATE = "$WRONG_ANSWER" ]
then
    cat $CHECK_ANSWER_FILE > $RESULT_TXT
    exit 0
elif [ $STATE = "$ACCEPT" ]
then
    cat $CHECK_ANSWER_FILE > $RESULT_TXT
    exit 0
fi

# End of script
