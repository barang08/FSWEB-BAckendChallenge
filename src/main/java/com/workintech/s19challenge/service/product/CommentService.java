package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Comment;


import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(long id);
    Comment save(Comment comment);
    Comment delete(long id);
}
