public class Region {
    private String codigoRegion;
    private String nombreRegion;
    private double precioNormal ;
    private double precioEspecial;
    private int cantidadEnvios;

    public Region(String codigoRegion, String nombreRegion, double precioNormal,double precioEspecial,int cantidadEnvios){
        this.codigoRegion=codigoRegion;
        this.nombreRegion=nombreRegion;
        this.precioNormal=precioNormal;
        this.precioEspecial=precioEspecial;
        this.cantidadEnvios=cantidadEnvios;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(double precioNormal) {
        this.precioNormal = precioNormal;
    }

    public double getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(double precioEspecial) {
        this.precioEspecial = precioEspecial;
    }

    public int getCantidadEnvios() {
        return cantidadEnvios;
    }

    public void setCantidadEnvios(int cantidadEnvios) {
        this.cantidadEnvios = cantidadEnvios;
    }
}
