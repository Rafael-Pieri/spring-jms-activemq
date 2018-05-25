# Spring JMS with ActiveMQ

### Overview
Spring provides a JMS Integration framework that simplifies the use of the JMS API.
This application uses it to publish and consume messages from Apache ActiveMQ.

#### About Apache ActiveMQ
Apache ActiveMQ is the most popular and powerful open source messaging and Integration Patterns server.
Apache ActiveMQ is fast, supports many Cross Language Clients and Protocols, comes with easy to use Enterprise Integration Patterns and many advanced features while fully supporting JMS 1.1 and J2EE 1.4. Apache ActiveMQ is released under the Apache 2.0 License
Grab yourself a Download, try our Getting Started Guide, surf our FAQ or start Contributing and join us on our Discussion Forums.

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




