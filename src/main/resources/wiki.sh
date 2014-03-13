#!/bin/bash

IP=`ifconfig eth0 | grep "inet addr" | cut -d: -f2 | awk '{print $1}'`
BCASTIP=`ifconfig eth0 | grep "inet addr" | cut -d: -f3 | awk '{print $1}'`
sed s/\@NODE_IP\@/${IP}/g jgroups-tcp.xml.tmpl | sed s/\@BPING_ADDR\@/${BCASTIP}/g  > jgroups-tcp.xml
sed s/\@NODE_IP\@/${IP}/g infinispan-clustered-tcp.xml.tmpl | sed s/\@BPING_ADDR\@/${BCASTIP}/g  > infinispan-clustered-tcp.xml
java -cp ".:wiki.jar" eu.leads.XmlDumpParser $1


