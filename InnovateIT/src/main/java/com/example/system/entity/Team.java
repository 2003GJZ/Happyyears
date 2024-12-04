package com.example.system.entity;



import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
public class Team extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String type;

    private String description;

    private Long leaderId;

    private long creationDate;

    private String status;

    private String participatingInActivityId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParticipatingInActivityId() {
        return participatingInActivityId;
    }

    public void setParticipatingInActivityId(String participatingInActivityId) {
        this.participatingInActivityId = participatingInActivityId;
    }



    @Override
    public String toString() {
        return "Team{" +
                "id = " + id +
                ", name = " + name +
                ", type = " + type +
                ", description = " + description +
                ", leaderId = " + leaderId +
                ", creationDate = " + creationDate +
                ", status = " + status +
                ", participatingInActivityId = " + participatingInActivityId +
                "}";
    }
}
