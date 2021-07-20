# Ohalo Technical Assignment
Ohalo's Assignment to list all the episodes of a show for a specific season

## Technology Stack Used

* Java 1.8 with Spring Boot and an embedded Tomcat
* Maven for dependency management
* H2 in memory db

## Approach

I designed db first and focus was on to meet the requirement with the simplest design, so I created a table for storing the user information, another table to store the information related to tvshow and 1 join table to store userid and the tvshows ids liked by the user.
For now, I just used the bare minimum attributes for each entity just to meet the current requirement but the design is extendable for future enhancements.

I skiped exception handling, validations and unit testing to save time.

## Building the Project

The project can be built with Maven 
``` java
mvn clean install
```

After building, an executable jar (target/assignment-0.0.1-SNAPSHOT.jar) would be available for running the server. The application would run on port 8080 by default. No other arguments need to be passed to the jar.

The endpoints will be available at 
``` 
localhost:8080/
```

Please use the following endpoints for the show resource

Create/Update:
URL:http://localhost:8080/show
HttpMethod: Post
Body:{  
		"title": "Got",  
		"season": 1,  
		"episode": 1
    }   
	
Get:
URL:http://localhost:8080/show/id
HttpMethod: Get

	
Please use the following endpoints for the user resource

Create/Update
URL:http://localhost:8080/user
HttpMethod: Post
Body:{  
		"id": "User1",  
		"name": "First"
     }   
	 
Get:
URL:http://localhost:8080/user/id
HttpMethod: Get 

## Home Page URL
http://localhost:8080/shows/home.html

Enter the name of your tvshow and enter the season in corresponding input boxes then click on the submit button, it will show you all the episodes of the show and also your favourites under fav if you have already bookmarked.
You can click on any show from the list and it will get added to your favourite list.
