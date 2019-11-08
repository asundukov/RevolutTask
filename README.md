# Task

## Description

Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.
### Explicit requirements:
1. You can use Java or Kotlin.
1. Keep it simple and to the point (e.g. no need to implement any authentication).
1. Assume the API is invoked by multiple systems and services on behalf of end users.
1. You can use frameworks/libraries if you like (except Spring), but don't forget about
requirement #2 and keep it simple and avoid heavy frameworks.
1. The datastore should run in-memory for the sake of this test.
1. The final result should be executable as a standalone program (should not require a
pre-installed container/server).
1. Demonstrate with tests that the API works as expected.
### Implicit requirements:
1. The code produced by you is expected to be of high quality.
1. There are no detailed requirements, use common sense.

## Solution

### Run ways
1. `java -jar App.jar`
1. `mvn exec:java`

### Tests
* Integration tests -  `mvn test`
* Manual tests:
  1. Add funds `curl -X POST http://127.0.0.1:8080/service/balance/add-amount --data "{\"user\":{\"service\": \"alpha\",\"id\": \"id2\"}, \"amount\": {\"value\": 15000.05,\"currency\": \"RUB\"}}" -H "Content-type: application/json"`
  1. Get balance `curl "http://127.0.0.1:8080/api/balance/amount?service=alpha&id=id2"`
  1. Transfer funds `curl -X POST http://127.0.0.1:8080/api/balance/transfer --data "{\"from\":{\"service\": \"alpha\",\"id\": \"id2\"}, \"to\":{\"service\":\"beta\", \"id\":\"id3\"}, \"amount\": {\"value\": 5000.15,\"currency\": \"RUB\"}}" -H "Content-type: application/json"`
  1. Check balance: `curl "http://127.0.0.1:8080/api/balance/amount?service=alpha&id=id2"` and `curl "http://127.0.0.1:8080/api/balance/amount?service=beta&id=id3"`

### Build & compile
1. Compile to single jar: `mvn clean compile assembly:single`

### General terms

1. Service privides to other systems API for creating data about their user accounts/wallets and transferring funds between them.
1. API contains two points - private /service (just for add funds and test requirments) and open /api (transfer funds)
1. Every user/account identified by two string fields - _service name_ and _user_id_ of this service
1. Multithreading didn't develop
1. Architecture constructed in 3 layers - Resource-layer / Service-layer / Datastore-layer

### API endpoints

Default running port: 8080

#### Account balance
`GET /api/balance/amount?service=alpha&id=mail@mail.ru`
```json
[{
    "value": 10,
    "currency": "RUB"
}]
```


#### Money transfer

`POST /api/balance/transfer`
```json
{
  "from": {
    "service": "alpha",
    "id": "mail@mail.ru"
  },
  "to": {
    "service": "beta",
    "id": "mail@mail.ru"
  },
  "amount": {
    "value": 10,
    "currency": "RUB"
  }
}
```

#### Зачисление средств
`POST /service/balance/add-amount`
```json
{
  "user": {
    "service": "alhpa",
    "id": "mail@mail.ru"
  },
  "amount": {
    "value": 10,
    "currency": "RUB"
  }
}
```
