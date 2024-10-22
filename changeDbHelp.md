



# Change From Hikari db to Mysql db
1) Download docker and install into your local pc. Ensure you have sign in to the docker account.
Url: https://docs.docker.com/desktop/install/windows-install/

2) At Docker terminal, you will see the terminal is already connected to your local pc, then type command below
to install mysql
Command: docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle

3) check the mysql is created by command below, you will see your "mysql" was ready
Command:  docker container ps

4) Login to the mysql and modify the root password again
command 1:  
  docker exec -it mysql mysql -u root -p
command 2 (After login to mysql):
  ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword'; 
command 3:
   Exit;

5) After done setup, Open file "Application.properties",
Comment on H2 database settings and open mysql database

# H2 database settings
#spring.h2.console.enabled=true
#spring.datasource.url=jdbc:h2:mem:testdb
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.username=sa
#spring.datasource.password=password
#spring.datasource.platform=h2
#spring.jpa.defer-datasource-initialization=true

spring.datasource.url=jdbc:mysql://localhost:3307/todos?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=todos-user
spring.datasource.password=dummypassword
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

6) Start server
