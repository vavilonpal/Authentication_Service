package org.combs.authentication_service.service;


import lombok.RequiredArgsConstructor;
import org.combs.authentication_service.entity.Role;
import org.combs.authentication_service.enums.RoleType;
import org.combs.authentication_service.exeptions.RoleNotFoundException;
import org.combs.authentication_service.repository.RoleRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public Role getRoleByRoleType(RoleType roleType) {
        String roleName = roleType.toString();
        return roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found by name" + roleName));
    }

    public Role getRoleById(Long id) {

    }

    public Role createRole(Role user) {

    }

    public Role updateRole(Long id, Role user) {

    }

    public void deleteRole(Role user) {

    }
}
