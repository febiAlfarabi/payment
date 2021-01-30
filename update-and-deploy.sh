#!/usr/bin/env bash

#${1} MODE = prod | dev | both
ACTIVE_PROFILE="dev"
API_DOMAIN_TLD="payment.numeralasia.com"

if [ $1 = "" ];then
  printf "Provide MODE = prod | dev | both"
  return 1
fi &&

if [ $1 = "dev" ];then
  ACTIVE_PROFILE="dev"
  API_DOMAIN_TLD="dev-payment.numeralasia.com"
else
  ACTIVE_PROFILE="prod"
  API_DOMAIN_TLD="payment.numeralasia.com"
fi &&

cd /opt/payment/master &&
git stash &&

(git pull https://alfarabidwik:Gioibanez%2E1@github.com/febiAlfarabi/payment.git || true) &&

cd /opt/payment/master &&
> "service/src/main/resources/application.properties" &&
echo "spring.profiles.active=$ACTIVE_PROFILE" >> "service/src/main/resources/application.properties" &&
mvn clean install -U &&
cd service &&
yes | cp service/target/payment.jar /opt/payment/$API_DOMAIN_TLD/payment.jar &&
sudo service $API_DOMAIN_TLD restart &&

exit 0

