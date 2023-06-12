#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
BUILDROOT=$DIR/..
cd $BUILDROOT/..

mvn clean package -DskipTests

DATE=$(date +'%Y%m%d')
COMMIT_ID=$(git rev-parse --short HEAD)
IMAGE_NAME=odd-server:$DATE-$COMMIT_ID

docker build -t $IMAGE_NAME -f $DIR/Dockerfile --build-arg ENV_FILE=./build.env $BUILDROOT