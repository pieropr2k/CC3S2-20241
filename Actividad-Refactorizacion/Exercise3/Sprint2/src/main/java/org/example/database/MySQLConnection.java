package org.example.database;

public class MySQLConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }
}