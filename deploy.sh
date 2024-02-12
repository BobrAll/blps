chmod 777 mvnw
./mvnw clean install package
docker build . -t "backend"
docker compose up 
