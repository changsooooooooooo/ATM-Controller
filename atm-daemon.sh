#!/bin/bash

BUILD_PATH=./build/libs
JAR_NAME="ATM-bear-robotics.jar"
ATM_PID=$(pgrep -f $JAR_NAME)
echo "RUNNING PROCESS ID IS $ATM_PID"
kill -15 $ATM_PID
gradle build
cd $BUILD_PATH
nohup java -jar ATM-bear-robotics.jar > /dev/null 2>&1&
