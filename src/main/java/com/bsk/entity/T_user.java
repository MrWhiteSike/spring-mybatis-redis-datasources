package com.bsk.entity;

import java.io.Serializable;

public class T_user implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 278619129832300231L;
	private Integer id;
	private String userName;
	private String password;
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "T_user{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + password + '\''
				+ ", roleId=" + roleId + '}';
	}

}
