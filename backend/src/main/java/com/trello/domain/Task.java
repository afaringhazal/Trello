package com.trello.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trello.domain.enumeration.PriorityType;
import com.trello.domain.enumeration.TaskStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "Task")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Serial
    private static final long serialVersionUID = 699079129960234963L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskGenerator")
    @SequenceGenerator(name = "TaskGenerator", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TaskStatusType status;
    @Column(name = "estimatedTime")
    private LocalDateTime estimatedTime;

    @Column(name = "actualTime")
    private LocalDateTime actualTime;
    @Column(name = "dueDate")
    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    @JsonIgnore
    private Workspace workspace;

    @Column(name = "priority")
    private PriorityType priority;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id",referencedColumnName="id", insertable=false,
    // updatable=false)
    // private User assigneeId;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "imageURL")
    private String imageURL;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private Set<SubTask> subTasks = new HashSet<>();

}
