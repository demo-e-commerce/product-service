## Order Service

## Set up guide
    1. Install docker, docker compose
    2. Run development kafka:
        Open terminal, cd to resources/kafka, run docker-compose up -d
    3. Install maven
    4. Run the project:
        mvn spring-boot:run

## Architechture
[Overview flow chart](https://app.diagrams.net/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=gpx_track_online#RzZfbctowEIafxpfp%2BAxcEkia6aRTWqZTkpuObG9sNbLlkcWpT18Jy0a2QkNTmMAN3l8Hr779JYHlTfLNR4bK7DNNgFiunWwsb2q5rmuHofiSyrZWHMcb1krKcKK0vTDHv0GJtlKXOIGq05FTSjguu2JMiwJi3tEQY3Td7fZESfetJUrBEOYxIqb6Ayc8a5Zh2%2FuGO8Bppl49DFRDhOLnlNFlod5nud7T7lM356iZS%2FWvMpTQtSZ5N5Y3YZTy%2BinfTIBIuA22etztgdY2bwYFP2bAYPrr8Q6538af4p8j%2F2o9%2Ffr8cOXWs6wQWSoe3ytgKmG%2BbSCJ3Ev5uMzJOOaUWd71ChjHAuM9ioDMaIU5poXoElHOaa51GBOcygZOS6FmPCcicMQjXXKCC5i0hbWFqPIRY2FzcKFOi0%2F4EmgOnG1FFzWgKVDjSE%2FF6315GynTChsqDSlDpe3Ee6jiQXH9B8a%2BwfiW0YKDME6fs0YnQVUGiaKyzjCHqkSx7LcWO%2FE0pFpvNqh8E1W7EXRWwblYBQarKeIoQhUc9GS8FS5KgDWY5l1MGtJIblZI7qNWaLfwl9qLSj8B2oHXQ%2BsGBtrwBbLDc5ENDbLXYvUXYEJ%2FdGkmdByDlQFJgBvL20cakKCqwrGgUXHEuClrQGGD%2BULy%2FBCo6KETTTcK9i7YasEMGBbLkz7XDkpIjNutx11kTZcshtcuAbM%2BGv%2FgL%2FgZEMTxqpvGSzVRb5hRLBJsy9%2BWtin%2FqFfWOn01Sr%2FfehMZh1n%2FQBfFSYEbE%2B0s0i77P1xj3qUnc00hUls0ZpCB5hoZ7m2zi3TfLDTnHeO1E%2FpKXXw199eOpvfynxd2bePab%2FSf7%2FT81z%2Bfzu0%2F7%2BL8d0IvhUd6KXhPLwWDnpfeepaFvbPM9U%2FlJUv%2B%2BGz%2BctTd93%2FsvJs%2F)

- Audit events are send to Kafka and will be handle by other service
- Order service hold all data related to order include order items, only rely on product service to check product quantity when create/update new order
- Authentication is out of scope, order service just need a JWT token with valid signature and contain userId in payload

## Key Libraries
- Spring Boot, Sprint Security, Spring Data, Spring Kafka
- Maven
- java-jwt, java-rsa

## Folder structure

- Follow standard maven structure, details:
    - config: contains configurations class
    - controller: contains web controller
    - dto: contains dto class
    - model: contains data model
    - repository: contains JPA repository
    - security: contains security related class
    - service: contains services

## APIs
- Create order:
    curl --location --request POST 'http://localhost:6060/api/v1/order' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidXNlcklkIjoiZXJ3cjM0NTM0ZmdlZnJld3IiLCJpYXQiOjE1MTYyMzkwMjJ9.yy6RNHz1oL24eJlYCeU7Z3fzAIa_vrHLi9zLpj3clLE' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "orderItems": [
            {
                "code": "prod-100",
                "quantity": 2
            },
            {
                "code": "prod-100",
                "quantity": 2
            }
        ]
    }'
- Get order list
    curl --location --request GET 'http://localhost:6060/api/v1/order?page=0&size=2&sort=desc&sortBy=createdDate' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidXNlcklkIjoiZXJ3cjM0NTM0ZmdlZnJld3IiLCJpYXQiOjE1MTYyMzkwMjJ9.yy6RNHz1oL24eJlYCeU7Z3fzAIa_vrHLi9zLpj3clLE' \
    --data-raw ''