# EMPLOYEE APPLICATION PROJECT

##About Project
	The Application is develop using spring boot and use h2 in memory database. 
	The artifacts are generated using maven. Generated jar is self executable jar and use embeded tomcat.
	
##How to run
	This is maven project, get project to local computer make sure you have maven setup.
	Run mvn clean install
	Go to target folder EmployeRestProject-1.0.0.jar must be created
	Run the jar as
		java -jar EmployeRestProject-1.0.0.jar
		
	Server must have started successfully with h2 schema and table loaded 
	Please see below details of API and h2 database.
	

##API Details

###Following API are exposed by EMPLOYEE Application

	GET EMPLOYEE BY AN ID
		API :
			getEmployee/{id}
		Type:
			GET
		Sample Request:
			http://localhost:8080/getEmployee/1126
		Sample Response:
			{
			    "id": 1126,
			    "firstName": "anand",
			    "lastName": "rathi",
			    "middleInitial": null,
			    "dateOfBirth": 471650400000,
			    "dateOfEmployment": 471650400000,
			    "status": "ACTIVE"
			}
	
	CREATE NEW EMPLOYEE
		API:
			createEmployee
		TYPE:
			POST
		Sample Request:
			URL: http://localhost:8080/createEmployee
			Payload:
				{
				    "id": 2,
				    "firstName": "Tushar",
				    "lastName": "Kapur",
				    "middleInitial": "Raj",
				    "dateOfBirth": "2012-04-24",
				    "dateOfEmployment": "2012-04-23"
				}
		Response:
			Success : 201 CREATED
			Failure : 400 BAD REQUEST
	
	UPDATE EMPLOYEE
		API:
			upadtaeEmployee/{id}
		TYPE:
			PUT
		Sample Request:
			URL: http://localhost:8080/updateEmployee/2
			Update status payload:
				{
					"status" : "ACTIVE"
				}
		Response:
			Success : 200 OK
			Failure : 404 NOT FOUND (In case of wrong ID)
	
	DELETE EMPLOYEE
		API:
			employee/{id}
		TYPE:
			DELETE
		SAMPLE REQUEST:
			http://localhost:8080/employee/2
			header:
				Authorization: manager
	   Response:
			Success : 200 OK
			Failure : 404 NOT FOUND (In case of wrong ID)
                    401 Forbidden(In case you are not manager)		
	GET ALL EMPLOYEE
		API:
			/employee/
		TYPE:
			GET
		SAMPLE REQUEST:
			http://localhost:8080/employee/
		SAMPLE RESPONSE:
			[
			    {
			        "id": 1126,
			        "firstName": "anand",
			        "lastName": "rathi",
			        "middleInitial": "jp",
			        "dateOfBirth": 471650400000,
			        "dateOfEmployment": 471650400000,
			        "status": "ACTIVE"
			    },
			    {
			        "id": 1128,
			        "firstName": "anand",
			        "lastName": "rathi",
			        "middleInitial": "jp",
			        "dateOfBirth": 1355263200000,
			        "dateOfEmployment": 1355263200000,
			        "status": "ACTIVE"
			    }
			]
			

##Start UP
	This application is using h2 in memory Database. 
	On startup Employee objected will get loaded from data.sql provided in the 	class path. 
	data.sql is present in resource directory.

## Database Integration 

	The project is integrated with H2 in memory database.
	Database can be access with the following url : http://localhost:8080/h2
	User name of database is sa and password is sa
	
	Employee Table Details:
	
		
	
	| Column         | DataType          | comments                    |
	| -------------  |:----------------: | ---------------------------:|
	| FIRSTNAME      | INT               | Integer                     |
	| LASTNAME       | VARCHAR(255)      |                             |
	| MIDDLEINITIAL  | VARCHAR(255)      |                             |
	| DOBIRTH        | DATE              |                             |
	| DOEMPLOYMENT   | DATE              | Date of Employment          |
	| STATUS         | VARCHAR(10)       | Valid value ACTIVE/INACTIVE |
	
	
###SQL to create table

	CREATE TABLE EMPLOYEE(ID INT PRIMARY KEY,
	   FIRSTNAME VARCHAR(255),
	   LASTNAME VARCHAR(255),
	   MIDDLEINITIAL VARCHAR(255),
	   DOBIRTH DATE,
	   DOEMPLOYMENT DATE,
	   STATUS VARCHAR(10));
	   
	   