package com.ufrn.nei.almoxarifadoapi.dto.mapper;

import com.ufrn.nei.almoxarifadoapi.dto.pageable.PageableDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableMapper {
    public static PageableDTO toDto(Page page) {
        return new ModelMapper().map(page, PageableDTO.class);
    }
}
