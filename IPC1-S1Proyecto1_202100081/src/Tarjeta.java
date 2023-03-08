public class Tarjeta {
    private String nombreDeLaTarjeta;
    private String tipoTarjeta;
    private String fechaVencimiento;
    private String numeroTarjeta;
    public Tarjeta(String nombreDeLaTarjeta, String tipoTarjeta, String fechaVencimiento,String numeroTarjeta){
        this.nombreDeLaTarjeta=nombreDeLaTarjeta;
        this.tipoTarjeta=tipoTarjeta;
        this.fechaVencimiento=fechaVencimiento;
        this.numeroTarjeta=numeroTarjeta;
    }

    public String getNombreDeLaTarjeta() {
        return nombreDeLaTarjeta;
    }

    public void setNombreDeLaTarjeta(String nombreDeLaTarjeta) {
        this.nombreDeLaTarjeta = nombreDeLaTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
}
