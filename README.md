# Tabby Cat GitHub Explorer
This software is an implementation of the Tabby GitHub Explorer project. This is a case study for the forthcoming Springer book "Agile Software Engineering Skills."


# Running Locally (No Docker)

## Dependencies

- Java 1.8 (& Maven)
- NodeJS

### Clone Repository

`git clone https://github.com/BenDMDev/github-explorer.git` 

For this fork use

`git clone https://github.com/julianbass/github-explorer.git` 

### Setting Up the API Server

Once you have cloned the repository change into the server directory and use the following command:

`chmod +x mvnw`

`./mvnw spring-boot:run`

The API can be accessed via http://127.0.0.1:8080

### Setting up the Client "Dev" Server

From the root directory of the repository, change into the "client" directory. You'll need to install dependencies first using the following command:

`npm install`

Once dependencies have been install you can run a local development server which serves the HTML/JS files and acts as a reverse proxy (to avoid CORS issues). This is a convenience script that allows real time development of the client without the need to rebuild/deploy. 

`node local-dev-server.js`

The client can be accessed via http://127.0.0.1:4000


# Running Locally (Docker)

If you don't wish to install dependencies locally you can install Docker (however you will need Windows Pro to run Docker Desktop for Windows). 

### Dependencies

- Docker 
- Docker compose

### Building the API Server

From the root directory of the repository change into the server directory and build the docker image using the following command:

`sudo docker build . -t git-explorer-api`


### A Note on GitHub Rate limiting

By default GitHub limits a single IP address to 60 requests, if you need to make more you'll need to send authenticated requests (limit increases to 5000). This can be handled using a personal access token https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token 

Once you have created a personal access token you can inject this via the environmental variable GIT_ACCESS_TOKEN. This can be done a number of ways. 

- Running locally:
    - Add a file called ".env" to the server directory and add the key value pair GIT_ACCESS_TOKEN=yourtoken
    - Add a environmental variable directly to your system (e.g using the CLI in ubuntu or Windows System environmental variables UI). The format follows the same as above i.e GIT_ACCESS_TOKEN=yourtoken
- Running via Docker:
    - Add a .env file to the root directory and add the key value pair GIT_ACCESS_TOKEN=yourtoken


### Running the Client/Server application

For convenience there is a docker-compose file which allows the front end and server side to be built and ran in a single line command:

`sudo docker-compose up -d`

(this needs to be run in the root directory, at the same level as the docker-compose.yml file).

This will download the latest version of nginx and copy a preconfigured nginx conf file, as well as the client source files, run the pre-build server image and connect them via a local network.



