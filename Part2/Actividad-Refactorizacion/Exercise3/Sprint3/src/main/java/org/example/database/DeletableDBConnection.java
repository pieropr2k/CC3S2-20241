package org.example.database;

public interface DeletableDBConnection extends DBConnection {
    void deleteArticle(String article);
}