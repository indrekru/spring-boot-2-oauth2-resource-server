# Spring Boot 2 Oauth2 resource server example

This is a bare-bones Spring Boot 2 Oauth2 resource server with an authorization server.
I put this here, cause there was no good example out there on how to do it. 

This is a bootstrap project for all the startups out there who have trouble hiring talent and have to do with low paid students, who crawl stackoverflow all day to hack something together. Do it properly and save a bunch of money!

## Running

Here's a way to run it with docker (highly recommended for developing/testing).
If you wish to run it against your native environment, then just change the `application.properties` file.

Docker way:
1. Install docker
2. Run the docker container in the root of the project `docker-compose up`
3. Create a new terminal tab and cd into `bash_scripts` folder, run `./create-tables-add-data.sh` (This will setup all the oauth DB tables)
4. Run the java project from `Application.java` main method

Server should now be up and running.

## Test API with Postman

1. Install postman
2. Import postman collection from the `project.postman_collection.json` file
3. Run the `/oauth/token` POST request and get a access_token.
4. Change the token value to the access_token in the other requests and all should be working.

## Password encryption

Spring Security 5 drastically changed the way they store passwords,
this project stores all passwords and client_details in BCrypt encryption.

And I think that's great!
