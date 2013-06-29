#!/bin/bash


cd /root/zhihu-read-rss
export LEIN_ROOT=1
succ=`java -jar target/zhihu-read-rss-0.1.0-SNAPSHOT-standalone.jar rss/rss.xml`

if [ $succ == '1' ] 
    then 
    echo 'rss gen success!'
    git commit -am 'auto commit'
    git push origin master
fi
