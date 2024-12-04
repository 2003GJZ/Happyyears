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
public class PersonTeam extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long personId;

    private Long teamId;

    private Boolean admin;

    private Long grade;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "PersonTeam{" +
            "personId = " + personId +
            ", teamId = " + teamId +
            ", admin = " + admin +
            ", grade = " + grade +
        "}";
    }
}
