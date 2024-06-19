# Actividad Configuracion Docker

## Prueba de Docker Engine

En este caso mi computadora ya lo tenia instalado el Docker Desktop y Docker en general.

Para que se pueda ejecutar estos comandos sin error

```
docker version
docker container run hello-world
docker container run rancher/cowsay Hello
```

Se tiene que inicializar el Docker Desktop.

![](docker_version.png)

Hay que crearse un grupo para no tener la necesidad de poner sudo a todos los comandos:

![](group.png)


Puse el comando *newgrp docker* para activar los cambios en los grupos: 

![](cow.png)

## Kubernetes

1. Abre el panel de Docker Desktop.
![](img/desktop.png)

2. En la esquina superior izquierda, selecciona el ícono de la rueda dentada. Esto abrirá la página de configuración (setting).
![](img/diagram.png)

3. En el lado izquierdo, selecciona la pestaña Kubernetes y luego marca la casilla Enable Kubernetes
![](img/diagrama.png)

4. Haz clic en el botón Apply & restart
![](img/diagrama.png)


## Probando minikube y kubectl

Instalamos kubectl:

![](img/kuber.png)

Instalamos minikube e iniciamos el cluster por defecto:

![](img/minikube.png)


![](img/kube_config1.png)

![](img/ginx1.png)

![](img/deploy.png)

![](img/deploy_web.png)


Con base en lo que se cubrió en esta actividad, responde las siguientes preguntas:
1. En tus propias palabras, usando analogías, explica qué es un contenedor.


2. ¿Por qué se considera que los contenedores cambian las reglas del juego en IT? Menciona tres o cuatro razones.



3. ¿Qué significa cuando afirmamos que, si un contenedor se ejecuta en una plataforma
determinada, entonces se ejecutará en cualquier lugar? Menciona dos o tres razones por las que
esto es cierto.


4. ¿Es verdadera o falsa la siguiente afirmación: los contenedores Docker solo son útiles para
aplicaciones modernas y totalmente nuevas basadas en microservicios? Por favor justifica tu
respuesta.


5. ¿Por qué nos importaría instalar y usar un administrador de paquetes en nuestra computadora
local?


6. ¿Con Docker Desktop, puede desarrollar y ejecutar contenedores de Linux?


7. ¿Por qué son esenciales buenas habilidades de programación (como Bash o PowerShell) para el
uso productivo de los contenedores?


8. Nombra tres o cuatro distribuciones de Linux en las que Docker esté certificado para ejecutarse.


9. Instalaste minikube en tu sistema. ¿Para qué tipo de tareas utilizarás esta herramienta?


