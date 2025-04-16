package com.estsoft.demo.comment;

import com.estsoft.demo.comment.dto.CommentRequest;
import com.estsoft.demo.comment.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    @ResponseBody
    @PostMapping("/api/articles/{articlesId}/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long articlesId, @RequestBody CommentRequest request) {
        Comment comment = commentService.saveComment(articlesId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponse(comment));
    }

    @ResponseBody
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> findComment(@PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        return ResponseEntity.ok().body(new CommentResponse(comment));
    }

    @ResponseBody
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest request) {
        Comment comment = commentService.updateComment(commentId, request);
        return ResponseEntity.ok().body(new CommentResponse(comment));
    }

    @ResponseBody
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
