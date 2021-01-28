#!/usr/bin/env bash

#${1} MODE = prod | dev | both
if [ $1 = "" ];then
  printf "Provide MODE = prod | dev | both"
  return 1
fi &&


(git add --all && git commit -m "-" && git push origin master) || true &&

if [ $1 = "dev" ];then
  ssh root@144.91.85.99 "sh /opt/payment/master/update-and-deploy.sh ${1}"
elif [ $1 = "prod" ];then
  ssh root@144.91.85.99 "sh /opt/payment/master/update-and-deploy.sh ${1}"
elif [ $1 = "both" ];then
  ssh root@144.91.85.99 "sh /opt/payment/master/update-and-deploy.sh dev" &&
  ssh root@144.91.85.99 "sh /opt/payment/master/update-and-deploy.sh prod"
fi &&

exit 0

