
# OnePay
An API to read/write transactions


## Webservices

The webservices require and answer in JSON.

GET /transactions/all :
get all the transactions registered

GET /transactions/id/{id} :
get a transaction with a specific id

POST /transactions/ :
Create a transaction.

PUT /transactions/ :
Update a transaction.
## JSON sample
{
   "id": "113008c8-111a-11ee-be56-0242ac120002",
   "sum": 3,
   "status": "NEW",
   "paymentMode": "CREDIT_CARD",
   "orders": [   {
   	 "id" : "213008c8-111a-11ee-be56-0242ac120002",
      "name": "hat",
      "amount": 3,
      "price": 5
   }]
}