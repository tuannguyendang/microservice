# microservice
Microservice Authorization server - Resource Server - Gateway

## Oauth2 server
Authentication server using asymmetric

- Import database
- Generate keypair

------------ Branch Master -----------------
 
 Used Jdbc token store. Token generated store in database.
 
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

## Event driven
- using Kafka or Rest : not done yet