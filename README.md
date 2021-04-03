# Welcome to My API for Challenge BackEnd!

## Index

    - API documentation
    - Default Data

# API documentation

## URL

```
	localhost:8080/api
```

## Authentication

### POST /login

return basic data user and token type JWT

#### Resource Information

| Question         | Answer |
| ---------------- | ------ |
| Response formats | Json   |

### Example Request

```
  {
  	"username":"admin",
  	"password":"12345"
  }
```

### Example Response

```
    {
    	 "User": {
        		"password": null,
        		"username": "admin",
        		"authorities": [
            		{
                		"authority": "ROLE_ADMIN"
            		}
        		],
        		"accountNonExpired": true,
        		"accountNonLocked": true,
        		"credentialsNonExpired": true,
        		"enabled": true

        },
        "Message": "sign-in successful!",
        "Token":"eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn1dIiwic3ViIjoiYWRtaW4iLCJleHAiOjE2MTc0MjUzODh9.4YS_pBWbafPWmhlgjg0B1PBCKq2MPytZ3SDloh-ej-aIieRiEO-LukqzYoSPsQGgQGIBMmJSayOIzz7IAgD0GA"

    }
```

# API reference contents

     GET  /bills
     POST /bills
     PUT  /list
     DELETE /bills

# GET /bills

Returns the Bills.

### Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

not have

# POST /bills

Creates a new Bill

### Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

not have

### Example Request

```
  {
      "client":  "12345",
      "address":"11# 14-08",
      "amount":"75000"
  }
```

### Example Response

```
  {
  	"id":  "97512345",
  	"client":  "12345",
  	"address":  "11# 14-08",
  	"amount":  75000.0,
  	"priceDelivery":  5000.0,
  	"valueIVA":  14250.0,
  	"totalAmount":  94250.0,
  	"date":  "2021-04-02T19:25:27.3166369"
  }
```

# PUT /bills

Set a Existing Bill

### Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

not have

### Example Request

```
  {
   	"id": "97512345",
  	"client": "12345",
  	"address": "11# 14-08",
  	"amount": 120000.0,
  	"date": "2021-04-02T23:25:27.3166369"
  }
```

### Example Response

```
  {
  	 "id": "97512345",
  	 "client": "12345",
  	"address": "11# 14-08",
  	 "amount": 120000.0,
  	 "priceDelivery": 0.0,
  	 "valueIVA": 22800.0,
  	 "totalAmount": 142800.0,
  	 "date": "2021-04-02T23:25:27.3166369"
  }
```

# DELETE /bills

delete one existing bill

### Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

| Name | Required | Description        |
| ---- | -------- | ------------------ |
| id   | Required | The ID of the bill |

### Example Request

    localhost:8080/api/2314125

### Example Response

```
  {
  	"Message": "bill delete Correct"
  }
```

# Default Data

data was generated for ease of manual testing

## Return Data

```
[
    {
        "id": "23456",
        "client": "12345",
        "address": "Calle 1 # 3-3",
        "amount": 87555.0,
        "priceDelivery": 5000.0,
        "valueIVA": 16635.45,
        "totalAmount": 109190.45,
        "date": "2021-04-03T15:43:19.0989343"
    },
    {
        "id": "12345",
        "client": "23456",
        "address": "Calle 2 # 6-6",
        "amount": 104000.0,
        "priceDelivery": 0.0,
        "valueIVA": 19760.0,
        "totalAmount": 123760.0,
        "date": "2021-04-03T15:43:19.0989343"
    },
    {
        "id": "12346",
        "client": "23456",
        "address": "Calle 2 # 6-6",
        "amount": 93333.0,
        "priceDelivery": 5000.0,
        "valueIVA": 17733.27,
        "totalAmount": 116066.27,
        "date": "2021-04-03T11:23:19.0989343"
    },
    {
        "id": "34567",
        "client": "232345",
        "address": "Calle 7 # 12-12",
        "amount": 105333.0,
        "priceDelivery": 0.0,
        "valueIVA": 20013.27,
        "totalAmount": 125346.27,
        "date": "2021-04-03T10:23:19.0989343"
    },
    {
        "id": "45679",
        "client": "232345",
        "address": "Calle 7 # 12-12",
        "amount": 105333.0,
        "priceDelivery": 0.0,
        "valueIVA": 20013.27,
        "totalAmount": 125346.27,
        "date": "2021-04-03T08:23:19.0989343"
    },
    {
        "id": "45678",
        "client": "232345",
        "address": "Calle 7 # 12-12",
        "amount": 105333.0,
        "priceDelivery": 0.0,
        "valueIVA": 20013.27,
        "totalAmount": 125346.27,
        "date": "2021-04-03T03:23:19.0989343"
    }
]
```

## Case 2.2

use the following registrys to test CASES
change the "amount"

```
	{
        "id": "12346",
        "amount": 93333.0,
  },

```

```
	{
        "id": "34567",
        "amount": 105333.0
  },

```

## Case 3

use the following registrys to test CASES

Less That 12 Hours

```
	localhost:8080/api/45679
```

Less That 12 Hours

```
	localhost:8080/api/45678
```
