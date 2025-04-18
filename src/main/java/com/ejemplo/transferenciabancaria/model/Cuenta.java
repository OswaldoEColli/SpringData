package com.ejemplo.transferenciabancaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Representa una cuenta bancaria en el sistema.
 */
@Entity
@Table(name = "cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String propietario;

    @Column(nullable = false)
    private BigDecimal saldo;

    /**
     * Verifica si la cuenta tiene saldo suficiente para una operación.
     * @param monto El monto a verificar
     * @return true si hay saldo suficiente, false en caso contrario
     */
    public boolean tieneSaldoSuficiente(BigDecimal monto) {
        return this.saldo.compareTo(monto) >= 0;
    }
}