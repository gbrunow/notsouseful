# NotSoUseful
A Not So Useful Crypto Web Service

# Compiling the Code
From a terminal access the root directory and run the following:

```bash
mvn clean install
```

# Running the Application
To run this project execute the following command from the root folder:

```bash
java -jar target/notsouseful-1.0-SNAPSHOT.jar server config.yml
```

You may now access the API at [http://localhost:8080/](http://localhost:8080/)

# Endpoints
This [Not So Useful Crypto Web Service](#NotSoUseful) has the following endpoints:

|Name|Method|URL|Input|Output|
|--|--|--|--|--|
| [PushAndRecalculate](https://github.com/gbrunow/notsouseful/blob/e3b50713a2ecaee524cac5132ade0bb8aae24331/src/main/java/com/notsouseful/resources/NotSoUsefulResource.java#L36) | POST | [nsu/push-and-recalculate](http://localhost:8080/nsu/push-and-recalculate) | A Single Number | JSON Object Containing the running average and standard deviation
| [PushRecalculateAndEncrypt](https://github.com/gbrunow/notsouseful/blob/e3b50713a2ecaee524cac5132ade0bb8aae24331/src/main/java/com/notsouseful/resources/NotSoUsefulResource.java#L50) | POST | [nsu/push-recalculate-and-encrypt](http://localhost:8080/nsu/push-recalculate-and-encrypt) | A Single Number | JSON Object Containing the **encrypted** running average and standard deviation
| [Decrypt](https://github.com/gbrunow/notsouseful/blob/e3b50713a2ecaee524cac5132ade0bb8aae24331/src/main/java/com/notsouseful/resources/NotSoUsefulResource.java#L58) | POST | [nsu/decrypt](http://localhost:8080/nsu/decrypt) | An encryted number provided by PushRecalculateAndEncrypt | Plain text containing the decrypted number
| Bonus: [GetStats](https://github.com/gbrunow/notsouseful/blob/e3b50713a2ecaee524cac5132ade0bb8aae24331/src/main/java/com/notsouseful/resources/NotSoUsefulResource.java#L29) | GET | [nsu/stats](http://localhost:8080/nsu/stats) | None | JSON Object Containing the current running average and standard deviation

# Running Unit Tests
To run the unit tests execute the following command fom the root folder:
```bash
mvn test
```
# Riddle me this
![Jokes Card](https://readme-jokes.vercel.app/api)
