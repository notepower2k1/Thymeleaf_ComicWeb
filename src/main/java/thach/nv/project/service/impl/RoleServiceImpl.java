package thach.nv.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.Role;
import thach.nv.project.repository.RoleRepository;
import thach.nv.project.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepo;

	public RoleServiceImpl(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	@Override
	public List<Role> getAllRoles() {
		return this.roleRepo.findAll();
	}

	@Override
	public Role getById(int id) {
		Optional<Role> optional = this.roleRepo.findById(id);
		Role role = null;
		if(optional.isPresent()) {
			role = optional.get();
		} else {
			throw new RuntimeException("Role not found	for id::" + id);
		}
		return role;
	}
}
