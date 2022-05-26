package thach.nv.project.service;

import java.util.List;

import thach.nv.project.entity.Role;


public interface RoleService {
	List<Role> getAllRoles();
	Role getById(int id);
}
