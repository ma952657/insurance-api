# Insurance App #

This app is to manage details of drivers for use within a hypothetical insurance service using the following operations.
* Create new Driver
* Get Details of drivers created after the specified date
* Get all drivers

**System requirements**
- JDK 8
- Maven
- Docker
- Mongodb

## Build and run application

The file [docker-compose.yml](docker-compose.yml) contains all the necessary settings to configure the environemnt.
Docker images, including  .jars and their dependencies can be built from the root directory using:

```bash
mvn clean install
```

To run the application locally:
```bash
docker-compose up
```

## Usage ##
* Below are the Endpoints for the following operations
1. Create a new Driver
   `http://localhost:8080/v1/driver/create`
   MethodType:`POST`.
An example request body is:
```json
{
  "firstName": "John",
  "lastName": "Smith",
  "date_of_birth": "1980-05-01" 
}

Response 

   ```json
{
    "id": "8",
    "createdOn": "2021-06-22",
    "firstName": "Test",
    "lastName": "Shahu",
    "date_of_birth": "1992-12-15"
}
,,,

2.Get Details of drivers by cretaed date
  `http://localhost:8080/v1/drivers/byDate?date=<date>`
Method:`GET`
Response:
```json
[
  {
    "createdOn": "2021-06-21",
    "date_of_birth": "1980-12-15",
    "firstName": "Test",
    "id": "2",
    "lastName": "Last"
  }
]
```
3.Get All Drivers
`http://localhost:8080/v1/drivers`
Method:`GET`
Response:
```json
{
    "content": [
        {
            "id": "1",
            "createdOn": "2021-06-21",
            "firstName": "Test",
            "lastName": "Last",
            "date_of_birth": "1986-12-14"
        },
        {
            "id": "2",
            "createdOn": "2021-06-21",
            "firstName": "TestName",
            "lastName": "LastName",
            "date_of_birth": "1986-12-15"
        }
        
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageSize": 10,
        "pageNumber": 0,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 8,
    "last": true,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 8,
    "size": 10,
    "number": 0,
    "empty": false
}
```

## API documentation 
This app uses Swagger 2. Access http://localhost:8080/swagger-ui.html to check the documentation.

## Improvements:

There are some improvements that should be made here:

- **Testing**: Include contract testing for the service & also to include more unit test cases .
- **Security**: Implement the security like OAuth2 in code.
- **Error handling**: Errors are logged and error messages returned to clients however, we can add more error sceanrios .
- And finally, we can always refactor and improve the code :)
