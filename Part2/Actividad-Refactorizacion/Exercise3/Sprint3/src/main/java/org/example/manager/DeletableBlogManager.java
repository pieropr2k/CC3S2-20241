package org.example.manager;

import org.example.database.DeletableDBConnection;

public class DeletableBlogManager extends BlogManager{
    private DeletableDBConnection deletableArticleDatabase;

    public DeletableBlogManager(DeletableDBConnection deletableArticleDatabase) {
        super(deletableArticleDatabase);
        this.deletableArticleDatabase = deletableArticleDatabase;
    }

    public void deleteArticle(String article) {
        if (article != null) {
            System.out.println("Artículo eliminado: " + article);
            super.getArticles().remove(article);
        }
    }
}