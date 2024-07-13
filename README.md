## Takshashila

### Spring Boot Run:

```sh
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"`
```

**DB Setup**

```
docker run --detach --name takshashila_db -p3306:3306 --env MARIADB_ROOT_PASSWORD=mariadb --env MARIADB_USER=takshashila --env MARIADB_PASSWORD=takshashila mariadb:latest
docker exec -it takshashila_db mariadb --user root -pmariadb
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 5
Server version: 11.4.2-MariaDB-ubu2404 mariadb.org binary distribution

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> create database ts_prod;
Query OK, 1 row affected (0.001 sec)

MariaDB [(none)]> GRANT ALL PRIVILEGES ON ts_prod.* TO takshashila;
Query OK, 0 rows affected (0.003 sec)
MariaDB [(none)]> quit
Bye
docker exec -it takshashila_db mariadb --user takshashila -ptakshashila
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 8
Server version: 11.4.2-MariaDB-ubu2404 mariadb.org binary distribution

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> use ts_prod;
Database changed
MariaDB [ts_prod]> show tables;
Empty set (0.001 sec)

MariaDB [ts_prod]>
```

The -d option means "without data".

`mysqldump -d -upccofvns -ppccofvns -hlocalhost takshashila`

### References:

* [Securing a Rest API with Spring Security and JWT](https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/#user-auth-token)
* [Deprecating WebSecurityConfigurerAdapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
* [JWT](https://github.com/jwtk/jjwt)
* [Spring Boot Security JWT](https://www.bezkoder.com/spring-boot-security-jwt)
