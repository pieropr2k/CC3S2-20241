package org.example;

import org.example.database.DBConnection;
import org.example.database.MySQLConnection;
import org.example.manager.BlogManager;

public class Main {
    public static void main(String[] args) {
        DBConnection mysqlRepo = new MySQLConnection();

        BlogManager blogManagerMySQL = new BlogManager(mysqlRepo);
        blogManagerMySQL.addArticle("Primer artículo en MySQL");
        blogManagerMySQL.printAllArticles();
    }
}