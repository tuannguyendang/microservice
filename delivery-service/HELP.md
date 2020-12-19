# Delivery Service
Delivery service: receive request delivery send to third party like Lalamove, Delivery roo, Grab...Tracking and update status delivery to customers
### Tech specification
Spring boot
### Deployment
### Health API
http://localhost:8766/delivery-service/actuator/health

### Tracing
Open Zipkin: Running latest image docker run -d -p 9411:9411 openzipkin/zipkin. Data store in-memory

more version for zipkin: https://hub.docker.com/u/openzipkin

Admin UI: http://localhost:9411/zipkin/?serviceName=delivery-service&lookback=30m&endTs=1608301463449&limit=10