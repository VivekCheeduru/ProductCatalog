package org.example.spring.productcatalog.dto;

import org.example.spring.productcatalog.Models.News;
import java.util.List;

public class RealTimeNewsData {
    private List<News> news;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
