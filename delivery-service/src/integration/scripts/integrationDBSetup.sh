docker kill pg_delivery_test || true

sleep 2
docker run --rm --name pg_delivery_test -d -p 5433:5432 -e POSTGRES_USER=delivery -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=pg_delivery_db postgres:13.2-alpine
sleep 10
echo "Done setting delivery database"