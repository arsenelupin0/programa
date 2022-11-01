package Modelo;

import java.util.Objects;

public class Lecturas {
    String codigo_porcion;
    String uni_lectura;
    String doc_lectura;
    String cuenta_contrato;
    String medidor;
    String lectura_ant;
    String lectura_act;
    String anomalia_1;
    String anomalia_2;
    String codigo_operario;
    String vigencia;
    String fecha;
    String orden_lectura;
    String leido;
    String calle;
    String edificio;
    String suplemento_casa;
    String interloc_comercial;
    String apellido;
    String nombre;
    String clase_instalacion;
    public Lecturas(String codigo_porcion, String uni_lectura, String doc_lectura, String cuenta_contrato, String medidor, String lectura_ant, String lectura_act, String anomalia_1, String anomalia_2, String codigo_operario, String vigencia, String fecha, String orden_lectura, String leido, String calle, String edificio, String suplemento_casa, String interloc_comercial, String apellido, String nombre, String clase_instalacion) {
        this.codigo_porcion = codigo_porcion;
        this.uni_lectura = uni_lectura;
        this.doc_lectura = doc_lectura;
        this.cuenta_contrato = cuenta_contrato;
        this.medidor = medidor;
        this.lectura_ant = lectura_ant;
        this.lectura_act = lectura_act;
        this.anomalia_1 = anomalia_1;
        this.anomalia_2 = anomalia_2;
        this.codigo_operario = codigo_operario;
        this.vigencia = vigencia;
        this.fecha = fecha;
        this.orden_lectura = orden_lectura;
        this.leido = leido;
        this.calle = calle;
        this.edificio = edificio;
        this.suplemento_casa = suplemento_casa;
        this.interloc_comercial = interloc_comercial;
        this.apellido = apellido;
        this.nombre = nombre;
        this.clase_instalacion = clase_instalacion;
    }
    public String getCodigo_porcion() { return codigo_porcion; }
    public void setCodigo_porcion(String codigo_porcion) {
        this.codigo_porcion = codigo_porcion;
    }
    public String getUni_lectura() {
        return uni_lectura;
    }
    public void setUni_lectura(String uni_lectura) {
        this.uni_lectura = uni_lectura;
    }
    public String getDoc_lectura() {
        return doc_lectura;
    }
    public void setDoc_lectura(String doc_lectura) {
        this.doc_lectura = doc_lectura;
    }
    public String getCuenta_contrato() {
        return cuenta_contrato;
    }
    public void setCuenta_contrato(String cuenta_contrato) {
        this.cuenta_contrato = cuenta_contrato;
    }
    public String getMedidor() {
        return medidor;
    }
    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }
    public String getLectura_ant() {
        return lectura_ant;
    }
    public void setLectura_ant(String lectura_ant) {
        this.lectura_ant = lectura_ant;
    }
    public String getLectura_act() {
        return lectura_act;
    }
    public void setLectura_act(String lectura_act) {
        this.lectura_act = lectura_act;
    }
    public String getAnomalia_1() {
        return anomalia_1;
    }
    public void setAnomalia_1(String anomalia_1) {
        this.anomalia_1 = anomalia_1;
    }
    public String getAnomalia_2() {
        return anomalia_2;
    }
    public void setAnomalia_2(String anomalia_2) {
        this.anomalia_2 = anomalia_2;
    }
    public String getCodigo_operario() {
        return codigo_operario;
    }
    public void setCodigo_operario(String codigo_operario) {
        this.codigo_operario = codigo_operario;
    }
    public String getVigencia() {
        return vigencia;
    }
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getOrden_lectura() {
        return orden_lectura;
    }
    public void setOrden_lectura(String orden_lectura) {
        this.orden_lectura = orden_lectura;
    }
    public String getLeido() {
        return leido;
    }
    public void setLeido(String leido) {
        this.leido = leido;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getEdificio() {
        return edificio;
    }
    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
    public String getSuplemento_casa() {
        return suplemento_casa;
    }
    public void setSuplemento_casa(String suplemento_casa) {
        this.suplemento_casa = suplemento_casa;
    }
    public String getInterloc_comercial() {
        return interloc_comercial;
    }
    public void setInterloc_comercial(String interloc_comercial) {
        this.interloc_comercial = interloc_comercial;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getClase_instalacion() {
        return clase_instalacion;
    }
    public void setClase_instalacion(String clase_instalacion) {
        this.clase_instalacion = clase_instalacion;
    }

    @Override
    public String toString() {
        return "Lecturas{" +
                "codigo_porcion='" + codigo_porcion + '\'' +
                ", uni_lectura='" + uni_lectura + '\'' +
                ", doc_lectura='" + doc_lectura + '\'' +
                ", cuenta_contrato='" + cuenta_contrato + '\'' +
                ", medidor='" + medidor + '\'' +
                ", lectura_ant='" + lectura_ant + '\'' +
                ", lectura_act='" + lectura_act + '\'' +
                ", anomalia_1='" + anomalia_1 + '\'' +
                ", anomalia_2='" + anomalia_2 + '\'' +
                ", codigo_operario='" + codigo_operario + '\'' +
                ", vigencia='" + vigencia + '\'' +
                ", fecha='" + fecha + '\'' +
                ", orden_lectura='" + orden_lectura + '\'' +
                ", leido='" + leido + '\'' +
                ", calle='" + calle + '\'' +
                ", edificio='" + edificio + '\'' +
                ", suplemento_casa='" + suplemento_casa + '\'' +
                ", interloc_comercial='" + interloc_comercial + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", clase_instalacion='" + clase_instalacion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturas lecturas = (Lecturas) o;
        return Objects.equals(codigo_porcion, lecturas.codigo_porcion) && Objects.equals(uni_lectura, lecturas.uni_lectura) && Objects.equals(doc_lectura, lecturas.doc_lectura) && Objects.equals(cuenta_contrato, lecturas.cuenta_contrato) && Objects.equals(medidor, lecturas.medidor) && Objects.equals(lectura_ant, lecturas.lectura_ant) && Objects.equals(lectura_act, lecturas.lectura_act) && Objects.equals(anomalia_1, lecturas.anomalia_1) && Objects.equals(anomalia_2, lecturas.anomalia_2) && Objects.equals(codigo_operario, lecturas.codigo_operario) && Objects.equals(vigencia, lecturas.vigencia) && Objects.equals(fecha, lecturas.fecha) && Objects.equals(orden_lectura, lecturas.orden_lectura) && Objects.equals(leido, lecturas.leido) && Objects.equals(calle, lecturas.calle) && Objects.equals(edificio, lecturas.edificio) && Objects.equals(suplemento_casa, lecturas.suplemento_casa) && Objects.equals(interloc_comercial, lecturas.interloc_comercial) && Objects.equals(apellido, lecturas.apellido) && Objects.equals(nombre, lecturas.nombre) && Objects.equals(clase_instalacion, lecturas.clase_instalacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_porcion, uni_lectura, doc_lectura, cuenta_contrato, medidor, lectura_ant, lectura_act, anomalia_1, anomalia_2, codigo_operario, vigencia, fecha, orden_lectura, leido, calle, edificio, suplemento_casa, interloc_comercial, apellido, nombre, clase_instalacion);
    }
}
