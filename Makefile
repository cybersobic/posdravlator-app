# Быстрый запуск приложения ("Напишите в консоль --> make run")
run:
	mvn clean package
	sudo docker-compose up --build -d