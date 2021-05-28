package com.gmail.andreygritsevich.service;

import com.gmail.andreygritsevich.service.model.NewsDTO;

import java.util.List;

public interface NewsService {

    NewsDTO add(NewsDTO newsDTO);

    Long getCountNews();

    List<NewsDTO> getItemsByPageSorted(Integer page);

    NewsDTO findById(Long id);

    boolean deleteById(Long id);

    NewsDTO update(NewsDTO newsDTO);

}
