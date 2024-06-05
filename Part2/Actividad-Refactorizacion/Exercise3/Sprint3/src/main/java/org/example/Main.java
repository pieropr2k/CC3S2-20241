package org.example;

import org.example.database.DBConnection;
import org.example.database.DeletableDBConnection;
import org.example.database.EthBlockchainConnection;
import org.example.database.MySQLConnection;
import org.example.manager.BlogManager;
import org.example.manager.DeletableBlogManager;

public class Main {
    public static void main(String[] args) {
        DeletableDBConnection mysqlRepo = new MySQLConnection();
        DBConnection blockchainRepo = new EthBlockchainConnection();

        DeletableBlogManager blogManagerMySQL = new DeletableBlogManager(mysqlRepo);
        blogManagerMySQL.addArticle("Primer artículo en MySQL");
        blogManagerMySQL.printAllArticles();
        blogManagerMySQL.deleteArticle("Primer artículo en MySQL");
        blogManagerMySQL.printAllArticles();


        System.out.println();

        BlogManager blogManagerBlockchain = new BlogManager(blockchainRepo);
        blogManagerBlockchain.addArticle("Primer artículo en Blockchain");
        blogManagerBlockchain.printAllArticles();
        //blogManagerBlockchain.deleteArticle("Primer artículo en Blockchain"); // Manejo especial si es necesario
    }
}