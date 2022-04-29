This projects is implementation of workmotion senior backend challange

**Specifications**
Spring Boot 2.6.7
Spring state machine
Lombok
In memory H2 database.
Dockerfile

**How to run Note:**
* Build the applicaiton mvn clean package. This will create challange.1.0.0.jar in target folder
* Build docker image docker build -t challange-image .
* Run the image docker run -d -it --name challange challange-image:latest
* By default application is exposed on port 8080
* Employees table will be loaded with 1 record. 

**api references **
* http://localhost:8080/employees : get all employees
* http://localhost:8080/employees/add : to add employee with below body
  {
    "empId": 2,
    "empName": "newEmpName",
    "state": "ADDED"
  }
* http://localhost:8080/employees/beginCheck/{empId} : This will change state to IN-CHECK. Database will be updated.



 
  
