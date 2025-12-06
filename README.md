# Mon projet Hadoop

Le Projet lab1 contient des classes Java pour lire et écrire dans HDFS.

## Installation du Lab Hadoop

1. Installer Docker et ses dépendances.
2. Cloner le projet :

 ```bash
   git clone https://github.com/Dianamikolo/BigdataLabs.git
   cd BigdataLabs/hadoop_lab
   docker network create hadoop
   docker compose up -d
   ```

3. Cree pour chaque fichier java l'executable File.jar

se rendre dans [text](lab1) ou se trouve pom.xml et execute

 ```bash
   mvn clean package
   ```

## Description

- Classe `WriteHDFS` : écrit un fichier dans HDFS

### Capture d'écran

![Capture du terminal](images/captureWRITE.png)

- Classe `ReadHDFS` : lit un fichier depuis HDFS
