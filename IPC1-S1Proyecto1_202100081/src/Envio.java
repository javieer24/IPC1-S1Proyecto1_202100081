import java.util.ArrayList;

public class Envio {
    /*
    * ● Código de paquete
● Tipo de servicio (Estándar o especial)
● Destinatario
● Total de envío
● Tipo de pago

    *
    * */
    private String codigoPaquete;
    private String tipoServicio;
    private String destinatario;
    private Double totalEnvio;
    private String tipoPago;
    private ArrayList<String> datosFactura;
    private ArrayList<String> datosGuia;

    public Envio(String codigoPaquete, String tipoServicio,String destinatario, Double totalEnvio, String tipoPago, ArrayList<String> datosFactura,  ArrayList<String> datosGuia){
        this.codigoPaquete=codigoPaquete;
        this.tipoServicio=tipoServicio;
        this.destinatario=destinatario;
        this.totalEnvio=totalEnvio;
        this.tipoPago=tipoPago;
        this.datosFactura=datosFactura;
        this.datosGuia=datosGuia;
    }

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Double getTotalEnvio() {
        return totalEnvio;
    }

    public void setTotalEnvio(Double totalEnvio) {
        this.totalEnvio = totalEnvio;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public ArrayList<String> getDatosFactura() {
        return datosFactura;
    }

    public void setDatosFactura(ArrayList<String> datosFactura) {
        this.datosFactura = datosFactura;
    }

    public ArrayList<String> getDatosGuia() {
        return datosGuia;
    }

    public void setDatosGuia(ArrayList<String> datosGuia) {
        this.datosGuia = datosGuia;
    }
}
