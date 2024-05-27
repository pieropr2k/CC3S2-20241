package org.example.database;

public class MySQLConnection implements DeletableDBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }

    @Override
    public void deleteArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}