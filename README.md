[![Build Status](https://travis-ci.com/rafael-pieri/spring-jms-activemq.svg?branch=master)](https://travis-ci.com/rafael-pieri/spring-jms-activemq)

# Spring JMS with ActiveMQ

### Overview
Spring provides a JMS Integration framework that simplifies the use of the JMS API.
This application uses it to publish and consume messages from Apache ActiveMQ.

#### About Apache ActiveMQ
Apache ActiveMQ is the most popular and powerful open source messaging and Integration Patterns server.
Apache ActiveMQ is fast, supports many Cross Language Clients and Protocols, comes with easy 
to use Enterprise Integration Patterns and many advanced features while fully supporting JMS 1.1 and J2EE 1.4. 

### How to deploy the application

#### Embedded ActiveMQ
Execute the following command to deploy the application:

```gradle bootRun```

It will be available at: `http://localhost:8080`

#### ActiveMQ provided by Docker
Follow the command below to deploy the application:

```docker-compose up```

It will be available at: `http://localhost:8080`

### Swagger UI
```http://localhost:8080/swagger-ui.html```

### ActiveMQ Console (Visible only for docker deploy mode)
```http://localhost:8161/admin/queues.jsp```




