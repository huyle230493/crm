package com.myclass.dto;

import java.sql.Date;

public class TaskOfJobDTO {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String userName;
	private int jobId;
	private int statusId;

	public TaskOfJobDTO() {
		//super();
	}

	public TaskOfJobDTO(int id, String name, Date startDate, Date endDate, String userName, int jobId, int statusId) {
		//super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userName;
		this.jobId = jobId;
		this.statusId = statusId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}
