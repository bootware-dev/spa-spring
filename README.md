# Single Page Application + Spring Security

Session Management Practices in SPA + Spring Security.

## Requires

 - Open JDK 11 Amazon Corretto
 - Docker Desktop
 - Yarn (or npm)

## Overview

 - Construction:
    - Frontend: 
        - Vue.js (axios, VueRouter, Vuex)
    - Backend:
        - Spring Boot (Spring Security, JPA, lombok)
- Authentication Method: JWT ( httpOnly )

## Setup

```sh
./mvnw clean
./mvnw -N install
./mvnw -N -Drun=init-container
./mvnw -f migration -P migrate-test
./mvnw -f entity -P reveng
./mvnw install -f entity
./mvnw install -f backend-common -Dmaven.test.skip
./mvnw install -f backend -Dmaven.test.skip
```

## Example

**Backend**

In the project root, type the following command on the console.

```sh
# Windows
mvnw -f backend spring-boot:run

# macOS
./mvnw -f backend spring-boot:run
```

**Frontend**

Open a separate console screen from the Backend.

```sh
# Windows
cd frontend-vue
yarn install
yarn serve
start http://localhost:8081

# macOS
cd frontend-vue
yarn install
yarn serve
open http://localhost:8081
```

### Login info

**Admin**
 - email: admin@bootware.jp
 - password: password
 
**User**
 - email: user@bootware.jp
 - password: password

### Database

 - host: localhost
 - db: postgres
 - username: postgres
 - password: postgres
 - schema: app
