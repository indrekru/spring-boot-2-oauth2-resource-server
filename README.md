# Spring Boot 2 Oauth2 resource and authorization server [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Check%20out%20Spring%20Boot%202%20Oauth2%20resource%20and%20authorization%20server%20example%20https%3A%2F%2Fgithub.com%2Findrekru%2Fspring-boot-2-oauth2-resource-server)

Spring Boot 2 OAuth2 resource and authorization server implementation with Database for Users and Clients (JPA, Hibernate, MySQL)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See running for notes on how to run the project on a system.

### Prerequisites

1. Clone the project to your local environment:
```
git clone https://github.com/indrekru/spring-boot-2-oauth2-resource-server.git
```

2. You need maven installed on your environment:

#### Mac (homebrew):

```
brew install maven
```
#### Ubuntu:
```
sudo apt-get install maven
```

3. You need Docker to be installed:

#### Windows:
https://download.docker.com/win/stable/Docker%20for%20Windows%20Installer.exe

#### Mac:
https://download.docker.com/mac/stable/Docker.dmg

#### Ubuntu:
https://docs.docker.com/install/linux/docker-ce/ubuntu/

### Installing

Once you have maven and docker installed on your environment, install the project dependencies via:

```
mvn install
```

Start docker:

```
docker-compose up
```

Create a new terminal tab and navigate into `bash_scripts` and run (docker is running, this will create tables and add data):
```
./create-tables-add-data.sh
```

## Running

Start docker:
```
docker-compose up
```

Run the application from the `Application.java` main method directly,
or from a command line:
```
mvn spring-boot:run
```

NB! Keep docker running in a separate terminal tab, create another tab to run the application.

Your server should be now running on http://localhost:8080

## Get an access token with Postman

1. Install Postman (https://www.getpostman.com)
2. Import Postman collection from the `project.postman_collection.json` file
3. Run the `/oauth/token` POST request and get a `access_token`.
4. Change the token value to the `access_token` in the other requests and you should get `200 OK` responses.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot 2
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

If you have any improvement suggestions please create a pull request and I'll review it.


## Authors

* **Indrek Ruubel** - *Initial work* - [Github](https://github.com/indrekru)

See also the list of [contributors](https://github.com/indrekru/design-patterns-spring-boot/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License

## Acknowledgments

* Big thanks to Pivotal for Spring Boot framework, love it!
