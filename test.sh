#!/bin/sh

echo 'Premier coup'
curl -s -X POST localhost:8080/game/1/1/c | jq
echo -e '\nSecond coup'
curl -s -X POST localhost:8080/game/1/2/p | jq
echo -e '\nRésultat'
curl -s localhost:8080/game/1/ | jq
