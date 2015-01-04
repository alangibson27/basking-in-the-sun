#!/bin/sh
PID=`lsof -t -i:1313 -sTCP:LISTEN`
if [ $PID ]; then
  kill $PID
fi

hugo server --destination=.hugolocal --buildDrafts=true --theme=hinatabokko &

