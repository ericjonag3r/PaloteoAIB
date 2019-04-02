package com.emprosoft7head.manejoDatos;

import java.security.PublicKey;

public class paloteoVo {

    private String CUENTA;
    private String NODO;
    private String CIUDAD;
    private String CMTS;
    private String DESCRIPCION;
    private String OTROS1;
    private String OTROS2;
    private String OTROS3;
    private String OTROS4;

    public paloteoVo() {

    }

    public paloteoVo(String CUENTA, String NODO, String CIUDAD, String CMTS, String DESCRIPCION, String OTROS1, String OTROS2, String OTROS3, String OTROS4) {
        this.CUENTA = CUENTA;
        this.NODO = NODO;
        this.CIUDAD = CIUDAD;
        this.CMTS = CMTS;
        this.DESCRIPCION = DESCRIPCION;
        this.OTROS1 = OTROS1;
        this.OTROS2 = OTROS2;
        this.OTROS3 = OTROS3;
        this.OTROS4 = OTROS4;
    }

    @Override
    public String toString() {
        return "paloteoVo{" +
                "CUENTA='" + CUENTA + '\'' +
                ", NODO='" + NODO + '\'' +
                ", CIUDAD='" + CIUDAD + '\'' +
                ", CMTS='" + CMTS + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                ", OTROS1='" + OTROS1 + '\'' +
                ", OTROS2='" + OTROS2 + '\'' +
                ", OTROS3='" + OTROS3 + '\'' +
                ", OTROS4='" + OTROS4 + '\'' +
                '}';
    }



    public String getCUENTA() {
        return CUENTA;
    }

    public void setCUENTA(String CUENTA) {
        this.CUENTA = CUENTA;
    }

    public String getNODO() {
        return NODO;
    }

    public void setNODO(String NODO) {
        this.NODO = NODO;
    }

    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String CIUDAD) {
        this.CIUDAD = CIUDAD;
    }

    public String getCMTS() {
        return CMTS;
    }

    public void setCMTS(String CMTS) {
        this.CMTS = CMTS;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getOTROS1() {
        return OTROS1;
    }

    public void setOTROS1(String OTROS1) {
        this.OTROS1 = OTROS1;
    }

    public String getOTROS2() {
        return OTROS2;
    }

    public void setOTROS2(String OTROS2) {
        this.OTROS2 = OTROS2;
    }

    public String getOTROS3() {
        return OTROS3;
    }

    public void setOTROS3(String OTROS3) {
        this.OTROS3 = OTROS3;
    }

    public String getOTROS4() {
        return OTROS4;
    }

    public void setOTROS4(String OTROS4) {
        this.OTROS4 = OTROS4;
    }
}
