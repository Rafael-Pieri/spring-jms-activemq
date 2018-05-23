# Spring JMS with ActiveMQ
The application uses Spring JMS template to publish and consume messages from ActiveMQ.

### How to deploy the application

#### Embedded ActiveMQ
Execute the following command to deploy the application:
```gradle bootRun```

#### ActiveMQ provided by Docker
Follow the command below to create the application image:
```docker build -t openjdk-8/springjms-activemq .```

Once the application image is created, execute the following command to deploy the application:
```docker-compose up```

### Swagger UI
```http://localhost:8080/swagger-ui.html```

### ActiveMQ Console (Visible only for docker deploy mode)
```http://localhost:8161/admin/queues.jsp```




