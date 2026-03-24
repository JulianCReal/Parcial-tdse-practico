**Numeros Catalan Parcial**

El cotenido de este repositorio se basa en una arquitectura Proxy y service, la cual el proxy se encargara de recibir las solicitudes para que el microsericio que computa las operaciones 
del numero de catalan reciba solo la accion de calculo y asi pueda generar el output de estas mismas.
Funcionamiento:

---
en local podemos ver que el proxy funciona correctamente:

y vemos como se muestra el calculo de los numeros de catalan:

---
1. **como ejecutar localmente**
para poder correr esto de forma local tenemos que entrar al directorio de cada uno de los proyectos y ejecutar ´´mvn clean install´´ para que se 
instalen todos los paquetes del pom correctamente y luego para correr los projectos usaremos mvn ´´spring-boot:run´´
y con esto tenndremos los proyectos corriendo, para despues abrir ´´localhost:8087´´ que es el perteneciente a el proxy-service y de ahi hacer los calculos correspondientes
---
