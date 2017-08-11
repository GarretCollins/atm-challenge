Author: 
Garret Collins

Code GIT Location:
https://github.com/GarretCollins/atm-challenge

Description:
Inorder to satisfy the requirements of this coding challenge I created a Restful Atm/Teller Service to perform various operations on an Account and also the ATM itself.

Spec:
Implemented using the SpringBoot Framework.
Restful Controllers were used, one for account operations and the other for atm operations.
A H2 in memory database was used to keep track of all transactions performed.
The Jacoco Plug-in is used to produce a sonar output.
Suitable Junit tests were used to provide a high level of code coverage (See SonarScanOutput.JPG)

Future TODO's:
In production Https should be used, with an OAuth or Token based security for all interaction using the spring security layer.
A logging framework should be added with all instances of the ATM's logs being pushed to a central location for operational support.
Addition of a feature to print all transactions performed on an account for a given period of time.

Testing:
Steps to Test the application once its running, also see "Teller Service Tests.postman_collection.json" if you want run the test via a postman collection:

In order to use the ATM funds must be deposited to it.
http://localhost:8080/atm/depositAtmFunds/8000

Check the balance of the first account 12345678.
http://localhost:8080/account/12345678/1234/checkBalance
Output: 500

Withdraw 100 from the account number 12345678.
http://localhost:8080/account/12345678/1234/withdraw/100/1
Output: 400

Withdraw 10 from the account number 87654321
http://localhost:8080/account/87654321/4321/withdraw/10/1
Output: 90

Withdraw 10 from the account number 56784321.
http://localhost:8080/account/56784321/1245/withdraw/10/1
Output: FUNDS_ERROR

check the balance for account number 56784321.
http://localhost:8080/account/56784321/1245/checkBalance
Output: .00


Addition Information if you want to view the sonar analysis:
During compilation of the application it will produce a sonar analysis.
This can be viewed by installing a freeware version of sonarqube-6.5.

In order to view the output you must have installed and started your sonar server:
C:\sonarqube-6.5\bin\windows-x86-64>StartSonar.bat

Next navigate to the location directly inside the teller folder and run:
mvn clean install
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login="THIS KEY WILL BE PROVIDED DURING INSTALLATION"

Next navigate to the this location on your browser:
http://localhost:9000
