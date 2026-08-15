package com.user.login.dto;
public class ProfileResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String mobile;

    public ProfileResponse() {
    }

    public ProfileResponse(Long id,
                           String name,
                           String email,
                           String mobile,
                           String role) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getMobile() {
    		return mobile;
    		}
    
    public void setMobile(String mobile) {
    			this.mobile = mobile;
		}
    
	@Override
	public String toString() {
		return "ProfileResponse [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + " ,  mobile=" + mobile + "]";
	}
    
    
}
