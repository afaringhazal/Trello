package com.trello.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.trello.domain.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "UserWorkspaceRole",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId","workspaceId"})
        })
@IdClass(UserWorkspaceRole.UserWorkspaceRolePK.class)
public class UserWorkspaceRole {
    @Id
    @Column(name = "userId")
    private Long userId;
    @Id
    @Column(name = "workspaceId")
    private Long workspaceId;


    @Column(name = "role")
    private RoleType role;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;



    public static class UserWorkspaceRolePK implements Serializable {

        private Long userId;

        private Long workspaceId;

        public UserWorkspaceRolePK() {
        }

        public UserWorkspaceRolePK(Long userId, Long workspaceId) {
            super();
            this.userId = userId;
            this.workspaceId = workspaceId;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof UserWorkspaceRolePK)) {
                return false;
            }
            UserWorkspaceRolePK that = (UserWorkspaceRolePK) other;
            if(userId != null
                    && workspaceId != null) {
                return userId.equals(that.userId)
                        && workspaceId.equals(that.workspaceId);
            }
            return false;
        }
        /**
         * Hashcode must also depend on identity values.
         */
        public int hashCode() {
            if(userId != null
                    && workspaceId != null) {
                return userId.hashCode() +
                        workspaceId.hashCode() + UserWorkspaceRolePK.class.hashCode();
            }
            return UserWorkspaceRolePK.class.hashCode();
        }

        public String toString() {
            return userId + " - " + workspaceId;
        }
        public Long getUserId() {
            return userId;
        }
        public void setUserId(Long userId) {
            this.userId = userId;
        }
        public Long getWorkspaceId() {
            return workspaceId;
        }
        public void setWorkspaceId(Long workspaceId) {
            this.workspaceId = workspaceId;
        }

    }

}
