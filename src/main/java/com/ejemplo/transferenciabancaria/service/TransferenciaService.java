package com.ejemplo.transferenciabancaria.service;

import com.ejemplo.transferenciabancaria.dto.TransferenciaDTO;
import java.math.BigDecimal;

/**
 * Interfaz para el servicio de transferencias entre cuentas.
 */
public interface TransferenciaService {

    /**
     * Realiza una transferencia entre dos cuentas.
     * @param transferenciaDTO Datos de la transferencia
     */
    void realizarTransferencia(TransferenciaDTO transferenciaDTO);

    /**
     * Obtiene el saldo de una cuenta.
     * @param cuentaId ID de la cuenta
     * @return El saldo actual
     */
    BigDecimal obtenerSaldo(Long cuentaId);
}