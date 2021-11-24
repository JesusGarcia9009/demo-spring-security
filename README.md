# SpringBootTest
Spring project BCI test

# Spring boot test

Microservicio orientado al registro de usuarios y registros telefónicos así como también a la información de login de dichos usuarios

El siguiente proyecto tiene la siguiente estructura de ejecucion

![alt text](https://github.com/JesusGarcia9009/demo-spring-security/blob/main/diagrama.png)

Los scripts de para la creacion de la base de datos esta en el archivo

```
https://github.com/JesusGarcia9009/spring-boot-test/blob/main/DDL.sql
```
Las configuraciones del sistema están en el archivo el cual se puede eliminar la propiedad de creacion del modelo:
> **bootstrap.yml**
```
jpa:
    database-platform: org.hibernate.dialect.H2Dialect 
    hibernate:
      ddl-auto: update  #esta propiedad se podria cambiar por none y ejecutar el script de base de datos
    properties:
      hibernate: 
        show_sql: false
        use_sql_comments: true  
        format_sql: true
```


## Comenzando 🚀

Descargar Fuentes de git

```
git clone https://github.com/JesusGarcia9009/spring-boot-test.git
git checkout main
```
Una vez descargada las fuentes se debe ejecutar el siguiente comando en consola:

```
gradlew build
```



La ejecución de la consola para acceder a la  base de datos es a través del siguiente link:

```
https://localhost:8080/h2-console
user: admin
pass: admin
```

La ejecución del servicio de registro es a través de la siguiente url:

```
http://localhost:8080/api/v1/springboot/register
body:
{
    "name":"Jesus Garcia",
    "email":"jesus@gmail.com",
    "password":"Ja11",
    "phones":[
        {
            "number":"1234567",
            "citycode":"1",
            "contrycode":"57"
        }
    ]
}
```
quedando de la siguiente forma

![alt text](https://github.com/JesusGarcia9009/demo-spring-security/blob/main/evid.PNG)

## Pre-requisitos 🛠

- Maquina Virtual de Java
- Gradle
- Variables de entorno
- IDE
- Lombok


## Construido con 🛠


Herramientas y lenguajes utilizados


* [Java](https://www.java.com/) - Lenguaje de programacion.
* [Gradle](https://gradle.org/) - Manejador de dependencias.
* [Eclipse](https://www.eclipse.org/) - IDE de desarrollo.
* [Lombok](https://projectlombok.org/) - Creacion de metodos basicos de objetos.

## Autores.

* **Jesús García** - *Trabajo Inicial-Programación-Dcumentación* 

