#!/bin/bash
pgrep "^hugo$" | xargs -r kill
./localSite.sh &

mvn -f Tests -DhostAndPort=localhost:1313 clean test
TEST_RESULT=$?

pgrep "^hugo$" | xargs -r kill

exit $TEST_RESULT
