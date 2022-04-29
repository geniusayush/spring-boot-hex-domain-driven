

.PHONY: clean
clean:
	./mvnw clean

.PHONY: build
build:
	./mvnw build $(SKIP_CHECKS)



.PHONY: prepare-docker
prepare-docker: clean build

.PHONY: docker
docker: prepare-docker
	docker build -t server:latest .

