package com.myclass.dto;

import java.sql.Date;

public class TaskDTO {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int userId;
	private String jobName;
	private int statusId;
	private float doneRatio;
	private float toDoRatio;
	private float newRatio;

	public float getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(float doneRatio) {
		this.doneRatio = doneRatio;
	}

	public float getToDoRatio() {
		return toDoRatio;
	}

	public void setToDoRatio(float toDoRatio) {
		this.toDoRatio = toDoRatio;
	}

	public float getNewRatio() {
		return newRatio;
	}

	public void setNewRatio(float newRatio) {
		this.newRatio = newRatio;
	}

	public TaskDTO(int id, String name, Date startDate, Date endDate, int userId, String jobName, int statusId) {
		// super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.jobName = jobName;
		this.statusId = statusId;
	}

	public TaskDTO() {
		// super();
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatus(int statusId) {
		this.statusId = statusId;
	}

}
