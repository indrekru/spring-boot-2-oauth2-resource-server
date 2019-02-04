# Spring Boot 2 Oauth2 resource and authorization server

Spring Boot 2 OAuth2 resource and authorization server implementation with Database for Users and Clients (JPA, Hibernate, MySQL)

## Running

Here's a way to run it with a docker mysql server (highly recommended for developing/testing).
If you wish to run it against your native mysql environment, then just change the `application.properties` file or define your own profile `application-[your-profile].properties`.

1. Install docker
2. Run the docker container in the root of the project `docker-compose up`
3. Create a new terminal tab and cd into `bash_scripts` folder, run `./create-tables-add-data.sh` (This will setup all the oauth DB tables)
4. Install dependencies `mvn clean install`
5. Run it `mvn spring-boot:run`

Server should now be up and running on `http://localhost:8080`.

## Get an access token with Postman

1. Install Postman
2. Import Postman collection from the `project.postman_collection.json` file
3. Run the `/oauth/token` POST request and get a access_token.
4. Change the token value to the access_token in the other requests and you should get 200 OK responses.

## Database

Mysql 5.7
