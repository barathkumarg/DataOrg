# DataOrg
DataOrg is a Lite version of Online DataBase Management Tool, Where User can maintain their data in tables under seperate Org(DataBasse)

## Introduction:
DataOrg is lite online Data Base Management tool with basic operations

## Tech Stack
* Java Servlets
* MySQL
* JDBC
* Ajax
* Bootstrap

## Tech Actions Implemented
* Followed Layered Architecture Servlet - Data Transfer Layer - Bean Layer - Data Access Layer in development
* Http Session Management
* Request - Response Acknowledgement (Every action will get the proper response as alert before rdirection)
* Singleton Design pattern for DB Connection
* Usage of Request Dispatcher and Send Direct in the apt places.
* Password and user authentication

# Modules and working
* Login - Register : Standard Login, Register template with js perchecks
* User - DB : Every user where to be associated with Seperate Data base on their own (DB Automatically created with username when attempts to create the table for first time)
* Table creation : User can create Table which will be mapped under user DataBase
* Table - constraints: One primary key and dynamic range of Columns can be created
* Table data can be deleted , updated and added
