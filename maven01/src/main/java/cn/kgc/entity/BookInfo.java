package cn.kgc.entity;

import java.sql.Date;

public class BookInfo {
	private Integer book_id;
	private String book_code;
	private String book_name;
	private Integer book_type;
	private String book_author;
	private String publish_press;
	private Date publish_date;
	private Integer is_borrow;
	private String createdBy;
	private Date creation_time;
	private Date last_updatetime;
	
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer bookId) {
		book_id = bookId;
	}
	public String getBook_code() {
		return book_code;
	}
	public void setBook_code(String bookCode) {
		book_code = bookCode;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String bookName) {
		book_name = bookName;
	}
	public Integer getBook_type() {
		return book_type;
	}
	public void setBook_type(Integer bookType) {
		book_type = bookType;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String bookAuthor) {
		book_author = bookAuthor;
	}
	public String getPublish_press() {
		return publish_press;
	}
	public void setPublish_press(String publishPress) {
		publish_press = publishPress;
	}
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publishDate) {
		publish_date = publishDate;
	}
	public Integer getIs_borrow() {
		return is_borrow;
	}
	public void setIs_borrow(Integer isBorrow) {
		is_borrow = isBorrow;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(Date creationTime) {
		creation_time = creationTime;
	}
	public Date getLast_updatetime() {
		return last_updatetime;
	}
	public void setLast_updatetime(Date lastUpdatetime) {
		last_updatetime = lastUpdatetime;
	}
	
	public BookInfo(Integer bookId, String bookCode, String bookName,
			Integer bookType, String bookAuthor, String publishPress,
			Date publishDate, Integer isBorrow, String createdBy,
			Date creationTime, Date lastUpdatetime) {
		super();
		book_id = bookId;
		book_code = bookCode;
		book_name = bookName;
		book_type = bookType;
		book_author = bookAuthor;
		publish_press = publishPress;
		publish_date = publishDate;
		is_borrow = isBorrow;
		this.createdBy = createdBy;
		creation_time = creationTime;
		last_updatetime = lastUpdatetime;
	}
	public BookInfo() {
		super();
	}
	
	public String toString() {
		return "BookInfo [book_author=" + book_author + ", book_code="
				+ book_code + ", book_id=" + book_id + ", book_name="
				+ book_name + ", book_type=" + book_type + ", createdBy="
				+ createdBy + ", creation_time=" + creation_time
				+ ", is_borrow=" + is_borrow + ", last_updatetime="
				+ last_updatetime + ", publish_date=" + publish_date
				+ ", publish_press=" + publish_press + "]";
	}
	
}
