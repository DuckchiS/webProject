package com.example.web.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="board")
@Data
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_no")
    private int bNo;

    @Column(name = "B_title", nullable = false, length = 50)
    private String bTitle;

    @Column(name = "B_content", nullable = false, length = 100)
    private String bContent;

    @Column(name = "B_hit", nullable = false)
    private int bHit;

    @Column(name = "B_like", nullable = false)
    private int bLike;

    @Column(name = "B_datetime", nullable = false)
    private LocalDateTime bDatetime;
    
    @Column(name = "B_updatetime", nullable = false)
    private LocalDateTime bUpdatetime;
    
    @Column(name = "B_category", nullable = false)
    private String bCategory;

    @ManyToOne
    @JoinColumn(name = "M_nickname", referencedColumnName = "M_nickname", nullable = false)
    private UserEntity member;

    @OneToMany(mappedBy = "board")
    private List<ReplyEntity> replies;
    
    @Column(name = "B_image", nullable = true)
    private byte[] bImage; // 이미지 필드 추가
}
