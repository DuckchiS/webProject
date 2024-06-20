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
@Table(name="reply")
@Data
public class ReplyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "R_no")
	private int rNo;
	
	@Column(name = "R_content", length = 50)
	private String rContent;
	
    @Column(name = "R_datetime", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime rDatetime;

    @ManyToOne
    @JoinColumn(name = "M_nickname", referencedColumnName = "M_nickname")
    private UserEntity member;

    @ManyToOne
    @JoinColumn(name = "B_no", referencedColumnName = "B_no")
    private BoardEntity board;
}
