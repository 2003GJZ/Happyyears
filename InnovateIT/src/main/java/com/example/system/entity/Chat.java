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
public class Chat extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long aId;

    private Long bId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    @Override
    public String toString() {
        return "Chat{" +
            "id = " + id +
            ", aId = " + aId +
            ", bId = " + bId +
        "}";
    }
}
