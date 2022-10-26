package com.cybersoft.crm.services;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRoles() {
        return roleRepository.getRoles();
    }

    public boolean deleteRoleById(int id) {
        if (roleRepository.delateRoleById(id) >0)
            return true;
        else return false;
    }
}
