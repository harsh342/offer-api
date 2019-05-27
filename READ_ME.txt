Assumptions

1. We have list of pre-registered merchants who can create offer for themselves. 
	I am using and ENUM to hold the list of merchants. Valid merchants are MERCHANT_1 to MERCHANT_9.
2. Application supports Offer Type as CASH_BACK, DISCOUNT, POINTS.
3. Application Supports Currency GBP, POINTS.
4. Percentage offer type can not have Offer Amount greater than 100 or less than 0.
5. For Offer type DISCOUNT currency should be GBP only.
6. In Create Offer API all the input fields are validated by JSON validator.

Application Access Details

-->Please use Swagger http://localhost/swagger-ui.html#/ for testing the APIs

-->In memory H2 Db can be accessed at 

http://localhost/h2/

username : sa
password: (blank)

localtion of DB : jdbc:h2:./db/offerdb

Sample Request:

{
  "merchantName": "MERCHANT_5",
  "offerCurrency": "GBP",
  "offerDescription": "Discount Offer from Merchant 5",
  "offerEndDate": "2019-06-30",
  "offerStartDate": "2019-05-30",
  "offerType": "DISCOUNT",
  "offerAmount":10.80
}