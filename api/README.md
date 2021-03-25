# android-gym-app

A REST API for managing:
* Gyms
* Gym offers
* Users
* User subscriptions

## Prerequisites

* JDK 8
* Maven
* PostgreSQL



## Installation
#### Clone the repository 
```bash 
$ git clone https://github.com/AleksandarStojanovikj/android-gym-app.git
```

#### Install dependencies
```bash
$ cd android-gym-app/api
$ mvn install
```

#### Update properties

Insert your Firebase private key in `FirebaseConfig`: 

```java
 FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/YOUR_KEY.json");
```

Set the Firebase url in the same class:

```java
FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("YOUR_URL")
                .build();
```

Update `application.properties` with the url to your data source. 


#### Start the application
```bash
$ mvn spring-boot:run
```

