package com.gmail.andreygritsevich.service;

import com.gmail.andreygritsevich.service.model.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackDTO add(FeedbackDTO feedbackDTO);

    Long getCountFeedback();

    List<FeedbackDTO> getItemsByPage(int page);

    void deleteById(Long id);

}
