package com.gmail.andreygritsevich.repository.impl;

import com.gmail.andreygritsevich.repository.NewsRepository;
import com.gmail.andreygritsevich.repository.model.News;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsRepositoryImpl extends GenericRepositoryImpl<Long, News> implements NewsRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getItemsByPageSorted(int startPosition, int itemsByPage) {
        String hql = "FROM " + entityClass.getName() + " n ORDER BY n.date DESC";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(itemsByPage);
        return query.getResultList();
    }

}
