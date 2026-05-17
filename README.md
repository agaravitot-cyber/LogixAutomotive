# LogixAutomotive - Gestor de parqueaderos  
## Proyecto Programacion Orientada a Objetos 2026-1 UNAl  
## Integrantes:  

Yeiner Esteban Hernandez Samboni  
Santiago Esteban Rivas Ruiz  
Juan Esteban Martinez Marquez  
Sebastian Chisaba Cruz  
Andres Felipe Garavito Tunjo  
Juan Camilo Bocanegra Luna  

## Dependencias

- **Java 21** (JDK)
- **JavaFX 21 SDK** (copiar los `.jar` en la carpeta `lib/`):
  - javafx.base.jar
  - javafx.controls.jar
  - javafx.fxml.jar
  - javafx.graphics.jar
  
## Compilación y ejecución

```bash
javac --module-path lib --add-modules javafx.controls,javafx.fxml \
      -d bin src/main/java/parqueaderoapp/control/App.java

java --module-path lib --add-modules javafx.controls,javafx.fxml \
     -cp bin parqueaderoapp.control.App
