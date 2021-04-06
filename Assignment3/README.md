# Assignment 3 - EDMA

### Table of contents

- [Assignment description](#assignment-description)
- [Solution](#solution)
  * [Task 1](#task-1---valuedomains)
  * [Task 2](#task-2---tostring)



## Assignment description
- [Local](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment3/Assignment3.pdf)  
  
Example JSON to use in the assignment. 
```JSON
{
   "student": {
     "name": "John",
     "age": 34
   },
   "courses" : [
     {
       "id": 1234,
       "name": "Seahorse riding",
       "ects": 15
     },
     {
       "id": 4321,
       "name": "Squid painting",
       "ects": 5
     }
   ],
   "active": true
}

```

## Solution
**Our chosen value domains are shown in this figure**  
![image](https://user-images.githubusercontent.com/35559774/113520834-1bdc7d80-9596-11eb-98ee-1cc3a4912ad2.png)



### Task 1 - Valuedomains
> Model the JSON abstract syntax tree using ValueDomains (hint: can be done using around 8 ValueDomains).

Our edmasource valuedomains can be found [here](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment3/edmasrc/assignment.edma).  
  
### Task 2 - ToString
> Create a toString method for printing an instance of your JSON abstract syntax tree as valid Json.

Here's the output of the program:

```json
{"student": { "name": "John", "age":34},"courses": [{"id":1234, "name":"Seahorse riding", "ects":15},{"id":4321, "name":"Squid painting", "ects":5}],"active":true}
```
It is validated by several online tools as well as the JSON package we used.

Here it is prettyfied:
```json
{
   "student":{
      "name":"John",
      "age":34
   },
   "courses":[
      {
         "id":1234,
         "name":"Seahorse riding",
         "ects":15
      },
      {
         "id":4321,
         "name":"Squid painting",
         "ects":5
      }
   ],
   "active":true
}
```
