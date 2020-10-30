package nl.inholland.university.endassignment.data;

import nl.inholland.university.endassignment.models.Article;

import java.util.List;

public class ArticleDao {
    private DataSeed dB;

    public List<Article> getArticles() {
        return dB.articles;
    }

    public ArticleDao(DataSeed dataSeed) {

        this.dB = dataSeed;
    }

}
