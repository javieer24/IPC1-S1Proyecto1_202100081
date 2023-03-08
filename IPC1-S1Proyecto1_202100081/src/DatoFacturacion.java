public class DatoFacturacion {
    private String nombreCompleto;
    private String direccion;
    private String nit;
    public DatoFacturacion(String nombreCompleto,String direccion, String nit){
        this.nombreCompleto=nombreCompleto;
        this.direccion=direccion;
        this.nit=nit;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
