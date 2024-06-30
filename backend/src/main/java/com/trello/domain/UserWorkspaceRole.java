package com.trello.domain;

import com.trello.domain.enumeration.RoleType;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDateTime;

public class UserWorkspaceRole {
    @Serial
    private static final long serialVersionUID = 699079129960234963L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SubTaskGenerator")
    @SequenceGenerator(name = "SubTaskGenerator", allocationSize = 1)
    private Long id;

    @Column(name = "role")
    private RoleType role;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id",referencedColumnName="id", insertable=false, updatable=false)
    @Column(name="WorkspacaId", nullable = false)
    private Workspace WorkspacaId;

//todo add user

//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "id",referencedColumnName="id", insertable=false, updatable=false)
//    @Column(name="UserId", nullable = false)
//    private User UserId;

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    private LocalDateTime updatedAt;






}
