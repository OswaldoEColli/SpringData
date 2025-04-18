package com.ejemplo.transferenciabancaria.exception;

/**
 * Excepción lanzada cuando una cuenta no tiene saldo suficiente para realizar una operación.
 */
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}