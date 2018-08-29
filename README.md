### Tech stack
- Java 8
- Maven 3.5.2
- Springboot 2.x

### Running it
The following command will run the set of tests and start the app

`mvn clean && mvn package && java -jar target/fb-0.1.0.jar`

To play FizzBuzz, please hit this endpoint

`http://localhost:8080/?sequenceStart=1&sequenceEnd=20`


### Limitation
This solution does not support large number such as `Integer.MAX_VALUE` or `Integer.MIN_VALUE`

### Improvements
Configure spring test to pick up all available beans so that a few integration tests will break automatically when a new rule or report is added
