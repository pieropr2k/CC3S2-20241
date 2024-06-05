package org.example.manager;
import org.example.database.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class BlogManager {
    private List<String> articles = new ArrayList<>();
    private DBConnection articleDatabase;

    public BlogManager(DBConnection articleDatabase) {
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
        if (articles.isEmpty()) {
            System.out.println("No hay artículos registrados.");
            return ;
        }
        for (String article : articles) {
            System.out.println("Artículo: " + article);
        }
    }

    public List<String> getArticles() {
        return articles;
    }
}