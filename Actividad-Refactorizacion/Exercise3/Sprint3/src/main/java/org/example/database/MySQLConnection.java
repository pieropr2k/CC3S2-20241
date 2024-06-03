package org.example.database;

public class MySQLConnection implements DeletableDBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }

    @Override
    public void deleteArticle(String article) {
        System.out.println("Eliminando en la base de datos: " + article);
    }
}