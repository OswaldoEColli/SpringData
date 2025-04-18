package com.ejemplo.transferenciabancaria;

import com.ejemplo.transferenciabancaria.model.Cuenta;
import com.ejemplo.transferenciabancaria.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Inicializador de datos para pruebas.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public DataInitializer(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public void run(String... args) {
        // Crear cuentas de ejemplo
        Cuenta cuenta1 = new Cuenta(null, "Juan Pérez", new BigDecimal("1000.00"));
        Cuenta cuenta2 = new Cuenta(null, "María López", new BigDecimal("500.00"));
        Cuenta cuenta3 = new Cuenta(null, "Carlos González", new BigDecimal("1500.00"));

        // Guardar cuentas
        cuentaRepository.saveAll(Arrays.asList(cuenta1, cuenta2, cuenta3));

        System.out.println("Datos iniciales cargados correctamente");
    }
}