package com.store.model;

import java.util.Date;

/*
VO(Value Object) => Một đối tượng đóng gói và lưu trữ các giá trị
DTO(Data Transfer Object) => Các đối tượng truyền giá trị cho các đối tượng trong từng vai trò

Mối quan hệ 1:1 với bảng/chế độ xem
or
Thông tin được hiển thị trên trang mối quan hệ 1:1

1) Nếu kiểu trả về của setter là void, hãy gọi nó như sau:
	member.setNo(10);
	member.setName("hong");
	member.setEmail("hong@naver.com");
	
2) Nếu kiểu trả về của setter là Member, nó sẽ được gọi theo cách chuỗi như dưới đây.
	  member.setNo(10)
			.setName("hong")
			.setEmail("hong@naver.com");
*/

public class Member {

	protected int id;
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phone;
	protected String address;
	protected Date CreatedDate;
	protected Date modifiedDate;

	public int getId() {
		return id;
	}

	public Member setId(int id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public Member setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Member setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Member setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Member setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Member setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Member setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Member setAddress(String address) {
		this.address = address;
		return this;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public Member setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
		return this;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}

}
