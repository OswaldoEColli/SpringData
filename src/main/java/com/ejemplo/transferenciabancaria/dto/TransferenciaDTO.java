package com.ejemplo.transferenciabancaria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

/**
 * DTO para realizar operaciones de transferencia entre cuentas.
 */
@Data
public class TransferenciaDTO {

    @NotNull(message = "La cuenta de origen es obligatoria")
    private Long cuentaOrigenId;

    @NotNull(message = "La cuenta de destino es obligatoria")
    private Long cuentaDestinoId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor que cero")
    private BigDecimal monto;
}