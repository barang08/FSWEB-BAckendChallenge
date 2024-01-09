package com.workintech.s19challenge.repository.products;

import com.workintech.s19challenge.entity.product.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
