package org.example.manager;
import org.example.database.MySQLConnection;

import java.util.ArrayList;
import java.util.List;

public class BlogManager {
    private List<String> articles = new ArrayList<>();
    private MySQLConnection articleDatabase;

    public BlogManager(MySQLConnection articleDatabase) {
        this.articleDatabase = articleDatabase;
    }

    public void addArticle(String article) {
        if (article != null && !article.isEmpty()) {
            articles.add(article);
            System.out.println("Artículo añadido: " + article);
            articleDatabase.saveArticle(article);
        }
    }

    public void printAllArticles() {
        for (String article : articles) {
            System.out.println("Artículo: " + article);
        }
    }
}