#!bin/bash
# Judge system for C
# Author: hydai
# Time: 2013/12/26

PROBLEM_PATH=../../problem/1/
ANTISKILL_EXEC=../../antiskill
RESULT_TXT=result.txt
COMPILE_TXT=compile_message.txt
COMPILE_SOURCE=code.c
RUNTIME_FILE=code
RUNTIME_RESULT=state.txt
INPUT="$PROBLEM_PATH"input.txt
OUTPUT=output.txt
ANSWER="$PROBLEM_PATH"answer.txt
RUNTIME_ERROR=Program\ Killed
TIME_LIMIT_EXCEED=Time\ Limit\ Exceeded
MEMORY_LINIT_EXCEED=Memory\ Limit\ Exceeded
CHECK_ANSWER_EXEC=answerJudge
CHECK_ANSWER_FILE=final.txt
ACCEPT=Accept
WRONG_ANSWER=Wrong\ Answer
TEMP_FILE=tmp.txt

echo "Creat "$RESULT_TXT
touch $RESULT_TXT

echo "Compile "$COMPILE_SOURCE
clang -static $COMPILE_SOURCE -o $RUNTIME_FILE >& $COMPILE_TXT

echo "Check "$RUNTIME_FILE
if [ ! -f ./$RUNTIME_FILE ]
then
    echo "Compile Error"
    echo "Compile Error\n" > $TEMP_FILE
    cat $TEMP_FILE $COMPILE_TXT > $RESULT_TXT
    exit 1
fi

echo "Run "$RUNTIME_FILE" with antiskill"
"$ANTISKILL_EXEC" -i $INPUT -o $OUTPUT > $RUNTIME_RESULT
if [ ( sed -n 1p $RUNTIME_RESULT ) = $RUNTIME_ERROR ]
then
    echo "Runtime Error"
    echo "Runtime Error\nCheck your pointer or array\n" > $RESULT_TXT
    exit 1
elif [ ( sed -n 1p $RUNTIME_RESULT ) = $TIME_LIMIT_EXCEED ]
then
    echo "Time Limit Exceeded"
    echo "Time Limit Exceeded\nCheck your loop or algorithm\n" > $RESULT_TXT
    exit 1
elif [ ( sed -n 1p $RUNTIME_RESULT ) = $MEMORY_LINIT_EXCEED ]
then
    echo "Memory Limit Exceeded"
    echo "Memory Limit Exceeded\nCheck your memory usage\n" > $RESULT_TXT
    exit 1
fi

echo "Check if your answer is correct or not"
./$CHECK_ANSWER_EXEC -s $ANSWER $OUTPUT > $CHECK_ANSWER_FILE
if [ ( sed -n 1p $CHECK_ANSWER_FILE ) = $WRONG_ANSWER ]
then
    $CHECK_ANSWER_FILE > $RESULT_TXT
    exit 0
elif [ (sed -n 1p $CHECK_ANSWER_FILE ) = $ACCEPT ]
then
    $CHECK_ANSWER_FILE > $RESULT_TXT
    exit 0
fi

# End of script
