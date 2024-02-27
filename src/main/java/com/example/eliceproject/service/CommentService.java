package com.example.eliceproject.service;

import com.example.eliceproject.entity.Comment;
import com.example.eliceproject.entity.Post;
import com.example.eliceproject.exception.ExceptionCode;
import com.example.eliceproject.exception.ServiceLogicException;
import com.example.eliceproject.repository.CommentRepository;
import com.example.eliceproject.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public Comment findComment (Integer CommentId) {
        return commentRepository.findById(CommentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    public List<Comment> findCommentByPostId (Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment (Integer postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND))
        log.info(post.getTitle());

        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment updateComment (Integer CommentId, Comment comment) {
        Comment foundComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> foundComment.setContent(content));

        return commentRepository.save(foundComment);
    }

    public void deleteComment (Integer commentId) {
        Comment foundcomment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        commentRepository.delete(foundcomment);
    }

}
