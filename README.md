# Welcome to My Api!

# Authentication

###POST /login
return basic data user and token type JWT
####Resource Information
| Question | Answer |
|--|--|
| Response formats |Json|
###Example Request

    {
    	"username":"admin",
    	"password":"12345"
    }

###Example Response

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
#API reference contents

     GET  /bills
     POST /bills
     PUT  /list
     DELETE /bills/{id}

#GET /bills
Returns the Bills.

### Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

###Parameters
not have
#POST /bills
Creates a new Bill
###Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

not have
###Example Request

    {
        "client":  "12345",
        "address":"11# 14-08",
        "amount":"75000"
    }

###Example Response

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

#PUT /bills
Set a Existing Bill
###Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

not have
###Example Request

    {
     	"id": "97512345",
    	"client": "12345",
    	"address": "11# 14-08",
    	"amount": 120000.0,
    	"date": "2021-04-02T23:25:27.3166369"
    }

###Example Response

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

#DELETE /bills
delete one existing bill
###Resource Information

| Question                 | Answer |
| ------------------------ | ------ |
| Response formats         | Json   |
| Requires authentication? | Yes    |

### Parameters

| Name | Required | Description        |
| ---- | -------- | ------------------ |
| id   | Required | The ID of the bill |

###Example Request

    https://tmpurl/api/2314125

###Example Response

    {
    	"Message": "bill delete Correct"
    }
