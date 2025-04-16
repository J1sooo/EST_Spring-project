package com.estsoft.demo.comment;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.BlogRepository;
import com.estsoft.demo.comment.dto.CommentRequest;
import com.estsoft.demo.exception.NotExistsIdException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public Comment saveComment(Long articleId, CommentRequest request) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException(articleId + ": 게시물 없음"));
        return commentRepository.save(new Comment(request.getBody(), article));
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new NotExistsIdException(commentId));
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequest request) {
        Comment comment = findComment(commentId);
        return comment.updateBody(request.getBody());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
