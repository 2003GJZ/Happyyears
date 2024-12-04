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
public class TeamActivity extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long teamId;

    private Long activityId;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "TeamActivity{" +
                "teamId = " + teamId +
                ", activityId = " + activityId +
                "}";
    }
}