package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.service.impl.InstaladorServiceImpl;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class InstaladorControllerTest {

    @Mock
    private InstaladorServiceImpl instaladorServiceImpl;
    @InjectMocks
    private InstaladorController instaladorController;
    @Test
    void createInstalador(){
        Instalador instalador = new Instalador();
        instalador.setId(1L);
        instalador.setNombre("Empresa");
        instalador.setDni("12345678L");
        instaladorController.save(instalador);
        Mockito.verify(instaladorServiceImpl).save(instalador);
    }
}
