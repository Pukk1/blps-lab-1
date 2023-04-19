#!/bin/sh
set -e

psql --username postgres --dbname postgres <<-EOSQL
  CREATE DATABASE main;
  CREATE ROLE main WITH ENCRYPTED PASSWORD 'main' LOGIN;
  GRANT ALL PRIVILEGES ON DATABASE main TO main;

  CREATE DATABASE billing;
  CREATE ROLE billing WITH ENCRYPTED PASSWORD 'billing' LOGIN;
  GRANT ALL PRIVILEGES ON DATABASE billing TO billing;
EOSQL

psql --username postgres --dbname main <<-EOSQL
  GRANT ALL ON SCHEMA public TO main;
EOSQL

psql --username postgres --dbname billing <<-EOSQL
  GRANT ALL ON SCHEMA public TO billing;
EOSQL
