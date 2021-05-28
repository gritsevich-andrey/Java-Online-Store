package com.gmail.andreygritsevich.repository.impl;

import com.gmail.andreygritsevich.repository.FeedbackRepository;
import com.gmail.andreygritsevich.repository.model.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackRepositoryImpl extends GenericRepositoryImpl<Long, Feedback> implements FeedbackRepository {
}
