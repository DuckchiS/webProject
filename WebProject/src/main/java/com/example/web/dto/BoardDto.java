package com.example.web.dto;

import java.sql.Date;
import java.util.Base64;

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
	private String b_category;
	private long r_no;
	private byte[] b_image; // 이미지 필드 추가
    public String getB_imageAsBase64() {
        if (b_image != null) {
            return Base64.getEncoder().encodeToString(b_image);
        }
        return null;
    }
}
