package com.ufrn.nei.almoxarifadoapi.service;

import com.ufrn.nei.almoxarifadoapi.dto.RoleResponseDto;
import com.ufrn.nei.almoxarifadoapi.entity.RoleEntity;
import com.ufrn.nei.almoxarifadoapi.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private RoleEntity roleEntity;

    @BeforeEach
    public void setup() {
        roleEntity = createRole();
        MockitoAnnotations.openMocks(this);
    }

    private RoleEntity createRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Cliente");

        return roleEntity;
    }

    @Test
    @DisplayName("Testa a criação de novas roles")
    void saveRoleTest() {
        when(roleRepository.save(roleEntity)).thenReturn(roleEntity);

        RoleResponseDto savedRole = roleService.save(roleEntity);

        verify(roleRepository, times(1)).save(roleEntity);

        assertNotNull(savedRole);

        assertEquals(roleEntity.getId(), savedRole.getId());
        assertEquals(roleEntity.getRole(), savedRole.getRole());
    }

    @Test
    @DisplayName("Testa encontrar uma Role pelo ID no serviço")
    @Transactional(readOnly = true)
    void testFindRoleById() {
        Long roleId = roleEntity.getId();

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(roleEntity));

        RoleResponseDto foundRole = roleService.findById(roleId);

        verify(roleRepository, times(1)).findById(roleId);

        assertNotNull(foundRole);

        assertEquals(roleEntity.getId(), foundRole.getId());
        assertEquals(roleEntity.getRole(), foundRole.getRole());
    }

    @Test
    @DisplayName("Teste para encontrar todas as roles cadastradas")
    @Transactional(readOnly = true)
    void testFindAllRoles() {
        when(roleRepository.findAll()).thenReturn(Collections.singletonList(roleEntity));

        List<RoleResponseDto> roleResponse = roleService.findAllRoles();

        verify(roleRepository, times(1)).findAll();

        assertNotNull(roleResponse);

        assertEquals(roleEntity.getRole(), roleResponse.get(0).getRole());
        assertEquals(roleEntity.getId(), roleResponse.get(0).getId());
    }
}