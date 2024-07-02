package com.trello.repository;

import com.trello.domain.SubTask;
import com.trello.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    List<SubTask> findAllByTask(Long TaskId);
}
