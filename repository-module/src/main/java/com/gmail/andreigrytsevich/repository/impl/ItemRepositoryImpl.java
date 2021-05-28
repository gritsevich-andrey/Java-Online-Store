package com.gmail.andreygritsevich.repository.impl;

import java.util.List;
import javax.persistence.Query;

import com.gmail.andreygritsevich.repository.ItemRepository;
import com.gmail.andreygritsevich.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getItemsByPageSorted(int startPosition, int itemsByPage) {
        String hql = "FROM " + entityClass.getName() + " u ORDER BY u.name ASC";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(itemsByPage);
        return query.getResultList();
    }

}
