# RevolutTask

## Run ways
1. `java -jar App.jar`
1. `mvn exec:java`

## Tests
* Integration tests -  `mvn test`
* Manual tests:
  1. Add funds `curl -X POST http://127.0.0.1:8080/service/wallet/add-amount --data "{\"user\":{\"service\": \"alpha\",\"id\": \"id2\"}, \"amount\": {\"value\": 15000.05,\"currency\": \"RUB\"}}" -H "Content-type: application/json"`
  1. Get balance `curl "http://127.0.0.1:8080/api/wallet/amount?service=alpha&id=id2"`
  1. Transfer funds `curl -X POST http://127.0.0.1:8080/api/wallet/transfer --data "{\"from\":{\"service\": \"alpha\",\"id\": \"id2\"}, \"to\":{\"service\":\"beta\", \"id\":\"id3\"}, \"amount\": {\"value\": 5000.15,\"currency\": \"RUB\"}}" -H "Content-type: application/json"`
  1. Check balance: `curl "http://127.0.0.1:8080/api/wallet/amount?service=alpha&id=id2"` and `curl "http://127.0.0.1:8080/api/wallet/amount?service=beta&id=id3"`

## Build & compile
1. Compile to single jar: `mvn clean compile assembly:single`

## General terms

1. Service privides to other systems API for creating data about their user accounts/wallets and transferring funds between them.
1. API contains two points - private /service (just for add funds and test requirments) and open /api (transfer funds)
1. Every user/account identified by two string fields - _service name_ and _user_id_ of this service
1. Multithreading didn't develop
1. Architecture constructed in 3 layers - Resource-layer / Service-layer / Datastore-layer

## API endpoints

Default running port: 8080

### Account wallet
`GET /api/wallet/amount?service=alpha&id=mail@mail.ru`
```json
[{
    "value": 10,
    "currency": "RUB"
}]
```


### Money transfer

`POST /api/wallet/transfer`
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

### Зачисление средств
`POST /service/wallet/add-amount`
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
