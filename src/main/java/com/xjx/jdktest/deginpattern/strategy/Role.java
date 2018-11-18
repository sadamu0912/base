package com.xjx.jdktest.deginpattern.strategy;

public class Role {
	private String roleName;
	private String RoleId;
	
	public Role(String roleId,String roleName){
		this.RoleId =roleId;
		this.roleName = roleName;
		
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	
	
}
