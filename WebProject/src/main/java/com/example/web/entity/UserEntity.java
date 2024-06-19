package com.example.web.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "M_no")
	private int mNo;
	
	@Column(name = "M_id", nullable = false, unique = true, length = 30)
	private String mId;
	
	@Column(name = "M_pw", nullable = false, length = 255)
	private String mPw;
	
	@Column(name = "M_nickname", nullable = false, unique = true, length = 50)
	private String mNickname;
	
	@Column(name = "M_number", nullable = false, length = 20)
	private String mNumber;
	
	@Column(name = "M_email", nullable = false, length = 30)
	private String mEmail;
	
	@Column(name = "M_name", nullable = false, length = 10)
	private String mName;
	
	@Column(name = "M_role", length = 100)
	private String mRole;
	
    @OneToMany(mappedBy = "member")
    private List<BoardEntity> boards;

    @OneToMany(mappedBy = "member")
    private List<ReplyEntity> replies;

}
