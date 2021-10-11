#!/bin/bash
(git add --all && git commit -m "-" && git pull origin master && git push origin master) &&
mvn clean install -U && mvn release:prepare -Dresume=false && mvn release:perform -X
exit 0
