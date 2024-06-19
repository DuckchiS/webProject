package com.example.web.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	private long b_no;
	private String b_title;
	private String b_content;
	private long b_hit;
	private long b_like;
	private Date b_datetime;
	private Date b_updatetime;
	private String m_nickname;
	private long r_no;
}
