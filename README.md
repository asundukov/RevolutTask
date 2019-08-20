# RevolutTask

## Run ways
1. `java -jar App.jar`
1. mvn exec:java

## Tests
* Integration tests -  `mvn test`
* Manual tests:
  1. Add funds `curl -X POST http://127.0.0.1:8080/service/wallet/add-amount --data "{\"user\":{\"service\": \"alpha\",\"id\": \"id2\"}, \"amount\": {\"value\": 15000.05,\"currency\": \"RUB\"}}" -H "Content-type: application/json"`
  1. Get balance `curl "http://127.0.0.1:8080/api/wallet/amount?service=alpha&id=id2"`
  1. Transfer funds `curl -X POST http://127.0.0.1:8080/api/wallet/transfer --data "{\"from\":{\"service\": \"alpha\",\"id\": \"id2\"}, \"to\":{\"service\":\"beta\", \"id\":\"id3\"}, \"amount\": {\"value\": 5000.15,\"currency\": \"RUB\"}}" -H "Content-type: application/json"`
  1. Check balance: `curl "http://127.0.0.1:8080/api/wallet/amount?service=alpha&id=id2"` and `curl "http://127.0.0.1:8080/api/wallet/amount?service=beta&id=id3"`

## Build & compile
1. Комипляция в single jar: `mvn clean compile assembly:single`

## Основные тезисы

1. Сервис позволяет создавать другим системам данные о собственных пользовательских счетах внутри сервиса и переводить между ними средства (например, для будущего процессинга и реального вывода)
1. В API есть две точки входа - сервисная (для добавления средств пользователей и тестовых нужд) и открытая (перевод средств с аккаунта на аккаунт)
1. Каждый аккаунт характеризуется двумя строками - код внешней системы и идентификатор пользователя в той системе
1. Многопоточность не обработана
1. Архитектура в три слоя - Web-layer / Service-layer / Datastore-layer

## API endpoints

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
