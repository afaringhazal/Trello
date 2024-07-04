package com.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trello.domain.enumeration.RoleType;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDateTime;

public class UserWorkspaceRole {
//    @Serial
//    private static final long serialVersionUID = 699079129960234963L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserWorkspaceRoleGenerator")
    @SequenceGenerator(name = "UserWorkspaceRoleGenerator", allocationSize = 1)
    private Long id;

    @Column(name = "role")
    private RoleType role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    @JsonIgnore
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    private LocalDateTime updatedAt;






}
