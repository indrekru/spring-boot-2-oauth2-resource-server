#!/bin/bash

docker container exec -i $(docker-compose ps -q mysql) mysql -uroot -pchangeme project < ../docker/mysql/oauth.sql