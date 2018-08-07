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

## Setup the app in Eclipse 
Since Lambok is not fully supported by Eclipse need to patch eclipse to support fully follow below steps
To Run in Eclipse:
# Steps:1 Down load Lambok Jar from https://projectlombok.org/downloads/lombok.jar
# Step:2 run the below command in CL
    java -jar lombok.jar
Select Eclipse to install Lambok Support
# Step3: Restart eclipse and Machine
# Step:4 Clone project https://github.com/Dharamdas/account_cassandra_Int and import in Eclipse
# Step5: Refresh project (Project right click-- Gradle --> refresh)
# Step6: Project will display in Gradle Task (Build --> clean and Build)
# Step7: Run spring boot app (go to AccountApp.java - right clik and run as application)

