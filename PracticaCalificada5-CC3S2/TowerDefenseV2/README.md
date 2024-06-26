# Practica Calificada 5


### Configuración de docker


## Ejercicio 1: Configuración y uso de docker (3 puntos)

#### Teoría:
- Describe los principios fundamentales de los contenedores Docker y su arquitectura interna.
Explica cómo Docker maneja la seguridad y el aislamiento de contenedores.

**Rpta:**
Docker utiliza contenedores ligeros basados en la virtualización a nivel de sistema operativo, esto para garantizar portabilidad. 

- Compara y contrasta Docker con soluciones de virtualización tradicionales, como VMware y
VirtualBox. Discute las ventajas y desventajas de cada enfoque.

**Rpta:**
Docker es online y permite que el software que corre en la computadora de una persona corra en cuando abrimos su contenedor.
En VirtualBox se tiene que configurar todo desde cero, sin embargo en Docker ya todo esta realizado.

Se debe ejecutar este comando, sin embargo...

`docker exec -it tower-defense-container /bin/bash`

Si lo ejecutamos nos sale esto:

![](img/error.png)

Esto es debido a que aun no se ha creado el contenedor *tower-defense-container*, asi que primero vamos a crearlo.

### Práctico:
- Escribe un Dockerfile para la aplicación Tower Defense que incluya la instalación de todas las
dependencias necesarias. Asegúrate de optimizar el Dockerfile para reducir el tamaño de la
imagen final.

#### Respuesta:

Primero creamos el Dockerfile del contenedor para que previamente se cree la imagen:

```
// Dockerfile
FROM openjdk:17-oraclelinux7
WORKDIR /usr/src/myapp
COPY . /usr/src/myapp
RUN javac src/main/java/org/example/*.java
EXPOSE 8080
CMD ["sh", "-c", "java -cp src/main/java org.example.TowerDefenseGame && exec bash"]
```

Construcción de la imagen Docker:

`docker build -t tower-defense-game .`

![](img/build_image.png)

- Construye y ejecuta el contenedor Docker utilizando el Dockerfile creado. Utiliza docker exec
para acceder al contenedor y verificar que la aplicación funcione correctamente.

#### Respuesta

Hacemos la ejecución del contenedor para crearlo:

`docker run -it --name tower-defense-container tower-defense-game`

![](img/exec.png)

- Configura una red personalizada para la aplicación Tower Defense. Implementa múltiples
contenedores que interactúen entre sí a través de esta red personalizada.

#### Respuesta:

Primero se configura la red, en este caso ya la tenia configurada por la actividad V1:

`docker network create game-network`

Ahora implementamos multiples contenedores que sean parte de esta red:

docker run -it --name tower-defense-container-on-network1 --network game-network tower-defense-game

docker run -it --name tower-defense-container-on-network2 --network game-network tower-defense-game

docker run -it --name tower-defense-container-on-network3 --network game-network tower-defense-game

Aca se ve:

![](img/networks.png)


## Ejercicio 2: Redes y volúmenes en Docker (3 puntos)

#### Teoría:
- Explica en detalle cómo Docker maneja las redes y los volúmenes. Discute los diferentes
tipos de redes (bridge, host, overlay) y cuándo es apropiado usar cada una.
- Describe los mecanismos de persistencia de datos en Docker, incluyendo volúmenes y bind
mounts. Explica las diferencias entre ellos y las mejores prácticas para su uso.

#### Práctico:
- Crea una red personalizada para el proyecto Tower Defense y configura los contenedores
para que utilicen esta red.

#### Respuesta:
```
docker network create new-game-network
docker run -it --name tower-defense-container-on-network-pc5 --network new-game-network tower-defense-game
```

![](img/new_network.png)

- Implementa un volumen Docker para almacenar los datos del juego de forma persistente.
Asegúrate de que el volumen se monte correctamente y que los datos persistan después de
reiniciar el contenedor.

#### Respuesta:

En este caso ponemos este comando para implementar un volumen, hay que decir que ya estaba creado por la actividad V1, asi que se mostrara la prueba:

`docker volume create game-data`

![](img/volumes.png)

Se crea un contenedor tower-defense-container-with-vol y se conecta a la nueva red game-network.
Luego se monta el volumen game-data en este contenedor en la ubicacion /data usando la imagen tower-defense-game previamente creada.

```
docker run -d -it --name tower-defense-container-with-vol --network new-game-network -v game-data:/app/data tower-defense-game
```

**OJO**: tienes que poner -d para que el contenedor se corra en segundo plano, ya que `docker exec` requiere que el contenedor a ejecutar este en funcionamento y corriendo.

Acceder al contenedor en ejecución

`docker exec -it tower-defense-container /bin/bash`

Al ejecutar `ls /app` se ve que bota la ubicacion del volumen:

![](img/volume_code.png)

- Utiliza docker-compose para definir los servicios de la aplicación Tower Defense, incluyendo
redes y volúmenes. Escribe un archivo docker-compose.yml que configure estos servicios y
despliega la aplicación utilizando Docker Compose.

#### Respuesta:

Se crea un archivo docker-compose.yml:

```
## El espaciado de sangria siempre es 2
version: '3'
services:
  game:
    image: tower-defense-game
    networks:
      - game-network
    volumes:
      - game-data:/app/data

networks:
  game-network:
    driver: bridge

volumes:
  game-data:
    driver: local
```

Iniciar los servicios
docker-compose up -d

Todo corre bien:
![](img/compose.png)

## Ejercicio 3: Orquestación con Kubernetes (4 puntos)

#### Teoría:

- Describe la arquitectura de Kubernetes y sus componentes principales, incluyendo el API
server, etcd, scheduler, y kubelet. Explica cómo estos componentes interactúan para
gestionar un clúster de Kubernetes.
- Discute las estrategias de escalabilidad y alta disponibilidad en Kubernetes. Explica cómo
Kubernetes maneja la recuperación de fallos y la gestión de réplicas.

#### Práctico:

- Escribe un archivo deployment.yaml para la aplicación Tower Defense. Asegúrate de definir
los recursos necesarios (CPU, memoria) y las políticas de escalabilidad.

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
        - name: tower-defense-game
          image: tower-defense-game
          ports:
            - containerPort: 8080

```

- Implementa un Service en Kubernetes para exponer la aplicación Tower Defense a través de
una IP pública. Utiliza un LoadBalancer para distribuir el tráfico entre múltiples réplicas de la
aplicación.

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
        - name: tower-defense-game
          image: tower-defense-game
          ports:
            - containerPort: 8080

```

- Despliega la aplicación Tower Defense en un clúster de Kubernetes. Utiliza kubectl para
gestionar el despliegue y verificar que la aplicación funcione correctamente en el clúster.

Aplicar los archivos de configuración en Kubernetes
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
Verificar el estado del despliegue
kubectl get pods
kubectl get services


## Ejercicio 4: Pruebas unitarias y de integración con Mockito (4 puntos)

#### Teoría:
- Explica los conceptos de mocks, stubs y fakes. Discute cuándo y cómo se deben utilizar estos
patrones en las pruebas unitarias.

**Respuesta:**

Mocks: son objetos simulados que verifican interacciones específicas con otros objetos
Stubs: garantizan respuestas cuando se invocan al metodo del mock
Fakes: son implementaciones simples y funcionales de interfaces o clases que simulan comportamientos complejo

Estos patrones de pruebas unitarias se deben usar en pruebas donde la respuesta del testing sea aleatoria o dependa de librerias externas, ejemplo una llamada a una API o un numero Random.

- Describe el proceso de creación de pruebas unitarias con Mockito. Explica cómo se pueden
simular dependencias y verificar comportamientos en las pruebas.

Se usa Mock para crear el mock de la clase
Se usa InjectMocks para inyectar el mock al mock superior, tiene que usarse el patron de inyeccion de dependencias obligatorio

Con when se define el stub, se define el comportamiento predefinido que se debe lanzar al llamar al metodo del mock
Verify para verificar si se llamo al metodo y sus parametros variaron


**Respuesta:**


#### Práctico:
- Escribe pruebas unitarias para la clase TowerDefenseGame utilizando Mockito para simular
las dependencias de Map, Player y Wave.

```java
@ExtendWith(MockitoExtension.class)
public class TowerDefenseGameTest {
    // tests 1
    @Mock
    private Map mockMap;
    // tests 2
    @Mock
    private Wave mockWave;
    @InjectMocks
    private Game game;
    @Test
    public void testPlaceTower_ValidPosition() {
        // Configurar mock para posición válida
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre se haya colocado
        verify(mockMap).placeTower(any(Tower.class), eq(3), eq(4));
    }
}
```

Explicacion: lo que hacemos es inicializar el stub del metodo *isValidPosition()* de mockMap (el mock de Map) para que retorne true, 
esto con el fin de que se ejecute la logica del metodo placeTower. Luego con verify() evaluamos si en el mock de Map se coloco la torre en (3,4).


- Implementa pruebas de integración que verifiquen la interacción entre las clases principales
(TowerDefenseGame, Map, Player, Wave). Utiliza Mockito para controlar y verificar el
comportamiento de las dependencias en estas pruebas.

```java
@ExtendWith(MockitoExtension.class)
public class IntegrationGameTest {
    // tests 1
    @Mock
    private Map mockMap;
    // tests 2
    @Mock
    private Wave mockWave;
    @InjectMocks
    private TowerDefenseGame game;
    @Test
    public void testPlaceTower_ValidPosition() {
        // Configurar mock para posición válida
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre se haya colocado
        verify(mockMap).placeTower(any(Tower.class), eq(3), eq(4));
    }
    @Test
    public void testPlaceTower_InvalidPosition() {
        // Configurar mock para posición inválida
        when(mockMap.isValidPosition(3, 4)).thenReturn(false);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre no se haya colocado
        verify(mockMap, never()).placeTower(any(Tower.class), eq(3), eq(4));
    }
    @Test
    public void testStartWave_WithEnemiesList() {
        when(mockWave.spawnEnemies()).thenReturn(Arrays.asList(new BasicEnemy(), new BasicEnemy(), new BasicEnemy()));
        game.startWave();
        // Verificar que los enemigos han sido generados y la oleada ha comenzado
        assertEquals(3, game.getEnemies().size());
    }
}
```

- Configura un pipeline de integración continua (CI) que ejecute automáticamente las pruebas
unitarias e informe sobre los resultados. Utiliza herramientas como Jenkins o GitHub Actions
para implementar este pipeline (opcional).



## Ejercicio 5: Pruebas de mutación (4 puntos)

#### Teoría:
- Define qué son las pruebas de mutación y cómo contribuyen a la mejora de la calidad del
software. Explica los tipos de operadores de mutación y su propósito.

**Respuesta:**

- Discute las métricas utilizadas para evaluar la efectividad de las pruebas de mutación, como
la tasa de mutación (mutation score) y la cobertura de mutación.

**Respuesta:**

![](img/pitest1.png)

#### Práctico:
- Configura una herramienta de pruebas de mutación, como PIT, en el proyecto Tower
Defense. Asegúrate de integrar la herramienta en el pipeline de CI (opcional).

```
plugins {
    id 'java'
    id 'info.solidsoft.pitest' version '1.15.0'
    id 'jacoco' // Plugin de JaCoCo
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.2'
    testImplementation 'org.junit.jupiter:junit-jupiter-params:5.8.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
    testImplementation 'org.assertj:assertj-core:3.23.1'
    testImplementation 'org.mockito:mockito-inline:3.12.4'
    testImplementation 'org.mockito:mockito-junit-jupiter:3.12.4'
}

test {
    useJUnitPlatform()
}

// jacoco
jacoco {
    toolVersion = "0.8.12" // Versión de JaCoCo (compatible con java 21)
}

jacocoTestReport {
    dependsOn test // Ejecuta las pruebas antes de generar el informe

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

check.dependsOn jacocoTestCoverageVerification

// ./gradlew pitest
// ./gradlew jacocoTestReport

pitest {
    targetClasses = ['org.example.*'] // Paquete de clases a mutar
    mutators = ['DEFAULTS'] // Conjunto de mutadores [OLD_DEFAULTS, DEFAULTS, STRONGER, ALL]
    outputFormats = ['HTML'] // Formato de salida del informe
    timestampedReports = false // Deshabilitar informes con marca de tiempo para facilitar la navegación
    verbose = true
}

// ./gradlew pitest
```

Ejecutamos `./gradlew pitest`

- Implementa pruebas de mutación para la clase Map y analiza los resultados. Asegúrate de
identificar y corregir las pruebas unitarias que no detecten mutaciones.

![](img/pitest_map.png)

- Realiza un informe detallado sobre la calidad de las pruebas del proyecto Tower Defense,
basado en los resultados de las pruebas de mutación. Incluye recomendaciones para
mejorar la cobertura y efectividad de las pruebas.






## Ejercicio 6: Diseño por contrato (Design by Contract) (2 puntos)

#### Teoría:
- Explica el concepto de diseño por contrato y cómo se aplica en el desarrollo de software.
Discute las diferencias entre precondiciones, postcondiciones e invariantes.

**Rpta:**
El diseño por contrato es una metodología del desarrollo de software que define las obligaciones y responsabilidades de los componentes del software mediante contratos que incluyen precondiciones, postcondiciones e invariantes. Esta metodología asegura interacciones predecibles y confiables entre los componentes.

Diferencias:
* Precondiciones: Se verifican antes de la ejecución de un método y son responsabilidades del llamador del método.
Si no se cumplen, el método no se ejecutará correctamente.
* Postcondiciones: Se verifican después de la ejecución de un método y son responsabilidades del método.
* Invariantes: Se verifican antes y después de cualquier operación sobre un objeto, y son propiedades constantes que deben mantenerse durante todo el ciclo de vida del objeto.

- Describe cómo el diseño por contrato puede mejorar la robustez y mantenibilidad del código.

**Rpta:**
El diseño por contrato mejora la robustez al poner condiciones previas y posteriores de lo que se espera de cada metodo, ya que esto hace que se detecten errores temprano y se facilita la mantenibilidad del software mediante pruebas y refactorizaciones.

#### Práctico:
- Aplica el diseño por contrato a la clase Tower. Define las precondiciones, postcondiciones e
invariantes de los métodos principales de la clase.

```java
public void attack(List<Enemy> enemies) {
        // Precondicion
        if (enemies.isEmpty()) {
            throw new IllegalArgumentException("Tienes que tener minimo 1 enemigo");
        }

        for (Enemy enemy: enemies){
            int x_diff = Math.abs(enemy.getPosition().getX() - position.getX());
            int y_diff = Math.abs(enemy.getPosition().getY() - position.getY());
            if ((x_diff <= range || y_diff <= range) && (x_diff == y_diff || x_diff == 0 || y_diff == 0)) {
                enemy.downHealth(damage);
            }
        }
    }
```

- Escribe pruebas unitarias que verifiquen el cumplimiento de los contratos definidos para la
clase Tower. Utiliza herramientas como Java Assertions para implementar estas
verificaciones.

```java
public class TowerContractTest {
    @Test
    public void testTowerPreCondition() {
        Tower tower = new Tower(30, 20, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tower.attack(new ArrayList<>());
        });
        assertEquals(exception.getMessage(), "Tienes que tener minimo 1 enemigo");
    }
}
```

- Realiza una revisión de código para asegurarte de que todas las clases del proyecto Tower
Defense siguen los principios del diseño por contrato. Documenta cualquier ajuste o mejora
necesaria en el código.
