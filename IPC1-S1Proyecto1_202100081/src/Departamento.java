public class Departamento extends Region{
    private String nombreDepartamento;
    private String codigoDepartamento;
    public Departamento(String codigoRegion, String nombreRegion, double precioNormal,double precioEspecial,int cantidadEnvios, String codigoDepartamento,String nombreDepartamento){
        super(codigoRegion,nombreRegion,precioNormal,precioEspecial,cantidadEnvios);
        this.codigoDepartamento=codigoDepartamento;
        this.nombreDepartamento=nombreDepartamento;
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