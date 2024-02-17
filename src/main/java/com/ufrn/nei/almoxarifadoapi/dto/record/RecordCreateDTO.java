package com.ufrn.nei.almoxarifadoapi.dto.record;

import com.ufrn.nei.almoxarifadoapi.enums.RecordOperationEnum;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordCreateDTO {
    @Positive
    private Long userID;
    @Positive
    private Long itemID;
    @Positive
    private Integer quantity;
    private RecordOperationEnum operation;
}
