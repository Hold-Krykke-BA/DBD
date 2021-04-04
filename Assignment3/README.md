# Assignment 3 - EDMA

### Table of contents

- [Assignment description](#assignment-description)
- [Solution](#solution)
  * [Task 1](#task-1---valuedomains)
  * [Task 2](#task-2---tostring)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

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
![image](https://user-images.githubusercontent.com/35559774/113520541-10885280-9594-11eb-8428-acfe7f7a935d.png)


### Task 1 - Valuedomains
> Model the JSON* abstract syntax tree** using ValueDomains (hint: can be done using around 8 ValueDomains).

Our edmasource valuedomains can be found [here](https://github.com/Hold-Krykke-BA/DBD/blob/main/Assignment3/edmasrc/assignment.edma).  
  
### Task 2 - ToString
> Create a toString method for printing an instance*** of your JSON abstract syntax tree as valid Json.