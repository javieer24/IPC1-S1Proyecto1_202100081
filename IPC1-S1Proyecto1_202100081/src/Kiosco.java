public class Kiosco {
    private String codigoKiosco;
    private String nombreKiosco;
    private String codigoRegion;

    public Kiosco(String codigoKiosco,String nombreKiosco, String codigoRegion){
        this.codigoKiosco=codigoKiosco;
        this.nombreKiosco=nombreKiosco;
        this.codigoRegion=codigoRegion;
    }

    public String getCodigoKiosco() {
        return codigoKiosco;
    }

    public void setCodigoKiosco(String codigoKiosco) {
        this.codigoKiosco = codigoKiosco;
    }

    public String getNombreKiosco() {
        return nombreKiosco;
    }

    public void setNombreKiosco(String nombreKiosco) {
        this.nombreKiosco = nombreKiosco;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }
}
