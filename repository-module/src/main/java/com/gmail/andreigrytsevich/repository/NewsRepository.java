package com.gmail.andreygritsevich.repository;

import com.gmail.andreygritsevich.repository.model.News;

import java.util.List;

public interface NewsRepository extends GenericRepository<Long, News> {

    List<News> getItemsByPageSorted(int startPosition, int itemsByPage);

}
