package com.gmail.andreygritsevich.repository.impl;

import com.gmail.andreygritsevich.repository.CommentRepository;
import com.gmail.andreygritsevich.repository.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl extends GenericRepositoryImpl<Long, Comment> implements CommentRepository {
}
