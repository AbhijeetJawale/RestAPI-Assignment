# RestAPI-Assignment
The aim of the assignment is to develop a service which will perform CRUD operations on the table using spring boot. The database used is MS Access. 
The outputs(screenshots) are in the output folder. 

Put "Bearer authToken" in Authorization in the header. 
## Developed APIs:

  
### * POST : To add the new broker. 
  * URL: /broker/save
  * Response codes:
    * 201 Created: When record get created successfully
    * 503 Service Unavailable: When Record is not created & service is unavailable
    * 401 Unauthorized: when auth token validation failed

### * GET: Get brokerDetail for given broker id
  * URL:/broker/{brokerId}
  * Response codes:
    * 200 OK: When you get the list of the matching record.
    * 201 NO CONTENT: If not matcching records found
    * 401 Unauthorized: when auth token validation failed

### * GET: Get all brokers record
  * URL:/allbrokers
  * Response codes:
    * 200 OK:All broker records
    * 201 NO CONTENT:If not matching records found
    * 401 Unauthorized:when auth token validation failed

### * Put:Update brokerDetails For Given brokerId
  * URL:/broker/{brokerId}
   * Response codes:
     * 200 OK: Updated Record
     * 503 Service Unavailable:When Record is not updated & service is unavailable
     * 401 Unauthorized:when auth token validation failed

### * Delete:Delete broker record for given brokerId
  * URL:/broker/delete/{brokerId}
    * 200 OK: List of records matching brokerId
    * 503 Service Unavailable: When Record is not deleted & service is unavailable
    * 401 Unauthorized: when auth token validation failed
   
