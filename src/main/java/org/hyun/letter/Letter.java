package org.hyun.letter;

public class Letter {
	String letterId; 
	String title;
	String content;
	String senderId;
	String senderName; 
	String receiverId;
	String receiverName;
	String cdate;
	
	public String getletterId() {
		return letterId;
	}

	public void setletterId(String letterId) {
		this.letterId = letterId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getsenderId() {
		return senderId;
	}

	public void setsenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getsenderName() {
		return senderName;
	}

	public void setsenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public String getreceiverId() {
		return receiverId;
	}

	public void setreceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getreceiverName() {
		return receiverName;
	}

	public void setreceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	

public String getContentHtml() {
	if(content !=null)
		return content.replace("\n", "<br/>");
	return null;
	
}
	@Override
	public String toString() {
		return "Article [letterId=" + letterId + ", title=" + title
				+ ", content=" + content + ", senderId=" + senderId + ", senderName="
				+ senderName + ", cdate=" + cdate + "]";
	}
}
