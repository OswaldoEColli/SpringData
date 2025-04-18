package com.ejemplo.transferenciabancaria.service;

import com.ejemplo.transferenciabancaria.dto.TransferenciaDTO;
import com.ejemplo.transferenciabancaria.exception.CuentaNotFoundException;
import com.ejemplo.transferenciabancaria.exception.SaldoInsuficienteException;
import com.ejemplo.transferenciabancaria.model.Cuenta;
import com.ejemplo.transferenciabancaria.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * Implementación del servicio de transferencias.
 */
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public TransferenciaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    /**
     * Realiza una transferencia entre dos cuentas.
     * @param transferenciaDTO Datos de la transferencia
     * @throws CuentaNotFoundException si alguna cuenta no existe
     * @throws SaldoInsuficienteException si la cuenta de origen no tiene saldo suficiente
     */
    @Override
    @Transactional
    public void realizarTransferencia(TransferenciaDTO transferenciaDTO) {
        Cuenta origen = cuentaRepository.findById(transferenciaDTO.getCuentaOrigenId())
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta de origen no encontrada"));

        Cuenta destino = cuentaRepository.findById(transferenciaDTO.getCuentaDestinoId())
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta de destino no encontrada"));

        if (!origen.tieneSaldoSuficiente(transferenciaDTO.getMonto())) {
            throw new SaldoInsuficienteException("Saldo insuficiente en la cuenta de origen");
        }

        // Realizar la transferencia
        origen.setSaldo(origen.getSaldo().subtract(transferenciaDTO.getMonto()));
        destino.setSaldo(destino.getSaldo().add(transferenciaDTO.getMonto()));

        // Guardar cambios
        cuentaRepository.save(origen);
        cuentaRepository.save(destino);
    }

    /**
     * Obtiene el saldo de una cuenta.
     * @param cuentaId ID de la cuenta
     * @return El saldo actual
     * @throws CuentaNotFoundException si la cuenta no existe
     */
    @Override
    public BigDecimal obtenerSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada"));
        return cuenta.getSaldo();
    }
}