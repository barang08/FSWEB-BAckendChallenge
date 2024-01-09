package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.entity.product.Comment;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.products.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;


    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(long id) {
        Optional<Comment> findComment = commentRepository.findById(id);
        if(findComment.isPresent()){
            return findComment.get();
        }else{
            throw new GlobalException("No comment related to the given id found"+id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment delete(long id) {
      Comment comment = findById(id);
      if(comment != null){
          commentRepository.delete(comment);
          return comment;
    }else {
          throw new GlobalException("No comment related to the given id found"+id, HttpStatus.BAD_REQUEST);
      }
    }

}
