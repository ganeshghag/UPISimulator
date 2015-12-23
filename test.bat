rem testing account list
curl -i -X POST -H "Content-Type:application/json" -d @./WebContent/WEB-INF/messages/requests/ReqListAccount.xml http://localhost:9080/UPISimulator/UPIAccountList

rem ******************************
rem testing payment
curl -i -X POST -H "Content-Type:application/json" -d @./WebContent/WEB-INF/messages/requests/ReqPay.xml http://localhost:9080/UPISimulator/UPIPayment