package com.yedam.notice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int noticeId;
    private String noticeWriter;
    private String noticeTitle;
    private String noticeSubject;
    private Date noticeDate;
    private int hitCount;
    private String attachFile;
	private int startIndex;
	private int	pageSize;
	private int lastIndex;
	
	public void setLastIndex(int start, int size) {
		this.lastIndex = startIndex + pageSize -1;
	}
}
