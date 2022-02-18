package net.codejava.payload.request;

public class UpdateNotesRequest {

	private Long id;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String updateNotes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpdateNotes() {
		return updateNotes;
	}

	public void setUpdateNotes(String updateNotes) {
		this.updateNotes = updateNotes;
	}

}
