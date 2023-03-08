public class Departamento {
    private String codigoRegion;
    private String nombreRegion;
    private String nombreDepartamento;
    private String codigoDepartamento;
    public Departamento(String codigoRegion, String nombreRegion, String codigoDepartamento,String nombreDepartamento){
        this.codigoRegion=codigoRegion;
        this.nombreRegion=nombreRegion;
        this.codigoDepartamento=codigoDepartamento;
        this.nombreDepartamento=nombreDepartamento;
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

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
}
