# account_cassandra_Int
to run this application install cassandra in local
use below commands:
### Cassandra install notes
cat /etc/issue
 echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
 curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -
 sudo apt-get update
 sudo apt-get install cassandra  -y

## Provide below Credtional
  username: cassandra
  contactpoints: 127.0.0.1
  keyspace: test01
  password: cassandra
  
 Create key space with  test01
 use below command
 CREATE KEYSPACE test01 WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

## Create Table

CREATE TABLE test01.account (
    guestid int PRIMARY KEY,
    createtimestamp timestamp,
    email text,
    firstname text,
    lastname text,
    mobile text,
    passsword text
)

## Execute Application
