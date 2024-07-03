package com.trello.repository;
import com.trello.domain.UserWorkspaceRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserWorkspaceRoleRepository extends JpaRepository<UserWorkspaceRole, UserWorkspaceRole.UserWorkspaceRolePK> {

    List<UserWorkspaceRole> findAllByWorkspaceId(Long id);
}