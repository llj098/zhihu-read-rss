#!/bin/bash


succ=`lein run zhihu-reader/zhihu-reader-rss.xml`

if [ $succ == '1' ] 
    then 
    echo 'rss gen success!'
    cd zhihu-reader
    git commit -am 'auto commit'
    git push origin master
fi
