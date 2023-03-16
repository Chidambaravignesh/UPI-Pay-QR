# UPI-Pay-QR

Create payment link for UPI using below parameters suggested by NPCI.
Payee Address, Payee Name, Amount, Currency, transaction ID, Transaction reference, purpose, etc query parameters to be sent to UPI://pay link and returns the URI for the payment in UPI Application.
This URI to be base 64 encoded using barcode generator provided by google zing library and returns the barcode string with base 64 format. 
This barcode string to be used in HTML file or in app page to allow the user to scan from their UPI App and proceed payment.

Download/Clone this project
Build using Maven in spring tool suite or any other IDE
Run Spring boot app
The application will launch in local host with port number 8081
Open post man and create new POST request "http://localhost:8081/api/v1/QRCodeGenerate/getQRCodeBase64" with the below body payload
{
	"payeeAddress": "xxx@okaxis",
	"payeeName": "xxx-name",
	"trxNo": "123456",
	"amount": "1",
	"purpose": "Test",
	"trxRef": "Phonepe",
	"currency": "INR"
}

Response from the local server for the above POST request:
  - Base 64 encoded QR Code string for the UPI Intent deep lin URL
  
Add this Base 64 encoded string in the image src attribute in pay.html and open in any browser.
The page will display the QR Code as image.
Open any UPI App in your phone and try to scan the QR code to pay and you will be redirected to the payment confirmation in the app.
Once you confirm the payment, the amount will be transferred to the payee successfully.

Thank you.
