docker kill my_order_test || true;
sleep 3
docker run --rm --name my_order_test -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=order_db -e MYSQL_USER=order -e MYSQL_PASSWORD=123456 mysql:8.0.21
sleep 10
echo "Done setting order database"