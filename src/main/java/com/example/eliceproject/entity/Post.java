package com.example.eliceproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "writer", nullable = true)
    private String writer;

    @Column(name = "viewcount", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer viewcount;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    public Post() {
        this.createdAt = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
        if (!this.board.getPosts().contains(this)) {
            this.board.getPosts().add(this);
        }
    }
}
