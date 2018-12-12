package com.imrancluster.savedream.user;

import java.util.LinkedHashMap;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.imrancluster.savedream.entity.UserType;
import com.imrancluster.savedream.validation.FieldMatch;
import com.imrancluster.savedream.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class SDUser {

	@NotNull(message = "is required")
	@Size(min = 3, message = "Min 3 character required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 5, message = "Min 5 character required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 5, message = "Min 5 character required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String name;
	
	@NotNull(message="is required")
	@Pattern(regexp="^(0)\\d{10}$", message="Mobile number must 11 digit. eg. 017XXXXXXXX")
	private String mobile;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	@NotNull(message="is required")
	private long userTypeId;

	public SDUser() {
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(long userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	
}
