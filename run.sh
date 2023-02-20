docker build -t knote-java .
docker network create knote
docker run \
  --name=knote-java \
  --rm \
  --network=knote \
  -p 8080:8080 \
  -e MONGO_URL=mongodb://mongo:27017/dev \
  knote-java

docker tag knote-java <username>/knote-java:1.0.0
docker push username/knote-java:1.0.0

kubectl apply -f kube
minikube service knote --url