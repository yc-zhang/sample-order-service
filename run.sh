#!/bin/sh

xmx_threshold=60 # What percentage of memory do we want to setup xmx heap size
xms_threshold=30 # What percentage of memory do we want to setup xms heap size

total_mem_in_host=$(head -n 1 < /proc/meminfo | awk '{print $2}') # Outputs in kilobytes

xmx_heap_size=$((${xmx_threshold} * ${total_mem_in_host} / 100 / 1024)) # Outputs in megabytes
xms_heap_size=$((${xms_threshold} * ${total_mem_in_host} / 100 / 1024)) # Outputs in megabytes

java -Xmx${xmx_heap_size}m \
     -Xms${xms_heap_size}m \
     -jar $1
