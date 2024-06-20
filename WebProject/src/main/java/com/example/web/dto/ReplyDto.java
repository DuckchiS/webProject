package com.example.web.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyDto {
	private long r_no;
	private String r_content;
	private Date r_datetime;
	private String m_nickname;
	private long b_no;
}
