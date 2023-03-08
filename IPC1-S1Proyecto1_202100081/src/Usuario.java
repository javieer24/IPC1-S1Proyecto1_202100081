import java.util.ArrayList;

public class Usuario {
    private String correo;
    private String contasenia;
    private String rol;
    private String nombre;
    private String apellido;
    private String dpi;
    private String fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String sobreNombre;
    private String telefono;
    private String fotografia;
    private int paquetesEnviados;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<DatoFacturacion> datosFacturacion;
    private ArrayList<Envio> envios;
    public Usuario(String  correo, String contasenia,String rol,String nombre,String apellido,String dpi
            ,String fechaNacimiento,String genero,String nacionalidad,String sobreNombre,String telefono,String fotografia
            ,int  paquetesEnviados,ArrayList<Tarjeta> tarjetas,ArrayList<DatoFacturacion> datosFacturacion,ArrayList<Envio> envios){
        this.correo = correo;
        this.contasenia = contasenia;
        this.rol= rol;
        this.nombre= nombre;
        this.apellido= apellido;
        this.dpi= dpi;
        this.fechaNacimiento= fechaNacimiento;
        this.genero= genero;
        this.nacionalidad= nacionalidad;
        this.sobreNombre= sobreNombre;
        this.telefono= telefono;
        this.fotografia= fotografia;
        this.paquetesEnviados=paquetesEnviados;
        this.tarjetas=tarjetas;
        this.datosFacturacion=datosFacturacion;
        this.envios=envios;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContasenia() {
        return contasenia;
    }

    public void setContasenia(String contasenia) {
        this.contasenia = contasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSobreNombre() {
        return sobreNombre;
    }

    public void setSobreNombre(String sobreNombre) {
        this.sobreNombre = sobreNombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public int getPaquetesEnviados() {
        return paquetesEnviados;
    }

    public void setPaquetesEnviados(int paquetesEnviados) {
        this.paquetesEnviados = paquetesEnviados;
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public ArrayList<DatoFacturacion> getDatosFacturacion() {
        return datosFacturacion;
    }

    public void setDatosFacturacion(ArrayList<DatoFacturacion> datosFacturacion) {
        this.datosFacturacion = datosFacturacion;
    }

    public ArrayList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(ArrayList<Envio> envios) {
        this.envios = envios;
    }
}
