package com.ftn.WebXML2018.XWS_2018_Backend.dto;

public class AgentApproveDTO {
	private Long id;
	private String pmb;
	
	public AgentApproveDTO() {
		super();
	}
	public AgentApproveDTO(Long id, String pmb) {
		super();
		this.id = id;
		this.pmb = pmb;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPmb() {
		return pmb;
	}
	public void setPmb(String pmb) {
		this.pmb = pmb;
	}
}
