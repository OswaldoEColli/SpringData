package com.ejemplo.transferenciabancaria.exception;

/**
 * Excepción lanzada cuando no se encuentra una cuenta.
 */
public class CuentaNotFoundException extends RuntimeException {

    public CuentaNotFoundException(String mensaje) {
        super(mensaje);
    }
}