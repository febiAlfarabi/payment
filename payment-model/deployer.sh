#!/bin/bash
(git add --all && git commit -m "-" && git pull origin master && git push https://febiAlfarabi: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@github.com/febiAlfarabi/payment.git origin master) &&
mvn clean install -U && mvn release:prepare -Dresume=false && mvn release:perform -X
exit 0
