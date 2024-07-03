# Actividad: TowerDefense V3

Creamos un Dockerfile para cada microservicio:

![](img/dockerfile.png)

Corremos todos los Dockerfiles para obtener las imagenes:

![](img/images.png)

Estos son todos los contenedores generados:

![](img/docker_containers.png)

### Configuración de Pitest en Gradle
Para configurar Pitest en un proyecto Gradle, necesitas agregar las dependencias y el plugin de Pitest a
tu archivo build.gradle.

Pasos:

1. Agregar el plugin de Pitest

![](img/add_pitest.png)

2. Configurar las dependencias

![](img/dependencies.png)

3. Configurar el plugin de Pitest:

![](img/config_pitest.png)

### Paso a paso para completar el proyecto

1. Preparación del entorno:

- Instalar Docker, Docker Compose y Minikube (o un clúster Kubernetes equivalente).

Se instalo en anteriores actividades

2. Desarrollar los microservicios:

- Crear los archivos Java para cada microservicio (GameService, MapService, PlayerService, TowerService, WaveService).

![](img/gameservice_code.png)

Asi creamos los archivos en GameService, MapService, PlayerService, TowerService y WaveService.

3. Configurar Docker:

- Crear un Dockerfile para cada microservicio.

![](img/dockerfile.png)

Asi creamos los Dockerfile's para Game, Map, Player, Tower y Wave.

- Construir las imágenes Docker usando el comando docker build.

![](img/build_image.png)

Asi creamos los imagenes para Game, Map, Player, Tower y Wave.

4. Configurar Docker Compose:

- Crear un archivo docker-compose.yml para definir cómo los contenedores interactuarán entre sí.

![](img/compose_code.png)

- Levantar los servicios definidos en Docker Compose usando docker-compose up.

![](img/compose.png)

5. Desplegar en Kubernetes:

- Crear archivos de despliegue (deployment.yaml) y servicio (service.yaml) para cada
microservicio.

![](img/kube_deploy_service.png)

Asi hacemos para Game, Map, Player, Tower y Wave.

- Aplicar las configuraciones de Kubernetes usando kubectl apply.

6. Verificar despliegue:

- Usar comandos kubectl para verificar que los pods y servicios estén funcionando
correctamente (kubectl get pods, kubectl get services).

![](img/kubectl_commands.png)

7. Implementar pruebas:

- Escribir pruebas unitarias e integración usando Mockito.

![](img/testing.png)

- Configurar y ejecutar pruebas de mutación para asegurar la robustez del código

![](img/pitest_deploy.png)

