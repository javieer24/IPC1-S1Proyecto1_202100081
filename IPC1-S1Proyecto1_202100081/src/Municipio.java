public class Municipio {
    private String codigoDepartamento;
    private String nombreMunicipio;
    public Municipio(String codigoDepartamento, String nombreMunicipio){
        this.codigoDepartamento=codigoDepartamento;
        this.nombreMunicipio=nombreMunicipio;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }
}

