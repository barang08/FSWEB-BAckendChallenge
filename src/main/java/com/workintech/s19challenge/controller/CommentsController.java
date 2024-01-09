package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.CommentResponse;
import com.workintech.s19challenge.dto.ProductsResponse;
import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.entity.product.Comment;
import com.workintech.s19challenge.entity.product.Products;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.product.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentService commentService;

@Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

@GetMapping
    public List<Comment> findAll(){
    return commentService.findAll();
}

    @GetMapping("/{id}")
    public CommentResponse getComment(@PathVariable long id){
        Comment comment = commentService.findById(id);
        if(comment != null){
            return new CommentResponse(comment.getId(), comment.getTitle(), comment.getDescription());
        }
        throw new GlobalException("Comment is not found with given id: "+id, HttpStatus.NOT_FOUND);
    }




}
