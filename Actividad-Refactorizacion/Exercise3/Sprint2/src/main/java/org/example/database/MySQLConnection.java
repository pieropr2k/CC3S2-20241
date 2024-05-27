package org.example.database;

public class MySQLConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}