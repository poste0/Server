#!/bin/sh
if [ $1 = 'help' ]
then
 echo "hello"
else
$1/javac $2
cd $3
$1/javah -jni -o $4 $5
cd $6
java -Djava.library.path=$3/. $5
fi
