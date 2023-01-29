# Project-Sem3-Java-Spring-App
> Java Spring application that fetches records from wolnelektury.pl open API and allows local work on them.

## Table of Contents
* [General Information](#general-information)
* [Technologies Used](#technologies-used)
* [Build and Run](#build-and-run)
* [Usage](#usage)
* [Project Status](#project-status)
* [Contact](#contact)

## General Information
An application written in Java Spring Boot that requests data from [wolnelektury open API](https://wolnelektury.pl/api/) and updates its database with.\
Allows basic CRUD operations on local database and also to view logs gathered throughout the app's runtime.
This is a project made for the university, where minimal requirements were as follows:
- at least 3 tables in database,
- databse crud layer using Spring repositories,
- REST controllers for database resources

I also achieved extended requirements:
- logic based on updating database from an external public service
- use logging dependency and have service to show those logs

hello

## Technologies Used
- Java 17
- Spring Boot 3
- Log4j 2.19
- XAMPP 8.1.4

## Build and Run
To properly run the project you will need XAMPP and IntelliJ Idea so that almost everything is done automaticly, but those are not necesarry if you know what you're doing.

First launch Apache and MySQL on XAMPP Control Panel. Apache is used to verify if everything is working correctly with the database through its database managing panel `localhost:8080/phpmyadmin`. Default options for ports are 8080 for Apache and 3306 for Server. If you have different ports you will have to change them either here or later in the project properties.

Next is to launch the project in IntilliJ Idea and wait for everything to download. You might need to look for a file called `application.properties` and change few things, like name of the database (jaz_lektury), credentials (root without password) or application's port (8081). Once it is done, simply navigate to `LekturySpringApplication` class and launch it.
If everything goes right you will see a basic JSON response on `localhost:8081`.

## Usage
There are three actions one can do with this application:

### Update the database
To update the database you have posibility of three paths:
- localhost:8081/db/update
- localhost:8081/db/clean-update
- localhost:8081/db/debug-update

Normal update simply adds records from wolnelektury to our database. 

Clean Update erases everything from our tables and then gets data from wolnelektury. 

Debug Update erases everything and then adds limited amount of records from wolnelektury, so that this insertion is way faster than getting everything.
Once the page has finished loading, everything should be set. You can keep your eye on the IntelliJ console output or log file in catalog /logs to see more details about updating the database.

### Manage the database
This application supports all CRUD operations for every table, where paths are easily accesible both in english and polish language preference. 
Here is a list of possible path requests. To get more, take a look at `ManageDatabaseView` file.
- /api/lektury/list
- /api/autorzy/5
- /api/rodzaje/zaktualizuj/21

To see all the functionality I suggest using a tool made for such requests called Postman. Any other tool that allows us to send GET, POST, PUT and DELETE methods will work as well.

### Checking log messages
There is a path to check log messages: `localhost:8081/logs/read`. It shows all the contents of the log file that gets automatically generated on application startup in /logs catalog.
To filter based on logging level you can pass a GET parameter to the url, like so: `localhost:8081/logs/read?level=error`.
All Log4j-defined log levels are supported, they can be found in `LogReader` file.

## Project Status
Project is fully complete and there is nothing more from me to add to it. Although if any critical bugs would be found I would take my time to fix them so that the project is at least working adequately for its sole purpouse and of course for a good university mark :)

## Contact
Created by s25429 AKA [ThatHoracy](https://github.com/ThatHoracy), but currently posted on an account using school email (this one!)
- Email: cezary.cislak@gmail.com
- LinkedIn: [profile page](https://www.linkedin.com/in/cezary-ci%C5%9Blak-913559237/)
