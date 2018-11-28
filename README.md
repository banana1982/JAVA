
# Demo JAVA

# Issue
Demo a CRUD RestFul API :
  - Using Jersey 2.x and jetty 9.x
  - Storing data when API starting in memory (non DB) 
  - Result data's response including following these fields: content, created_date, created_by, lastupdated_date, lastupdated_by
  - There's searching function (mode "like") using for fields content, created_date, created_by

# Usages
  - Step 1: Start project
  - Step 2: Open browser/postman type "localhost:8080/api/topics"
 
# Detail
Host: localhost:8080/api

    - GET - /topics 
    - GET - /topics/{id}
    - POST - /topics/create
    Body 
    {content :"SWIFT", create_by:"cuonglt2"}
    - PUT - /topics/update
    - DELETE - /topics/delete
    - GET - /topics/search?content=xxx&create_by=cuonglt2&create_date=timestamp
    
