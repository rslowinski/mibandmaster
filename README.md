# database

`docker pull postgres:11`

`docker run --name mibandtoolsdb -p 5444:5432 -e POSTGRES_PASSWORD=pass -d postgres:11`

######dev-db `docker exec mibandtoolsdb psql -U postgres -c"Create DATABASE mibandtoolsdb" postgres`
######test-db `docker exec mibandtoolsdb psql -U postgres -c"Create DATABASE test_mibandtoolsdb" postgres`