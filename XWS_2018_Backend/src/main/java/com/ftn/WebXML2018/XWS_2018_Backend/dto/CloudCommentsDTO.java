package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import java.util.ArrayList;

public class CloudCommentsDTO {

	private ArrayList<CloudResponseDTO> comments;

	public CloudCommentsDTO(ArrayList<CloudResponseDTO> comments) {
		super();
		this.comments = comments;
	}
	
	public CloudCommentsDTO() {}

	public ArrayList<CloudResponseDTO> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CloudResponseDTO> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "CloudCommentsDTO [comments=" + comments + "]";
	}
	
	
	
}
