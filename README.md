
# Credit Payment Plan Generator

It s a Spring Boot Application. 
After compiling project u can start project with CreditApplication class. Right click and Run as Java Application.

I used maven with Eclipse.
I also used Spring Test framework.
Implementation:
	1-) I created PaymentPlanRequest class for request info.
	2-) I created PaymentPlanResponse class for response
	3-) I created service interface for controller to communicate with Service Implementation to 
generate plan.
	4.) CreditServiceImpl class has method as generatePlan. Logic is here. I calculated monthly payment amount first.
then calculated all months properties according to duration. 
	5.) I used BigDecimal API of java to calculate complex functions.
	6.) I tested code with edge cases in Test class.
	
API Documentation:http://localhost:8080/swagger-ui.html#/credit-controller
