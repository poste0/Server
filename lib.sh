#!/bin/sh
if [ $1 = 'help' ]
then
 echo "hello"
else
cd $1
gcc -shared -fpic -o libMain.so -I $2   -I $3 $4
fi
