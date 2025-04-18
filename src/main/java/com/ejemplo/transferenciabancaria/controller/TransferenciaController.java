package com.ejemplo.transferenciabancaria.controller;

import com.ejemplo.transferenciabancaria.dto.TransferenciaDTO;
import com.ejemplo.transferenciabancaria.exception.CuentaNotFoundException;
import com.ejemplo.transferenciabancaria.exception.SaldoInsuficienteException;
import com.ejemplo.transferenciabancaria.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para operaciones de transferencia bancaria.
 */
@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    /**
     * Endpoint para realizar una transferencia entre cuentas.
     * @param transferenciaDTO Datos de la transferencia
     * @return Respuesta HTTP con el resultado de la operación
     */
    @PostMapping
    @Operation(summary = "Realiza una transferencia entre cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferencia realizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<?> realizarTransferencia(@Valid @RequestBody TransferenciaDTO transferenciaDTO) {
        try {
            transferenciaService.realizarTransferencia(transferenciaDTO);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Transferencia realizada con éxito");

            return ResponseEntity.ok(response);
        } catch (SaldoInsuficienteException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (CuentaNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * Endpoint para consultar el saldo de una cuenta.
     * @param id ID de la cuenta
     * @return Saldo de la cuenta
     */
    @GetMapping("/{id}/saldo")
    @Operation(summary = "Consulta el saldo de una cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<?> consultarSaldo(@PathVariable Long id) {
        try {
            BigDecimal saldo = transferenciaService.obtenerSaldo(id);

            Map<String, Object> response = new HashMap<>();
            response.put("cuentaId", id);
            response.put("saldo", saldo);

            return ResponseEntity.ok(response);
        } catch (CuentaNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * Endpoint para realizar una transferencia utilizando query params.
     * @param cuentaOrigenId ID de la cuenta de origen
     * @param cuentaDestinoId ID de la cuenta de destino
     * @param monto Monto a transferir
     * @return Respuesta HTTP con el resultado de la operación
     */
    @PostMapping("/transferir")
    @Operation(summary = "Realiza una transferencia usando query params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferencia realizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente o datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<?> realizarTransferenciaConParams(
            @RequestParam(name = "origen") Long cuentaOrigenId,
            @RequestParam(name = "destino") Long cuentaDestinoId,
            @RequestParam(name = "monto") BigDecimal monto) {

        try {
            // Crear un DTO con los parámetros recibidos
            TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
            transferenciaDTO.setCuentaOrigenId(cuentaOrigenId);
            transferenciaDTO.setCuentaDestinoId(cuentaDestinoId);
            transferenciaDTO.setMonto(monto);

            // Realizar la transferencia usando el servicio
            transferenciaService.realizarTransferencia(transferenciaDTO);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Transferencia realizada con éxito");

            return ResponseEntity.ok(response);
        } catch (SaldoInsuficienteException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (CuentaNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al procesar la transferencia: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}