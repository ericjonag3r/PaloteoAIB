package com.emprosoft7head.controladores;

import com.emprosoft7head.manejoDatos.DatabasePaloteo;
import com.emprosoft7head.paloteoaib.Formulario;

public class coordinador {

    private controlador controlador;
    private logica logica;
    private Formulario formulario;
    private DatabasePaloteo databasePaloteo;

    public coordinador(com.emprosoft7head.controladores.controlador controlador, com.emprosoft7head.controladores.logica logica, Formulario formulario) {
        this.controlador = controlador;
        this.logica = logica;
        this.formulario = formulario;

    }

    public coordinador(com.emprosoft7head.controladores.controlador controlador) {
        this.controlador = controlador;
    }

    public coordinador(com.emprosoft7head.controladores.controlador controlador, com.emprosoft7head.controladores.logica logica) {
        this.controlador = controlador;
        this.logica = logica;
    }

    public coordinador(Formulario formulario) {
        this.formulario = formulario;


        inicializarElementos();


    }


    private void inicializarElementos() {
        databasePaloteo =new DatabasePaloteo(formulario);

        controlador=new controlador(formulario);
        controlador.setDatabasePaloteo(databasePaloteo);



    }

    public coordinador() {
    }

    public DatabasePaloteo getDatabasePaloteo() {
        return databasePaloteo;
    }

    public void setDatabasePaloteo(DatabasePaloteo databasePaloteo) {
        this.databasePaloteo = databasePaloteo;
    }

    public com.emprosoft7head.controladores.controlador getControlador() {
        return controlador;
    }

    public void setControlador(com.emprosoft7head.controladores.controlador controlador) {
        this.controlador = controlador;
    }

    public com.emprosoft7head.controladores.logica getLogica() {
        return logica;
    }

    public void setLogica(com.emprosoft7head.controladores.logica logica) {
        this.logica = logica;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
}
