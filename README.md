## **Java practice studentSolution**

## Summary

Create a REST API for a system that assigns students to classes.

## What you'll need
  - A favorite IDE. I used Intellij IDEA
  - create a basic maven project
  - JDK 1.7 or later
  
## Technologies

For the practice I used **Spring Framework.** Just I needed (Spring Boot, web, jpa)

## database and model

I used h2 database 

Student = {id,  firstName, lastName}
Class = {id, title, description}

## data loaded
| (Id=1) English | (Id=2) Intro Progra |
| --- | --- |
| (Id=1) Andres Merida | (Id=3) Juan Perez |
| (Id=2) Ana Arevalo | (Id=4) Armando Carpas |

## What you'll build.
Run the project:

You can find the next services

* http://localhost:8080/students        GET (get all students)
* http://localhost:8080/students        POST (save a student)
* http://localhost:8080/students?search=firstName:a,lastName:Are    GET (search students with prefix firstName 'a' and lastName 'Are')
* http://localhost:8080/students/1      GET (get a student with id 1)
* http://localhost:8080/students/1      PUT (Edit a student with id 1)
* http://localhost:8080/students/1      DELETE (Delete a student with id 1)
* http://localhost:8080/students/1/classes/  GET (Get classes for the student with id 1)
 
##### Same way for Classes:  http://localhost:8080/classes/ GET (get all classes) 

## Tech stack

I have used **Java** as a programming language because I think it is the language that best applies to object-oriented programming.

I have used **Spring Framework** because it is the most complete java framework and popular with a large community. It allows you to easily configure many things.

## Download source code

You can download all source code from this url on Git Hup:

https://github.com/andresmerida/students

