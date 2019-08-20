# RevolutTask

## Основные тезисы

1. Сервис позволяет создавать другим системам данные о собственных пользовательских счетах внутри сервиса и переводить между ними средства (например, для будущего процессинга и реального вывода)
1. В API есть две точки входа - сервисная (для добавления средств пользователей и тестовых нужд) и открытая (перевод средств с аккаунта на аккаунт)
1. Каждый аккаунт характеризуется двумя строками - код внешней системы и идентификатор пользователя в той системе

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
}```
