package com.gmail.andreygritsevich.service;

import com.gmail.andreygritsevich.service.model.CommentDTO;

public interface CommentService {

    CommentDTO add(CommentDTO commentDTO);

    boolean deleteById(Long id);

}
