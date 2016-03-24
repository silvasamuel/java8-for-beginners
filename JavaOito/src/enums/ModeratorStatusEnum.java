package enums;

/**
 * @author samuel.silva
 */
public enum ModeratorStatusEnum {
	MODERATOR("Moderator", true),
	USER("User", false);
	
	private String description;
	private Boolean status;
	
	private ModeratorStatusEnum(String description, Boolean status) {
		this.description = description;
		this.setStatus(status);
	}
	
	public static ModeratorStatusEnum recoverByStatus(Boolean status) {
		for (ModeratorStatusEnum statusEntity : ModeratorStatusEnum.values()) {
			if (statusEntity.getStatus().equals(status)) {
				return statusEntity;
			}
		}
		return null;
	}

	// Gets and Sets
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
