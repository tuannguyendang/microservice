# microservice
Microservice Authorization server - Resource Server - Gateway

## Oauth2 server
Authentication server using asymmetric

- Import database
- Generate keypair

------------ Branch Master -----------------
 
 Used Jwt token store. Token generated store in memory.
 
------------ Branch oauth2-server-jdbc -----------------
 
 Used Jwt token store. Token generated store in database.
 
------------ Branch oauth2-server-jwt ------
 
 Used Jwt token store. Token generated store in memory.

## Resource server
- Resource Service: Skeleton code for Resource Server
- Order Service, Delivery Service,... is resource server

## Registry
- Eureka server

## Gateway server
- Gateway discovery service

## Config
- Spring cloud config for properties

## Common

- Common for Role Base Access Control
- Common Util: Util method
- Common Dto: DTO class
- Common Entity: Entity mapping from database
 
## Database
- MySQL
- Database per service
- Transaction rollback following Sagas pattern

### kafka
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties

## Event driven
- using Kafka or Rest : done

Delivery Service:
Delivery changed status delivered send an event to Order Service:
http://localhost:8766/delivery-service/v1/delivery/delivered

Order Service:
delete order send an event to Delivery Service for cancel delivery
http://localhost:8765/order-service/v1/order/6

Current code not fully adapt ACID in case update order or delivery status failure but event sent successful. To avoid this need apply OUTBOX pattern: event will save to database in table OUTBOX, use polling job (scheduler in spring boot) or debezium... send event to Kafka

### Running
Step 1: Start config service

Step 2: Start Registry service

Step 3: Start Gateway service

Step 4: Start other services

### Network (Service Mesh)

All request go through gateway:

    A. External:
    
    requests -> gateway -> services

    B. Internal: 
    
    Requests -> Gateway -> Services
    
    Requests -> Services -> Services

