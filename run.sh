#!/bin/bash


cd /root/zhihu-read-rss
export LEIN_ROOT=1
succ=`lein run rss/rss.xml`

if [ $succ == '1' ] 
    then 
    echo 'rss gen success!'
    git commit -am 'auto commit'
    git push origin master
fi
