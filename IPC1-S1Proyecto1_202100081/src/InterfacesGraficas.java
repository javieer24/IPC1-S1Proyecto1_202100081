import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;


public class InterfacesGraficas {
    static String almacenamiento = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";

    static ArrayList<Kiosco> kioscos = new ArrayList<>();
    static ArrayList<Region> regiones = new ArrayList<>();
    static ArrayList<Departamento> departamentos = new ArrayList<>();

    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Municipio> municipios = new ArrayList<>();
    static String dpiLoggeado="";
    static double totalCotizacion=0.00;

    static double ingresosTotales=0.00;
    static int totalEnviosGeneral=0;

    static String correoAdmin="ipc@gmail.com";
    static String passAdmin="1234";
    static int numeroFactura=0;


    public static void main(String[] args) throws IOException {
        inicializarRegiones();
        inicioDeSesion();
    }

    public static void generarHtmlFactura(String codigo){
        java.io.FileWriter htmlArchivo = null;
        java.io.PrintWriter escritor = null;
        Usuario usuarioTemp=null;
        Envio envioTemp=null;
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                usuarioTemp=usuario;
                break;
            }
        }

        for (Envio envio : usuarioTemp.getEnvios()) {
            if (Objects.equals(envio.getCodigoPaquete(), codigo)) {
                envioTemp=envio;
                break;
            }
        }

        try {
            htmlArchivo = new java.io.FileWriter("Factura"+dpiLoggeado+".html");
            escritor = new java.io.PrintWriter(htmlArchivo);
            escritor.println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "\n"
                    + "\n"
                    + "<title>Cotizacion</title>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body bgcolor=”#6CFC07”>\n"
                    + "\n"
                    + "<center>");
            escritor.println("<h1>" + "Factura No. " +envioTemp.getDatosFactura().get(0) + "</h1></center>\n");
            escritor.println("Datos facturacion:  " +envioTemp.getDatosFactura().get(4)+ "</h2>\n");
            escritor.println( "Origen:  " +envioTemp.getDatosFactura().get(2)+ "</h2>\n");
            escritor.println("Destino:  " +envioTemp.getDatosFactura().get(3)+ "</h2>\n");

            escritor.println("<center> <table> <tr> <th>No. Paquetes</th> <th>Peso Del Paquete</th> <th> Total Pago</th></tr>");
            escritor.println("<tr> <td>"+envioTemp.getDatosFactura().get(7)+"</td> <td>"+envioTemp.getDatosFactura().get(6)+"</td> <td>"+ envioTemp.getDatosFactura().get(8) +"</td></tr> </table>  </center>");
            escritor.println("\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != htmlArchivo) {
                    htmlArchivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void generarHtmlGuia(String codigo){
        java.io.FileWriter htmlArchivo = null;
        java.io.PrintWriter escritor = null;
        Usuario usuarioTemp=null;
        Envio envioTemp=null;
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                usuarioTemp=usuario;
                break;
            }
        }

        for (Envio envio : usuarioTemp.getEnvios()) {
            if (Objects.equals(envio.getCodigoPaquete(), codigo)) {
                envioTemp=envio;
                break;
            }
        }

        try {
            htmlArchivo = new java.io.FileWriter("Guia"+dpiLoggeado+".html");
            escritor = new java.io.PrintWriter(htmlArchivo);
            escritor.println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "\n"
                    + "\n"
                    + "<title>Cotizacion</title> <script src=\"https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js\"></script>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body bgcolor=”#6CFC07”>\n"
                    + "\n"
                    + "<center>");
            escritor.println("<h1>" + "Guia De Envio: </h1></center>\n");
            escritor.println( "Origen:  " +envioTemp.getDatosGuia().get(1)+ "</h2>\n");
            escritor.println("Destino:  " +envioTemp.getDatosGuia().get(2)+ "</h2>\n");

            escritor.println("<center> <table> <tr> <th>No. Paquetes</th> <th>Peso Del Paquete</th> <th> Total Pago</th></tr>");
            escritor.println("<tr> <td>"+envioTemp.getDatosGuia().get(5)+"</td> <td>"+envioTemp.getDatosGuia().get(4)+"</td> <td>"+ envioTemp.getDatosGuia().get(7) +"</td></tr> </table>  </center>");
            escritor.println("<h1>Código Paquete: </h1>\n");
            escritor.println("<svg id=\"barcode\"></svg>\n" +
                    "\t<script>\n" +
                    "\t\tJsBarcode(\"#barcode\", \""+envioTemp.getDatosGuia().get(0)+"\", {\n" +
                    "\t\t\tformat: \"ean13\",\n" +
                    "\t\t\tdisplayValue: true,\n" +
                    "\t\t\ttextAlign: \"center\",\n" +
                    "\t\t\ttextPosition: \"bottom\",\n" +
                    "\t\t\tfontSize: 20,\n" +
                    "\t\t\tmarginTop: 20,\n" +
                    "\t\t\tmarginBottom: 20\n" +
                    "\t\t});\n" +
                    "\t</script>");
            escritor.println("\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != htmlArchivo) {
                    htmlArchivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static Boolean existeElPaquete(String codigo){
        Usuario usuarioTemp=null;
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                usuarioTemp=usuario;
                break;
            }
        }

        for (Envio envio : usuarioTemp.getEnvios()) {
            if (Objects.equals(envio.getCodigoPaquete(), codigo)) {
                return true;
            }
        }
        return false;

    }


    public static void generarHtmlCotizacion(String dirOrigen,String dirDestino,String numeroPaquetes, String peso,String totalServicio, String tipoPrecio){
        java.io.FileWriter htmlArchivo = null;
        java.io.PrintWriter escritor = null;
        try {
            htmlArchivo = new java.io.FileWriter("Cotizacion"+dpiLoggeado+".html");
            escritor = new java.io.PrintWriter(htmlArchivo);
            escritor.println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "\n"
                    + "\n"
                    + "<title>Cotizacion</title>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body bgcolor=”#6CFC07”>\n"
                    + "\n"
                    + "<center>");
            escritor.println("<h1>" + "Cotizacion Usuario Con DPI:  " +dpiLoggeado+ "</h1></center>\n");
            escritor.println("<h2>" + "Direccion Origen:  " +dirOrigen+ "</h2></center>\n");
            escritor.println("<h2>" + "Direccion Destino:  " +dirDestino+ "</h2></center>\n");
            escritor.println("<h2>" + "Paquertes A Enviar:  " +numeroPaquetes+ "</h2></center>\n");
            escritor.println("<h2>" + "Peso Total:  " +peso+ "</h2></center>\n");
            escritor.println("<h2>" + "Tipo Precio:  " +tipoPrecio+ "</h2></center>\n");
            escritor.println("<h2>" + "Total Servicio:  " +totalServicio+ "</h2></center>\n");
            escritor.println("\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != htmlArchivo) {
                    htmlArchivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void inicializarRegiones() {
        regiones.add(new Region("M","Metropolitana",35.00,25.00,0));
        regiones.add(new Region("NT","Norte",68.50,45.55,0));
        regiones.add(new Region("NO","Nororiente",58.68,35.48,0));
        regiones.add(new Region("SO","Suroriente",38.68,32.48,0));
        regiones.add(new Region("SOC","Suroccidente",34.00,29.00,0));
        regiones.add(new Region("NOC","Noroccidente",44.50,40.00,0));
    }

    public static void agregarEnvioRegion(String codigoDepartamento){
        String codigoRegion="";
        for (Departamento departamento : departamentos) {
            if (Objects.equals(departamento.getCodigoDepartamento(), codigoDepartamento)) {
                codigoRegion=departamento.getCodigoRegion();
                break;
            }
        }
        for (Region region : regiones) {
            if (Objects.equals(region.getCodigoRegion(), codigoRegion)) {
                region.setCantidadEnvios(region.getCantidadEnvios()+1);
            }
        }

    }

    public static boolean validarString(String str) {
        // Definir la expresión regular
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$";

        // Comprueba si el String coincide con la expresión regular
        return str.matches(regex);
    }

    public static boolean loginCorrecto(String correo,String pass){
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getCorreo(), correo) && Objects.equals(usuario.getContasenia(), pass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean elCorreoExiste(String correo){
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getCorreo(), correo) ) {
                // El correo existe, no debe permitir el registro
                return false;
            }
        }
        return true;
    }

    public static String devolverDpiLoggeado(String correo,String pass){
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getCorreo(), correo) && Objects.equals(usuario.getContasenia(), pass)) {
                return usuario.getDpi();
            }
        }
        return "";
    }

    public static String devolverNombreRegion(String codigoRegion){
        for (Region region : regiones) {
            if (Objects.equals(region.getCodigoRegion(), codigoRegion)) {
                return region.getNombreRegion();
            }
        }
        return "";
    }

    public static Region devolverLaRegion(String codigoRegion){
        for (Region region : regiones) {
            if (Objects.equals(region.getCodigoRegion(), codigoRegion)) {
                return region;
            }
        }
        return null;
    }

    public static void actualizarRegion(String codigoRegion,String atributo,String nuevaInfo){
        for (Region region : regiones) {
            if (Objects.equals(region.getCodigoRegion(), codigoRegion)) {
                switch (atributo){
                    case "Nombre":
                        region.setNombreRegion(nuevaInfo);
                        break;
                    case "PrecioNormal":
                        region.setPrecioNormal(Double.parseDouble(nuevaInfo));
                        break;
                    case "PrecioEspecial":
                        region.setPrecioEspecial(Double.parseDouble(nuevaInfo));
                        break;
                }

            }
        }
    }

    public static void actualizarDepartamento(String codigoDep,String nuevaInfo){
        for (Departamento dep : departamentos) {
            if (Objects.equals(dep.getNombreDepartamento(), codigoDep)) {
                dep.setNombreDepartamento(nuevaInfo);
            }
        }
    }

    public static void actualizarMunicipio(String nombreMun,String nuevaInfo){
        for (Municipio mun : municipios) {
            if (Objects.equals(mun.getNombreMunicipio(), nombreMun)) {
                mun.setNombreMunicipio(nuevaInfo);
            }
        }

    }

    public static void eliminarRegion(String codigoRegion){
        for (Region region : regiones) {
            if (Objects.equals(region.getCodigoRegion(), codigoRegion)) {
                regiones.remove(region);
                break;
            }
        }
        eliminarDepartamentosPertenecientesRegion(codigoRegion);
    }

    public static void eliminarDepartamento(String codigoDep){
        for (Departamento dep : departamentos) {
            if (Objects.equals(dep.getCodigoDepartamento(), codigoDep)) {
                departamentos.remove(dep);
                break;
            }
        }
        eliminarMunicipiosAsociadosDepartamento(codigoDep);
    }

    public static void eliminarMunicipio(String codigoMun){
        for (Municipio mun : municipios) {
            if (Objects.equals(mun.getNombreMunicipio(), codigoMun)) {
                municipios.remove(mun);
                break;
            }
        }
    }

    public static void eliminarDepartamentosPertenecientesRegion(String codigoRegion){
        boolean yaEliminamosTodos=false;
        while (!yaEliminamosTodos){
            int contadorDepsConRegionAsociada=0;
            for (Departamento departamento : departamentos) {
                if (Objects.equals(departamento.getCodigoRegion(), codigoRegion)) {
                    contadorDepsConRegionAsociada++;
                }
            }
            if(contadorDepsConRegionAsociada>0){
                for (Departamento departamento : departamentos) {
                    if (Objects.equals(departamento.getCodigoRegion(), codigoRegion)) {
                        eliminarMunicipiosAsociadosDepartamento(departamento.getCodigoDepartamento());
                        departamentos.remove(departamento);
                        break;
                    }
                }
            }else if(contadorDepsConRegionAsociada==0){
                yaEliminamosTodos=true;
            }
        }
    }

    public static void eliminarMunicipiosAsociadosDepartamento(String codigoDep){
        boolean yaEliminamosTodos=false;
        while (!yaEliminamosTodos){
            int contadorMunConDepAsociado=0;
            for (Municipio municipio : municipios) {
                if (Objects.equals(municipio.getCodigoDepartamento(), codigoDep)) {
                    contadorMunConDepAsociado++;
                }
            }
            if(contadorMunConDepAsociado>0){
                for (Municipio municipio : municipios) {
                    if (Objects.equals(municipio.getCodigoDepartamento(), codigoDep)) {
                        municipios.remove(municipio);
                        break;
                    }
                }
            }else if(contadorMunConDepAsociado==0){
                yaEliminamosTodos=true;
            }
        }
    }

    public static double  devolverPrecioRegion(String codigoDepartamento, String tipoPrecio){
        String codigoRegion="";
        for (Departamento departamento : departamentos) {
            if (Objects.equals(departamento.getCodigoDepartamento(), codigoDepartamento)) {
                codigoRegion=departamento.getCodigoRegion();
                break;
            }
        }
        for (Region region : regiones) {
            if (Objects.equals(region.getCodigoRegion(), codigoRegion)) {
                if(Objects.equals(tipoPrecio, "especial")){
                    return region.getPrecioEspecial();
                }else{
                    return region.getPrecioNormal();
                }
            }
        }
        return 0.00;
    }

    public static Object[][] devolverDatosRegiones(){
        Object[][] matrizRegiones;
        matrizRegiones = new Object[regiones.size()][5];
        for(int i=0;i<regiones.size();i++){
            matrizRegiones[i][0]=regiones.get(i).getCodigoRegion();
            matrizRegiones[i][1]=regiones.get(i).getNombreRegion();
            matrizRegiones[i][2]=regiones.get(i).getPrecioNormal();
            matrizRegiones[i][3]=regiones.get(i).getPrecioEspecial();
            matrizRegiones[i][4]=regiones.get(i).getCantidadEnvios();
        }



        return matrizRegiones;
    }
    public static Object[][] devolverDatosRegionesOrdenadas(){
        ArrayList<Region> regionesOrdenadas = new ArrayList<Region>();
        regionesOrdenadas = ordenarRegionesPorEnvios();
        Object[][] matrizRegiones;
        matrizRegiones = new Object[6][3];
        matrizRegiones[0][0]=regionesOrdenadas.get(0).getCodigoRegion();
        matrizRegiones[0][1]=regionesOrdenadas.get(0).getNombreRegion();
        matrizRegiones[0][2]=regionesOrdenadas.get(0).getCantidadEnvios();

        matrizRegiones[1][0]=regionesOrdenadas.get(1).getCodigoRegion();
        matrizRegiones[1][1]=regionesOrdenadas.get(1).getNombreRegion();
        matrizRegiones[1][2]=regionesOrdenadas.get(1).getCantidadEnvios();

        matrizRegiones[2][0]=regionesOrdenadas.get(2).getCodigoRegion();
        matrizRegiones[2][1]=regionesOrdenadas.get(2).getNombreRegion();
        matrizRegiones[2][2]=regionesOrdenadas.get(2).getCantidadEnvios();

        matrizRegiones[3][0]=regionesOrdenadas.get(3).getCodigoRegion();
        matrizRegiones[3][1]=regionesOrdenadas.get(3).getNombreRegion();
        matrizRegiones[3][2]=regionesOrdenadas.get(3).getCantidadEnvios();


        matrizRegiones[4][0]=regionesOrdenadas.get(4).getCodigoRegion();
        matrizRegiones[4][1]=regionesOrdenadas.get(4).getNombreRegion();
        matrizRegiones[4][2]=regionesOrdenadas.get(4).getCantidadEnvios();

        matrizRegiones[5][0]=regionesOrdenadas.get(5).getCodigoRegion();
        matrizRegiones[5][1]=regionesOrdenadas.get(5).getNombreRegion();
        matrizRegiones[5][2]=regionesOrdenadas.get(5).getCantidadEnvios();

        return matrizRegiones;
    }

    public static Object[][] devolverDatosUsuariosOrdenadas(){
        ArrayList<Usuario> usuariosOrdenados = new ArrayList<>();
        usuariosOrdenados = ordenarUsuariosPorEnvios();
        Object[][] matrizUsuarios;
        matrizUsuarios = new Object[usuariosOrdenados.size()][3];
        int n = usuariosOrdenados.size();
        for (int i = 0; i < n - 1; i++) {
            matrizUsuarios[i][0]=usuariosOrdenados.get(i).getNombre();
            matrizUsuarios[i][1]=usuariosOrdenados.get(i).getDpi();
            matrizUsuarios[i][2]=usuariosOrdenados.get(i).getPaquetesEnviados();
        }

        return matrizUsuarios;
    }

    public static String[] devolverCodigosRegiones(){
        // declaración un arreglo para almacenar los codigos
        String[] codigos = new String[regiones.size()];
        for (int i = 0; i < regiones.size(); i++) {
            Region region = regiones.get(i);
            codigos[i] = region.getCodigoRegion();
        }
        return codigos;
    }

    public static String[] devolverCodigosDepartamentos(){
        // declaración un arreglo para almacenar los codigos
        String[] codigos = new String[departamentos.size()];
        for (int i = 0; i < departamentos.size(); i++) {
            Departamento departamento = departamentos.get(i);
            codigos[i] = departamento.getCodigoDepartamento();
        }
        return codigos;
    }
    public static String[] devolverCodigosMunicipios(){
        // declaración un arreglo para almacenar los codigos
        String[] codigos = new String[municipios.size()];
        for (int i = 0; i < municipios.size(); i++) {
            Municipio municipio = municipios.get(i);
            codigos[i] = municipio.getNombreMunicipio();
        }
        return codigos;
    }

    public static Object[][] devolverTarjetasUsuario(String dpi){
        ArrayList<Tarjeta> tarjetasCliente = new ArrayList<Tarjeta>();
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpi)) {
                tarjetasCliente=usuario.getTarjetas();
                break;
            }
        }
        int rows = tarjetasCliente.size();
        Object[][] matrizTarjetas=new Object[rows][4];
        for (int i = 0; i < tarjetasCliente.size(); i++) {
            Tarjeta tarjeta = tarjetasCliente.get(i);
            matrizTarjetas[i][0] = tarjeta.getNombreDeLaTarjeta();
            matrizTarjetas[i][1]="XXXXXXXX"+tarjeta.getNumeroTarjeta().substring(tarjeta.getNumeroTarjeta().length() - 4);
            matrizTarjetas[i][2]=tarjeta.getFechaVencimiento();
            matrizTarjetas[i][3]=tarjeta.getTipoTarjeta();
        }
        return matrizTarjetas;
    }

    public static ArrayList<Region> ordenarRegionesPorEnvios() {
        // ArrayList llamada "regionesOrdenadas" que contiene objetos de tipo Region
        ArrayList<Region> regionesOrdenadas = new ArrayList<Region>();
        regionesOrdenadas = regiones;
        // Ordenamiento la lista de regionesOrdenadas utilizando el algoritmo de ordenamiento de burbuja
        int n = regionesOrdenadas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (regionesOrdenadas.get(j).getCantidadEnvios() > regionesOrdenadas.get(j + 1).getCantidadEnvios()) {
                    Region temp = regionesOrdenadas.get(j);
                    regionesOrdenadas.set(j, regionesOrdenadas.get(j + 1));
                    regionesOrdenadas.set(j + 1, temp);
                }
            }
        }
        return regionesOrdenadas;
    }

    public static ArrayList<Usuario> ordenarUsuariosPorEnvios() {
        // ArrayList llamada "usuariosOrdenados" que contiene objetos de tipo Usuario
        ArrayList<Usuario> usuariosOrdenados = new ArrayList<Usuario>();
        usuariosOrdenados = usuarios;
        // Ordenamiento la lista de usuariosOrdenados utilizando el algoritmo de ordenamiento de burbuja
        int n = usuariosOrdenados.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (usuariosOrdenados.get(j).getPaquetesEnviados() > usuariosOrdenados.get(j + 1).getPaquetesEnviados()) {
                    Usuario temp = usuariosOrdenados.get(j);
                    usuariosOrdenados.set(j, usuariosOrdenados.get(j + 1));
                    usuariosOrdenados.set(j + 1, temp);
                }
            }
        }
        return usuariosOrdenados;
    }


    public static Object[][] devolverEnviosUsuario(String dpi){
        ArrayList<Envio> enviosCliente = new ArrayList<Envio>();
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpi)) {
                enviosCliente=usuario.getEnvios();
                break;
            }
        }
        int rows = enviosCliente.size();
        Object[][] matrizEnvios=new Object[rows][5];
        for (int i = 0; i < enviosCliente.size(); i++) {
            Envio envio = enviosCliente.get(i);
            matrizEnvios[i][0]=envio.getCodigoPaquete();
            matrizEnvios[i][1]=envio.getTipoServicio();
            matrizEnvios[i][2]=envio.getDestinatario();
            matrizEnvios[i][3]=envio.getTotalEnvio();
            matrizEnvios[i][4]=envio.getTipoPago();
        }
        return matrizEnvios;
    }

    public static String[] devolverNumerosTarjetasUsuario(String dpi){
        ArrayList<Tarjeta> tarjetasCliente = new ArrayList<Tarjeta>();
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpi)) {
                tarjetasCliente=usuario.getTarjetas();
                break;
            }
        }
        // declaración un arreglo para almacenar los codigos
        String[] codigos = new String[tarjetasCliente.size()+1];
        codigos[0]="No-Aplica";
        for (int i = 0; i < tarjetasCliente.size(); i++) {
            Tarjeta tarjeta = tarjetasCliente.get(i);
            codigos[i+1] = "XXXXXXXX"+tarjeta.getNombreDeLaTarjeta().substring(tarjeta.getNombreDeLaTarjeta().length() - 4);;
        }
        return codigos;

    }

    public static String[] devolverKioscos( ) {

        // declaración un arreglo para almacenar los codigos
        String[] codigos = new String[kioscos.size()+1];
        codigos[0]="No-Aplica";
        for (int i = 0; i < kioscos.size(); i++) {
            codigos[i+1]=kioscos.get(i).getCodigoKiosco();
        }
        return codigos;

    }

    public static String[] devolverListaDatosFacturacion(String dpi){
        ArrayList<DatoFacturacion> datosFacCliente = new ArrayList<DatoFacturacion>();
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpi)) {
                datosFacCliente=usuario.getDatosFacturacion();
                break;
            }
        }
        // declaración un arreglo para almacenar los codigos
        String[] codigos = new String[datosFacCliente.size()];
        for (int i = 0; i < datosFacCliente.size(); i++) {
            DatoFacturacion datoFac = datosFacCliente.get(i);
            codigos[i] = datoFac.getNombreCompleto()+"--"+datoFac.getDireccion();
        }
        return codigos;

    }

    public static Object[][] devolverDatosFacturacionUsuario(String dpi){
        ArrayList<DatoFacturacion> datosFacturacionCliente = new ArrayList<DatoFacturacion>();
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getDpi(), dpi)) {
                datosFacturacionCliente=usuario.getDatosFacturacion();
                break;
            }
        }
        int rows = datosFacturacionCliente.size();
        Object[][] matrizDatosFacturacion=new Object[rows][3];
        for (int i = 0; i < datosFacturacionCliente.size(); i++) {
            DatoFacturacion datoFacturacion = datosFacturacionCliente.get(i);
            matrizDatosFacturacion[i][0] = datoFacturacion.getNombreCompleto();
            matrizDatosFacturacion[i][1]=datoFacturacion.getDireccion();
            matrizDatosFacturacion[i][2]=datoFacturacion.getNit();
        }
        return matrizDatosFacturacion;
    }


    // Interfaz de inicio de sesión
    public static void inicioDeSesion() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameInicioDeSesion = new JFrame("Usac-Delivery");
        frameInicioDeSesion.setLayout(null);
        frameInicioDeSesion.setVisible(true);
        frameInicioDeSesion.setResizable(true);
        frameInicioDeSesion.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameInicioDeSesion.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameInicioDeSesion.setSize(700,550);



        JLabel tituloEmpresa = new JLabel("USAC-DELIVERY");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameInicioDeSesion.add(tituloEmpresa);

        JLabel labelCorreo = new JLabel("Correo: ");
        labelCorreo.setLayout(null);
        labelCorreo.setVisible(true);
        labelCorreo.setForeground(Color.BLACK);
        labelCorreo.setBounds(10,150,250,30);
        labelCorreo.setFont(fuenteDeTexto);
        frameInicioDeSesion.add(labelCorreo);


        JLabel labelContra = new JLabel("Contraseña: ");
        labelContra.setLayout(null);
        labelContra.setVisible(true);
        labelContra.setForeground(Color.BLACK);
        labelContra.setBounds(10,250,300,30);
        labelContra.setFont(fuenteDeTexto);
        frameInicioDeSesion.add(labelContra);



        JTextField campoCorreo = new JTextField();
        campoCorreo.setLayout(null);
        campoCorreo.setVisible(true);
        campoCorreo.setBounds(240,145,350,40);
        campoCorreo.setFont(fuenteDeTexto);
        frameInicioDeSesion.add(campoCorreo);

        JPasswordField campoContra = new JPasswordField();
        campoContra.setBounds(240,245,350,40);
        campoContra.setVisible(true);
        campoContra.setLayout(null);
        campoContra.setFont(fuenteDeTexto);
        frameInicioDeSesion.add(campoContra);

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setLayout(null);
        botonIniciarSesion.setVisible(true);
        botonIniciarSesion.setBounds(215, 325, 300, 60);
        botonIniciarSesion.setFont(fuenteDeTexto);
        botonIniciarSesion.setBackground(Color.WHITE);
        botonIniciarSesion.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(Objects.equals(campoCorreo.getText(), correoAdmin) && Objects.equals(campoContra.getText(), passAdmin)){
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Bienvenido Admin</p></html>" );
                    frameInicioDeSesion.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(loginCorrecto(campoCorreo.getText(),campoContra.getText())){
                    dpiLoggeado=devolverDpiLoggeado(campoCorreo.getText(),campoContra.getText());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Bienvenido Usuario</p></html>" );
                    frameInicioDeSesion.dispose();
                    try {
                        vistaUsuario();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Credenciales Incorrectas</p></html>" );
                }


            }
        });
        frameInicioDeSesion.add(botonIniciarSesion);


        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setLayout(null);
        botonRegistrarse.setVisible(true);
        botonRegistrarse.setBounds(215, 425, 300, 60);
        botonRegistrarse.setFont(fuenteDeTexto);
        botonRegistrarse.setBackground(Color.WHITE);
        botonRegistrarse.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameInicioDeSesion.dispose();
                try {
                    registroDeUsuarios();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        frameInicioDeSesion.add(botonRegistrarse);
        frameInicioDeSesion.repaint();
    }

    //Interfaz Registro De Usuarios
    public static void registroDeUsuarios() throws IOException {
        //Fuente utilizad
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,18);

        // Creación del frame
        JFrame registroDeUsuario = new JFrame("USAC-DELIVERY");
        registroDeUsuario.setLayout(null);
        registroDeUsuario.setVisible(true);
        registroDeUsuario.setResizable(true);
        registroDeUsuario.getContentPane().setBackground(Color.LIGHT_GRAY);
        registroDeUsuario.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        int h= sizeScreen.height;
        int w= sizeScreen.width;
        registroDeUsuario.setSize(1000,675);
        registroDeUsuario.setLocation(w/4,h/4);



        JLabel labelTitulo = new JLabel("Usac Delivery");
        labelTitulo.setLayout(null);
        labelTitulo.setVisible(true);
        labelTitulo.setForeground(Color.BLACK);
        labelTitulo.setBounds(240,10,200,30);
        labelTitulo.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelTitulo);


        JLabel labelCorreo = new JLabel("Correo: ");
        labelCorreo.setLayout(null);
        labelCorreo.setVisible(true);
        labelCorreo.setForeground(Color.BLACK);
        labelCorreo.setBounds(20,60,100,30);
        labelCorreo.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelCorreo);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setLayout(null);
        campoCorreo.setVisible(true);
        campoCorreo.setBounds(150,60,400,30);
        campoCorreo.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoCorreo);

        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setLayout(null);
        labelNombre.setVisible(true);
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setBounds(20,100,100,30);
        labelNombre.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setLayout(null);
        campoNombre.setVisible(true);
        campoNombre.setBounds(150,100,400,30);
        campoNombre.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoNombre);

        JLabel labelApellido = new JLabel("Apellido: ");
        labelApellido.setLayout(null);
        labelApellido.setVisible(true);
        labelApellido.setForeground(Color.BLACK);
        labelApellido.setBounds(20,140,100,30);
        labelApellido.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelApellido);

        JTextField campoApellido = new JTextField();
        campoApellido.setLayout(null);
        campoApellido.setVisible(true);
        campoApellido.setBounds(150,140,400,30);
        campoApellido.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoApellido);

        JLabel labelContra = new JLabel("Contraseña: ");
        labelContra.setLayout(null);
        labelContra.setVisible(true);
        labelContra.setForeground(Color.BLACK);
        labelContra.setBounds(20,180,200,30);
        labelContra.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelContra);

        JTextField campoContra = new JTextField();
        campoContra.setLayout(null);
        campoContra.setVisible(true);
        campoContra.setBounds(150,180,400,30);
        campoContra.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoContra);

        JLabel labelDpi = new JLabel("Dpi: ");
        labelDpi.setLayout(null);
        labelDpi.setVisible(true);
        labelDpi.setForeground(Color.BLACK);
        labelDpi.setBounds(20,220,100,30);
        labelDpi.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelDpi);

        JTextField campoDpi = new JTextField();
        campoDpi.setLayout(null);
        campoDpi.setVisible(true);
        campoDpi.setBounds(150,220,400,30);
        campoDpi.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoDpi);

        JLabel labelFecha = new JLabel("Nacimiento: ");
        labelFecha.setLayout(null);
        labelFecha.setVisible(true);
        labelFecha.setForeground(Color.BLACK);
        labelFecha.setBounds(20,260,130,30);
        labelFecha.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelFecha);

        JLabel labelDia = new JLabel("dd ");
        labelDia.setLayout(null);
        labelDia.setVisible(true);
        labelDia.setForeground(Color.BLACK);
        labelDia.setBounds(150,260,30,30);
        labelDia.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelDia);

        JTextField campoDia = new JTextField();
        campoDia.setLayout(null);
        campoDia.setVisible(true);
        campoDia.setBounds(180,260,30,30);
        campoDia.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoDia);

        JLabel labelMes = new JLabel("mm ");
        labelMes.setLayout(null);
        labelMes.setVisible(true);
        labelMes.setForeground(Color.BLACK);
        labelMes.setBounds(260,260,40,30);
        labelMes.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelMes);

        JTextField campoMes = new JTextField();
        campoMes.setLayout(null);
        campoMes.setVisible(true);
        campoMes.setBounds(290,260,30,30);
        campoMes.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoMes);

        JLabel labelAnio = new JLabel("yy ");
        labelAnio.setLayout(null);
        labelAnio.setVisible(true);
        labelAnio.setForeground(Color.BLACK);
        labelAnio.setBounds(370,260,40,30);
        labelAnio.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelAnio);

        JTextField campoAnio = new JTextField();
        campoAnio.setLayout(null);
        campoAnio.setVisible(true);
        campoAnio.setBounds(400,260,100,30);
        campoAnio.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoAnio);

        JLabel labelGenero = new JLabel("Género: ");
        labelGenero.setLayout(null);
        labelGenero.setVisible(true);
        labelGenero.setForeground(Color.BLACK);
        labelGenero.setBounds(20,300,100,30);
        labelGenero.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelGenero);

        String[] opcionesGenero = {"Masculino", "Femenino"};
        JComboBox<String> comboboxGenero = new JComboBox<>(opcionesGenero);
        comboboxGenero.setLayout(null);
        comboboxGenero.setVisible(true);
        comboboxGenero.setBounds(150,300,400,30);
        comboboxGenero.setFont(fuenteDeTexto);
        registroDeUsuario.add(comboboxGenero);

        JLabel labelNacionalidad = new JLabel("Nacionalidad: ");
        labelNacionalidad.setLayout(null);
        labelNacionalidad.setVisible(true);
        labelNacionalidad.setForeground(Color.BLACK);
        labelNacionalidad.setBounds(20,340,130,30);
        labelNacionalidad.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelNacionalidad);

        String[] opcionesPaises = {"Guatemala", "Mexico","El Salvador","Honduras","Panama","Costa Rica","Otro"};
        JComboBox<String> comboboxPais = new JComboBox<>(opcionesPaises);
        comboboxPais.setLayout(null);
        comboboxPais.setVisible(true);
        comboboxPais.setBounds(150,340,400,30);
        comboboxPais.setFont(fuenteDeTexto);
        registroDeUsuario.add(comboboxPais);

        JLabel labelSobreNombre = new JLabel("Alias: ");
        labelSobreNombre.setLayout(null);
        labelSobreNombre.setVisible(true);
        labelSobreNombre.setForeground(Color.BLACK);
        labelSobreNombre.setBounds(20,380,100,30);
        labelSobreNombre.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelSobreNombre);

        JTextField campoSobreNombre = new JTextField();
        campoSobreNombre.setLayout(null);
        campoSobreNombre.setVisible(true);
        campoSobreNombre.setBounds(150,380,400,30);
        campoSobreNombre.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoSobreNombre);

        JLabel labelTelefono = new JLabel("Teléfono: ");
        labelTelefono.setLayout(null);
        labelTelefono.setVisible(true);
        labelTelefono.setForeground(Color.BLACK);
        labelTelefono.setBounds(20,420,100,30);
        labelTelefono.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelTelefono);

        JTextField campoTelefono = new JTextField();
        campoTelefono.setLayout(null);
        campoTelefono.setVisible(true);
        campoTelefono.setBounds(150,420,400,30);
        campoTelefono.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoTelefono);

        JLabel labelRol = new JLabel("Rol: ");
        labelRol.setLayout(null);
        labelRol.setVisible(true);
        labelRol.setForeground(Color.BLACK);
        labelRol.setBounds(20,460,100,30);
        labelRol.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelRol);

        String[] opcionesRol = {"Individual", "Empresarial","Kiosco"};
        JComboBox<String> comboboxRol = new JComboBox<>(opcionesRol);
        comboboxRol.setLayout(null);
        comboboxRol.setVisible(true);
        comboboxRol.setBounds(150,460,160,30);
        comboboxRol.setFont(fuenteDeTexto);
        registroDeUsuario.add(comboboxRol);

        JLabel labelKiosco = new JLabel("Kiosco: ");
        labelKiosco.setLayout(null);
        labelKiosco.setVisible(true);
        labelKiosco.setForeground(Color.BLACK);
        labelKiosco.setBounds(320,460,100,30);
        labelKiosco.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelKiosco);


        JComboBox<String> comboboxKiosco = new JComboBox<>(devolverKioscos());
        comboboxKiosco.setLayout(null);
        comboboxKiosco.setVisible(true);
        comboboxKiosco.setBounds(430,460,160,30);
        comboboxKiosco.setFont(fuenteDeTexto);
        registroDeUsuario.add(comboboxKiosco);

        JLabel labelFotografia = new JLabel("Fotografía: ");
        labelFotografia.setLayout(null);
        labelFotografia.setVisible(true);
        labelFotografia.setForeground(Color.BLACK);
        labelFotografia.setBounds(20,500,100,30);
        labelFotografia.setFont(fuenteDeTexto);
        registroDeUsuario.add(labelFotografia);

        JTextField campoFotografia = new JTextField();
        campoFotografia.setLayout(null);
        campoFotografia.setVisible(true);
        campoFotografia.setEditable(false);
        campoFotografia.setBounds(150,500,300,30);
        campoFotografia.setFont(fuenteDeTexto);
        registroDeUsuario.add(campoFotografia);

        JButton cargarImagenButton = new JButton("Cargar imagen");
        cargarImagenButton.setLayout(null);
        cargarImagenButton.setVisible(true);
        cargarImagenButton.setBounds(475, 500, 120, 25);
        registroDeUsuario.add(cargarImagenButton);

        JLabel imagenLabel2 = new JLabel();
        imagenLabel2.setBounds(575, 200, 250, 250);
        registroDeUsuario.add(imagenLabel2);

        cargarImagenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    campoFotografia.setText(selectedFile.getAbsolutePath());
                    ImageIcon imageIcon = null;
                    try {
                        imageIcon = new ImageIcon(new ImageIcon(selectedFile.toURL()).getImage().getScaledInstance(imagenLabel2.getWidth(), imagenLabel2.getHeight(), Image.SCALE_DEFAULT));
                    } catch (java.net.MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                    imagenLabel2.setIcon(imageIcon);
                }
            }
        });


        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setLayout(null);
        botonRegistrarse.setVisible(true);
        botonRegistrarse.setBounds(400, 540, 120, 60);
        botonRegistrarse.setBackground(Color.WHITE);
        botonRegistrarse.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                if (!Objects.equals(campoCorreo.getText(), "") && !Objects.equals(campoNombre.getText(),"") && !Objects.equals(campoApellido.getText(), "") && !Objects.equals(campoContra.getText(),"") && !Objects.equals(campoDpi.getText(), "") && !Objects.equals(campoDia.getText(),"") && !Objects.equals(campoMes.getText(), "") && !Objects.equals(campoAnio.getText(),"")&& !Objects.equals(campoSobreNombre.getText(), "") && !Objects.equals(campoTelefono.getText(),"")){
                    if (validarString(campoContra.getText())){
                        ArrayList<Tarjeta> misTarjetas = new ArrayList<>();
                        ArrayList<DatoFacturacion> misDatosFacturacion = new ArrayList<>();
                        ArrayList<Envio> misEnvios = new ArrayList<>();
                        if(elCorreoExiste(campoCorreo.getText())){
                            usuarios.add(new Usuario(campoCorreo.getText(),campoContra.getText(),comboboxRol.getSelectedItem().toString(),campoNombre.getText(),campoApellido.getText(),campoDpi.getText(),campoDia.getText()+"/"+campoMes.getText()+"/"+campoAnio.getText(),comboboxGenero.getSelectedItem().toString(),comboboxPais.getSelectedItem().toString(),campoSobreNombre.getText(),campoTelefono.getText(),campoFotografia.getText(),0,misTarjetas,misDatosFacturacion,misEnvios));
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Usuario Registrado Con Éxito</p></html>" );
                        }else{
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:10px;\">El correo ya está utilizado en otra cuenta!!</p></html>" );
                        }
                        registroDeUsuario.dispose();
                        try {
                            inicioDeSesion();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">La contraseña debe tener al menos un número, minúscula, mayúscula y simbolo especial!!</p></html>" );
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos para registrarte!!</p></html>" );
                }
            }
        });
        registroDeUsuario.add(botonRegistrarse);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setLayout(null);
        botonSalir.setVisible(true);
        botonSalir.setBounds(170, 540, 100, 60);
        botonSalir.setBackground(Color.WHITE);
        botonSalir.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                registroDeUsuario.dispose();
                try {
                    inicioDeSesion();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        registroDeUsuario.add(botonSalir);


        registroDeUsuario.repaint();
    }

    // Interfaz Vista Admin
    public static void vistaAdmin() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameVistaAdmin = new JFrame("Usac-Delivery");
        frameVistaAdmin.setLayout(null);
        frameVistaAdmin.setVisible(true);
        frameVistaAdmin.setResizable(true);
        frameVistaAdmin.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameVistaAdmin.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameVistaAdmin.setSize(700,700);



        JLabel tituloEmpresa = new JLabel("USAC-DELIVERY");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameVistaAdmin.add(tituloEmpresa);


        JButton botonManejarKioscos = new JButton("Manejar Kioscos");
        botonManejarKioscos.setLayout(null);
        botonManejarKioscos.setVisible(true);
        botonManejarKioscos.setBounds(215, 80, 300, 60);
        botonManejarKioscos.setFont(fuenteDeTexto);
        botonManejarKioscos.setBackground(Color.WHITE);
        botonManejarKioscos.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaAdmin.dispose();
                try {
                    manejoDeKioscos();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        frameVistaAdmin.add(botonManejarKioscos);


        JButton botonRegionesYPrceios = new JButton("Regiones Y Precios");
        botonRegionesYPrceios.setLayout(null);
        botonRegionesYPrceios.setVisible(true);
        botonRegionesYPrceios.setBounds(185, 180, 360, 60);
        botonRegionesYPrceios.setFont(fuenteDeTexto);
        botonRegionesYPrceios.setBackground(Color.WHITE);
        botonRegionesYPrceios.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaAdmin.dispose();
                try {
                    manejoRegionesPrecios();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameVistaAdmin.add(botonRegionesYPrceios);

        JButton botonDepartamentos = new JButton("Departamentos");
        botonDepartamentos.setLayout(null);
        botonDepartamentos.setVisible(true);
        botonDepartamentos.setBounds(215, 280, 300, 60);
        botonDepartamentos.setFont(fuenteDeTexto);
        botonDepartamentos.setBackground(Color.WHITE);
        botonDepartamentos.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaAdmin.dispose();
                try {
                    manejoDeDepartamentos();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameVistaAdmin.add(botonDepartamentos);

        JButton botonMunicipios = new JButton("Municipios");
        botonMunicipios.setLayout(null);
        botonMunicipios.setVisible(true);
        botonMunicipios.setBounds(215, 380, 300, 60);
        botonMunicipios.setFont(fuenteDeTexto);
        botonMunicipios.setBackground(Color.WHITE);
        botonMunicipios.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaAdmin.dispose();
                try {
                    manejoDeMunicipios();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameVistaAdmin.add(botonMunicipios);

        JButton botonReportes = new JButton("Reportes");
        botonReportes.setLayout(null);
        botonReportes.setVisible(true);
        botonReportes.setBounds(215, 480, 300, 60);
        botonReportes.setFont(fuenteDeTexto);
        botonReportes.setBackground(Color.WHITE);
        botonReportes.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaAdmin.dispose();
                try {
                    manejoReportes();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameVistaAdmin.add(botonReportes);

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setLayout(null);
        botonCerrarSesion.setVisible(true);
        botonCerrarSesion.setBounds(215, 580, 300, 60);
        botonCerrarSesion.setFont(fuenteDeTexto);
        botonCerrarSesion.setBackground(Color.WHITE);
        botonCerrarSesion.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaAdmin.dispose();
                try {
                    inicioDeSesion();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        frameVistaAdmin.add(botonCerrarSesion);

        frameVistaAdmin.repaint();
    }

    // Interfaz manejo de kioscos
    public static void manejoDeKioscos() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameManejoDeKioscos = new JFrame("Usac-Delivery");
        frameManejoDeKioscos.setLayout(null);
        frameManejoDeKioscos.setVisible(true);
        frameManejoDeKioscos.setResizable(true);
        frameManejoDeKioscos.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoDeKioscos.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoDeKioscos.setSize(700,550);



        JLabel tituloEmpresa = new JLabel("USAC-DELIVERY");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(tituloEmpresa);

        JLabel labelCodigo = new JLabel("Código: ");
        labelCodigo.setLayout(null);
        labelCodigo.setVisible(true);
        labelCodigo.setForeground(Color.BLACK);
        labelCodigo.setBounds(10,150,250,30);
        labelCodigo.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(labelCodigo);

        JTextField campoCodigo = new JTextField();
        campoCodigo.setLayout(null);
        campoCodigo.setVisible(true);
        campoCodigo.setBounds(190,150,350,40);
        campoCodigo.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(campoCodigo);


        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setLayout(null);
        labelNombre.setVisible(true);
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setBounds(10,250,300,30);
        labelNombre.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(190,250,350,40);
        campoNombre.setVisible(true);
        campoNombre.setLayout(null);
        campoNombre.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(campoNombre);

        JLabel labelRegion = new JLabel("Región: ");
        labelRegion.setLayout(null);
        labelRegion.setVisible(true);
        labelRegion.setForeground(Color.BLACK);
        labelRegion.setBounds(10,350,300,30);
        labelRegion.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(labelRegion);

        JComboBox<String> comboboxRegion = new JComboBox<>(devolverCodigosRegiones());
        comboboxRegion.setLayout(null);
        comboboxRegion.setVisible(true);
        comboboxRegion.setBounds(190,350,350,30);
        comboboxRegion.setFont(fuenteDeTexto);
        frameManejoDeKioscos.add(comboboxRegion);

        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(80, 425, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoDeKioscos.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoDeKioscos.add(botonRegresar);


        JButton botonAgregarKiosco = new JButton("Agregar");
        botonAgregarKiosco.setLayout(null);
        botonAgregarKiosco.setVisible(true);
        botonAgregarKiosco.setBounds(305, 425, 300, 60);
        botonAgregarKiosco.setFont(fuenteDeTexto);
        botonAgregarKiosco.setBackground(Color.WHITE);
        botonAgregarKiosco.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoNombre.getText(), "") && !Objects.equals(campoCodigo.getText(), "")){
                    kioscos.add(new Kiosco(campoCodigo.getText(),campoNombre.getText(),comboboxRegion.getSelectedItem().toString()));
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Kiosco Registrado con éxito!!</p></html>" );
                    frameManejoDeKioscos.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos del kiosco para registralo!!</p></html>" );
                }
            }
        });
        frameManejoDeKioscos.add(botonAgregarKiosco);

        frameManejoDeKioscos.repaint();
    }

    // Interfaz manejo de departamentos
    public static void manejoDeDepartamentos() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        Font fuenteDeTexto2 =new Font("SansSerif",Font.BOLD,12);
        // Creación del frame
        JFrame frameManejoDepartamentos = new JFrame("Usac-Delivery");
        frameManejoDepartamentos.setLayout(null);
        frameManejoDepartamentos.setVisible(true);
        frameManejoDepartamentos.setResizable(true);
        frameManejoDepartamentos.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoDepartamentos.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoDepartamentos.setSize(900,800);



        JLabel tituloEmpresa = new JLabel("USAC-DELIVERY");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(tituloEmpresa);

        JLabel labelRegion = new JLabel("Region: ");
        labelRegion.setLayout(null);
        labelRegion.setVisible(true);
        labelRegion.setForeground(Color.BLACK);
        labelRegion.setBounds(10,150,250,30);
        labelRegion.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(labelRegion);

        JComboBox<String> comboboxRegion = new JComboBox<>(devolverCodigosRegiones());
        comboboxRegion.setLayout(null);
        comboboxRegion.setVisible(true);
        comboboxRegion.setBounds(190,150,350,30);
        comboboxRegion.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(comboboxRegion);

        JLabel labelCodigoDepartamento = new JLabel("Código: ");
        labelCodigoDepartamento.setLayout(null);
        labelCodigoDepartamento.setVisible(true);
        labelCodigoDepartamento.setForeground(Color.BLACK);
        labelCodigoDepartamento.setBounds(10,250,250,30);
        labelCodigoDepartamento.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(labelCodigoDepartamento);

        JTextField campoCodigoDepartamento = new JTextField();
        campoCodigoDepartamento.setLayout(null);
        campoCodigoDepartamento.setVisible(true);
        campoCodigoDepartamento.setBounds(190,250,350,40);
        campoCodigoDepartamento.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(campoCodigoDepartamento);


        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setLayout(null);
        labelNombre.setVisible(true);
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setBounds(10,350,300,30);
        labelNombre.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(190,350,350,40);
        campoNombre.setVisible(true);
        campoNombre.setLayout(null);
        campoNombre.setFont(fuenteDeTexto);
        frameManejoDepartamentos.add(campoNombre);


        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(230, 700, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoDepartamentos.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoDepartamentos.add(botonRegresar);


        JButton botonAgregarDepartamento = new JButton("Agregar");
        botonAgregarDepartamento.setLayout(null);
        botonAgregarDepartamento.setVisible(true);
        botonAgregarDepartamento.setBounds(190, 425, 300, 60);
        botonAgregarDepartamento.setFont(fuenteDeTexto);
        botonAgregarDepartamento.setBackground(Color.WHITE);
        botonAgregarDepartamento.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoNombre.getText(), "") && !Objects.equals(campoCodigoDepartamento.getText(), "")){
                    Region unaRegion=devolverLaRegion(comboboxRegion.getSelectedItem().toString());
                    departamentos.add(new Departamento(comboboxRegion.getSelectedItem().toString(),devolverNombreRegion(comboboxRegion.getSelectedItem().toString()),unaRegion.getPrecioNormal(),unaRegion.getPrecioEspecial(),unaRegion.getCantidadEnvios(),campoCodigoDepartamento.getText(),campoNombre.getText()));
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Departamento Registrado con éxito!!</p></html>" );
                    frameManejoDepartamentos.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos del departamento para registralo!!</p></html>" );
                }
            }
        });
        frameManejoDepartamentos.add(botonAgregarDepartamento);

        JLabel labelCodigoRegion = new JLabel("Cod. Dep:");
        labelCodigoRegion.setLayout(null);
        labelCodigoRegion.setVisible(true);
        labelCodigoRegion.setForeground(Color.BLACK);
        labelCodigoRegion.setBounds(10,510,100,60);
        labelCodigoRegion.setFont(fuenteDeTexto2);
        frameManejoDepartamentos.add(labelCodigoRegion);

        JComboBox<String> combobxDepartamento = new JComboBox<>(devolverCodigosDepartamentos());
        combobxDepartamento.setLayout(null);
        combobxDepartamento.setVisible(true);
        combobxDepartamento.setBounds(100,525,150,30);
        combobxDepartamento.setFont(fuenteDeTexto2);
        frameManejoDepartamentos.add(combobxDepartamento);

        JLabel labelRenovacion = new JLabel("Atributo a Renovar:");
        labelRenovacion.setLayout(null);
        labelRenovacion.setVisible(true);
        labelRenovacion.setForeground(Color.BLACK);
        labelRenovacion.setBounds(260,510,150,60);
        labelRenovacion.setFont(fuenteDeTexto2);
        frameManejoDepartamentos.add(labelRenovacion);

        String[] opcionesAtributos = {"Nombre"};
        JComboBox<String> comboboxRenovar = new JComboBox<>(opcionesAtributos);
        comboboxRenovar.setLayout(null);
        comboboxRenovar.setVisible(true);
        comboboxRenovar.setBounds(400,525,150,30);
        comboboxRenovar.setFont(fuenteDeTexto2);
        frameManejoDepartamentos.add(comboboxRenovar);

        JLabel datoRenovado = new JLabel("Dato Renovado:");
        datoRenovado.setLayout(null);
        datoRenovado.setVisible(true);
        datoRenovado.setForeground(Color.BLACK);
        datoRenovado.setBounds(600,525,100,30);
        datoRenovado.setFont(fuenteDeTexto2);
        frameManejoDepartamentos.add(datoRenovado);

        JTextField campoRenovar = new JTextField();
        campoRenovar.setLayout(null);
        campoRenovar.setVisible(true);
        campoRenovar.setForeground(Color.BLACK);
        campoRenovar.setBounds(700,525,150,30);
        campoRenovar.setFont(fuenteDeTexto2);
        frameManejoDepartamentos.add(campoRenovar);



        JButton botonAcualizar = new JButton("Actualizar");
        botonAcualizar.setLayout(null);
        botonAcualizar.setVisible(true);
        botonAcualizar.setBounds(100, 580, 200, 60);
        botonAcualizar.setFont(fuenteDeTexto);
        botonAcualizar.setBackground(Color.WHITE);
        botonAcualizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoRenovar.getText(), "")){
                    actualizarDepartamento(combobxDepartamento.getSelectedItem().toString(),campoRenovar.getText());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Departamento Actualizado Con Éxito!</p></html>" );
                    frameManejoDepartamentos.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:10px;\">Debes llenar todos loc campos, si deseas actualizar datos!!</p></html>" );
                }
            }
        });
        frameManejoDepartamentos.add(botonAcualizar);

        JButton botonEliminar = new JButton("Eliminar D.");
        botonEliminar.setLayout(null);
        botonEliminar.setVisible(true);
        botonEliminar.setBounds(400, 580, 200, 60);
        botonEliminar.setFont(fuenteDeTexto);
        botonEliminar.setBackground(Color.WHITE);
        botonEliminar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                eliminarDepartamento(combobxDepartamento.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Departamento Eliminado Con Éxito!</p></html>" );
                frameManejoDepartamentos.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoDepartamentos.add(botonEliminar);

        frameManejoDepartamentos.repaint();
    }

    // Interfaz manejo de municipios
    public static void manejoDeMunicipios() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        Font fuenteDeTexto2 =new Font("SansSerif",Font.BOLD,12);
        // Creación del frame
        JFrame frameManejoMunicipios = new JFrame("Usac-Delivery");
        frameManejoMunicipios.setLayout(null);
        frameManejoMunicipios.setVisible(true);
        frameManejoMunicipios.setResizable(true);
        frameManejoMunicipios.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoMunicipios.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoMunicipios.setSize(900,800);



        JLabel tituloEmpresa = new JLabel("USAC-DELIVERY");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoMunicipios.add(tituloEmpresa);

        JLabel labelDepartamento = new JLabel("Departamento: ");
        labelDepartamento.setLayout(null);
        labelDepartamento.setVisible(true);
        labelDepartamento.setForeground(Color.BLACK);
        labelDepartamento.setBounds(10,150,250,30);
        labelDepartamento.setFont(fuenteDeTexto);
        frameManejoMunicipios.add(labelDepartamento);

        JComboBox<String> comboboxDepartamento = new JComboBox<>(devolverCodigosDepartamentos());
        comboboxDepartamento.setLayout(null);
        comboboxDepartamento.setVisible(true);
        comboboxDepartamento.setBounds(225,150,350,30);
        comboboxDepartamento.setFont(fuenteDeTexto);
        frameManejoMunicipios.add(comboboxDepartamento);

        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setLayout(null);
        labelNombre.setVisible(true);
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setBounds(10,250,300,30);
        labelNombre.setFont(fuenteDeTexto);
        frameManejoMunicipios.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(225,250,350,40);
        campoNombre.setVisible(true);
        campoNombre.setLayout(null);
        campoNombre.setFont(fuenteDeTexto);
        frameManejoMunicipios.add(campoNombre);


        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(80, 325, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoMunicipios.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoMunicipios.add(botonRegresar);


        JButton botonAgregarKiosco = new JButton("Agregar");
        botonAgregarKiosco.setLayout(null);
        botonAgregarKiosco.setVisible(true);
        botonAgregarKiosco.setBounds(305, 325, 300, 60);
        botonAgregarKiosco.setFont(fuenteDeTexto);
        botonAgregarKiosco.setBackground(Color.WHITE);
        botonAgregarKiosco.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoNombre.getText(), "")){
                    municipios.add(new Municipio(comboboxDepartamento.getSelectedItem().toString(),campoNombre.getText()));
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Municipio Registrado con éxito!!</p></html>" );
                    frameManejoMunicipios.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos del municipio para registralo!!</p></html>" );
                }
            }
        });
        frameManejoMunicipios.add(botonAgregarKiosco);

        JLabel labelCodigoRegion = new JLabel("Nombre Mun:");
        labelCodigoRegion.setLayout(null);
        labelCodigoRegion.setVisible(true);
        labelCodigoRegion.setForeground(Color.BLACK);
        labelCodigoRegion.setBounds(10,510,100,60);
        labelCodigoRegion.setFont(fuenteDeTexto2);
        frameManejoMunicipios.add(labelCodigoRegion);

        JComboBox<String> combobxDepartamento = new JComboBox<>(devolverCodigosMunicipios());
        combobxDepartamento.setLayout(null);
        combobxDepartamento.setVisible(true);
        combobxDepartamento.setBounds(100,525,150,30);
        combobxDepartamento.setFont(fuenteDeTexto2);
        frameManejoMunicipios.add(combobxDepartamento);

        JLabel labelRenovacion = new JLabel("Atributo a Renovar:");
        labelRenovacion.setLayout(null);
        labelRenovacion.setVisible(true);
        labelRenovacion.setForeground(Color.BLACK);
        labelRenovacion.setBounds(260,510,150,60);
        labelRenovacion.setFont(fuenteDeTexto2);
        frameManejoMunicipios.add(labelRenovacion);

        String[] opcionesAtributos = {"Nombre"};
        JComboBox<String> comboboxRenovar = new JComboBox<>(opcionesAtributos);
        comboboxRenovar.setLayout(null);
        comboboxRenovar.setVisible(true);
        comboboxRenovar.setBounds(400,525,150,30);
        comboboxRenovar.setFont(fuenteDeTexto2);
        frameManejoMunicipios.add(comboboxRenovar);

        JLabel datoRenovado = new JLabel("Dato Renovado:");
        datoRenovado.setLayout(null);
        datoRenovado.setVisible(true);
        datoRenovado.setForeground(Color.BLACK);
        datoRenovado.setBounds(600,525,100,30);
        datoRenovado.setFont(fuenteDeTexto2);
        frameManejoMunicipios.add(datoRenovado);

        JTextField campoRenovar = new JTextField();
        campoRenovar.setLayout(null);
        campoRenovar.setVisible(true);
        campoRenovar.setForeground(Color.BLACK);
        campoRenovar.setBounds(700,525,150,30);
        campoRenovar.setFont(fuenteDeTexto2);
        frameManejoMunicipios.add(campoRenovar);



        JButton botonAcualizar = new JButton("Actualizar");
        botonAcualizar.setLayout(null);
        botonAcualizar.setVisible(true);
        botonAcualizar.setBounds(100, 580, 200, 60);
        botonAcualizar.setFont(fuenteDeTexto);
        botonAcualizar.setBackground(Color.WHITE);
        botonAcualizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoRenovar.getText(), "")){
                    actualizarMunicipio(combobxDepartamento.getSelectedItem().toString(),campoRenovar.getText());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Municipio Actualizado Con Éxito!</p></html>" );
                    frameManejoMunicipios.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:10px;\">Debes llenar todos loc campos, si deseas actualizar datos!!</p></html>" );
                }
            }
        });
        frameManejoMunicipios.add(botonAcualizar);

        JButton botonEliminar = new JButton("Eliminar M.");
        botonEliminar.setLayout(null);
        botonEliminar.setVisible(true);
        botonEliminar.setBounds(400, 580, 200, 60);
        botonEliminar.setFont(fuenteDeTexto);
        botonEliminar.setBackground(Color.WHITE);
        botonEliminar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                eliminarMunicipio(combobxDepartamento.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Municipio Eliminado Con Éxito!</p></html>" );
                frameManejoMunicipios.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoMunicipios.add(botonEliminar);

        frameManejoMunicipios.repaint();
    }

    // Interfaz manejo de regiones y precios
    public static void manejoRegionesPrecios() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        Font fuenteDeTexto2 =new Font("SansSerif",Font.BOLD,12);
        // Creación del frame
        JFrame frameManejoRegiones = new JFrame("Usac-Delivery");
        frameManejoRegiones.setLayout(null);
        frameManejoRegiones.setVisible(true);
        frameManejoRegiones.setResizable(true);
        frameManejoRegiones.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoRegiones.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoRegiones.setSize(1000,800);



        JLabel tituloEmpresa = new JLabel("USAC-DELIVERY");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(375,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoRegiones.add(tituloEmpresa);



        String[] header = {"Código","Nombre","P. Normal","P. Especial","Envíos"};
        JTable tablaRegiones = new JTable(devolverDatosRegiones(), header);
        JScrollPane scrollRegiones= new JScrollPane(tablaRegiones);

        scrollRegiones.getViewport().setBackground(Color.white);


        tablaRegiones.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaRegiones.getTableHeader().setForeground(Color.WHITE);
        tablaRegiones.getTableHeader().setFont(fuenteDeTexto);
        tablaRegiones.getColumnModel().getColumn(0).setPreferredWidth(140);
        tablaRegiones.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaRegiones.getColumnModel().getColumn(2).setPreferredWidth(190);
        tablaRegiones.getColumnModel().getColumn(3).setPreferredWidth(190);
        tablaRegiones.getColumnModel().getColumn(4).setPreferredWidth(130);
        tablaRegiones.setRowHeight(40);
        tablaRegiones.setFont(fuenteDeTexto);
        scrollRegiones.setBounds(70, 100, 850, 300);
        scrollRegiones.setViewportView(tablaRegiones);
        frameManejoRegiones.add(scrollRegiones);

        JLabel labelCodigoRegion = new JLabel("Cod. Region:");
        labelCodigoRegion.setLayout(null);
        labelCodigoRegion.setVisible(true);
        labelCodigoRegion.setForeground(Color.BLACK);
        labelCodigoRegion.setBounds(10,410,100,60);
        labelCodigoRegion.setFont(fuenteDeTexto2);
        frameManejoRegiones.add(labelCodigoRegion);

        JComboBox<String> comboboxRegion = new JComboBox<>(devolverCodigosRegiones());
        comboboxRegion.setLayout(null);
        comboboxRegion.setVisible(true);
        comboboxRegion.setBounds(100,425,150,30);
        comboboxRegion.setFont(fuenteDeTexto2);
        frameManejoRegiones.add(comboboxRegion);

        JLabel labelRenovacion = new JLabel("Atributo a Renovar:");
        labelRenovacion.setLayout(null);
        labelRenovacion.setVisible(true);
        labelRenovacion.setForeground(Color.BLACK);
        labelRenovacion.setBounds(260,410,150,60);
        labelRenovacion.setFont(fuenteDeTexto2);
        frameManejoRegiones.add(labelRenovacion);

        String[] opcionesAtributos = {"Nombre", "PrecioNormal","PrecioEspecial"};
        JComboBox<String> comboboxRenovar = new JComboBox<>(opcionesAtributos);
        comboboxRenovar.setLayout(null);
        comboboxRenovar.setVisible(true);
        comboboxRenovar.setBounds(400,425,150,30);
        comboboxRenovar.setFont(fuenteDeTexto2);
        frameManejoRegiones.add(comboboxRenovar);

        JLabel datoRenovado = new JLabel("Dato Renovado:");
        datoRenovado.setLayout(null);
        datoRenovado.setVisible(true);
        datoRenovado.setForeground(Color.BLACK);
        datoRenovado.setBounds(600,420,100,30);
        datoRenovado.setFont(fuenteDeTexto2);
        frameManejoRegiones.add(datoRenovado);

        JTextField campoRenovar = new JTextField();
        campoRenovar.setLayout(null);
        campoRenovar.setVisible(true);
        campoRenovar.setForeground(Color.BLACK);
        campoRenovar.setBounds(700,425,150,30);
        campoRenovar.setFont(fuenteDeTexto2);
        frameManejoRegiones.add(campoRenovar);



        JButton botonAcualizar = new JButton("Actualizar");
        botonAcualizar.setLayout(null);
        botonAcualizar.setVisible(true);
        botonAcualizar.setBounds(250, 550, 200, 60);
        botonAcualizar.setFont(fuenteDeTexto);
        botonAcualizar.setBackground(Color.WHITE);
        botonAcualizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoRenovar.getText(), "")){
                    actualizarRegion(comboboxRegion.getSelectedItem().toString(),comboboxRenovar.getSelectedItem().toString(),campoRenovar.getText());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Region Actualizada Con Éxito!</p></html>" );
                    frameManejoRegiones.dispose();
                    try {
                        vistaAdmin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:10px;\">Debes llenar todos loc campos, si deseas actualizar datos!!</p></html>" );
                }
            }
        });
        frameManejoRegiones.add(botonAcualizar);

        JButton botonEliminar = new JButton("Eliminar R.");
        botonEliminar.setLayout(null);
        botonEliminar.setVisible(true);
        botonEliminar.setBounds(550, 550, 200, 60);
        botonEliminar.setFont(fuenteDeTexto);
        botonEliminar.setBackground(Color.WHITE);
        botonEliminar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                eliminarRegion(comboboxRegion.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Region Eliminada Con Éxito!</p></html>" );
                frameManejoRegiones.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoRegiones.add(botonEliminar);


        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(400, 650, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoRegiones.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoRegiones.add(botonRegresar);



        frameManejoRegiones.repaint();
    }

    // Interfaz manejo de reportes
    public static void manejoReportes() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameManejoReportes = new JFrame("Usac-Delivery");
        frameManejoReportes.setLayout(null);
        frameManejoReportes.setVisible(true);
        frameManejoReportes.setResizable(true);
        frameManejoReportes.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoReportes.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoReportes.setSize(1000,720);



        JLabel tituloEmpresa = new JLabel("REPORTES");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(375,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoReportes.add(tituloEmpresa);

        JLabel labelTopRegiones = new JLabel("Regiones Con Más Envíos: ");
        labelTopRegiones.setLayout(null);
        labelTopRegiones.setVisible(true);
        labelTopRegiones.setForeground(Color.BLACK);
        labelTopRegiones.setBounds(20,50,400,60);
        labelTopRegiones.setFont(fuenteDeTexto);
        frameManejoReportes.add(labelTopRegiones);

        JLabel labelTopUsuarios = new JLabel("Usuarios Con Más Envíos: ");
        labelTopUsuarios.setLayout(null);
        labelTopUsuarios.setVisible(true);
        labelTopUsuarios.setForeground(Color.BLACK);
        labelTopUsuarios.setBounds(20,320,400,60);
        labelTopUsuarios.setFont(fuenteDeTexto);
        frameManejoReportes.add(labelTopUsuarios);



        String[] header = {"Código","Nombre","Cantidad Envíos"};
        JTable tablaRegiones = new JTable(devolverDatosRegionesOrdenadas(), header);
        JScrollPane scrollRegiones= new JScrollPane(tablaRegiones);

        scrollRegiones.getViewport().setBackground(Color.white);


        tablaRegiones.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaRegiones.getTableHeader().setForeground(Color.WHITE);
        tablaRegiones.getTableHeader().setFont(fuenteDeTexto);
        tablaRegiones.getColumnModel().getColumn(0).setPreferredWidth(140);
        tablaRegiones.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaRegiones.getColumnModel().getColumn(2).setPreferredWidth(190);
        tablaRegiones.setRowHeight(30);
        tablaRegiones.setFont(fuenteDeTexto);
        scrollRegiones.setBounds(70, 100, 850, 220);
        scrollRegiones.setViewportView(tablaRegiones);
        frameManejoReportes.add(scrollRegiones);

        String[] header2 = {"Nombre","DPI","Cantidad Envíos"};
        JTable tablaUsuarios = new JTable(devolverDatosUsuariosOrdenadas(), header);
        JScrollPane scrollUsuarios= new JScrollPane(tablaUsuarios);

        scrollUsuarios.getViewport().setBackground(Color.white);


        tablaUsuarios.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaUsuarios.getTableHeader().setForeground(Color.WHITE);
        tablaUsuarios.getTableHeader().setFont(fuenteDeTexto);
        tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(140);
        tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(190);
        tablaUsuarios.setRowHeight(30);
        tablaUsuarios.setFont(fuenteDeTexto);
        scrollUsuarios.setBounds(70, 370, 850, 220);
        scrollUsuarios.setViewportView(tablaUsuarios);
        frameManejoReportes.add(scrollUsuarios);


        JLabel labelPaquetesEnviados = new JLabel("Total Paquetes Enviados: "+totalEnviosGeneral);
        labelPaquetesEnviados.setLayout(null);
        labelPaquetesEnviados.setVisible(true);
        labelPaquetesEnviados.setForeground(Color.BLACK);
        labelPaquetesEnviados.setBounds(20,590,400,60);
        labelPaquetesEnviados.setFont(fuenteDeTexto);
        frameManejoReportes.add(labelPaquetesEnviados);

        JLabel labelTotalIngresos = new JLabel("Ingresos Totales: "+ingresosTotales);
        labelTotalIngresos.setLayout(null);
        labelTotalIngresos.setVisible(true);
        labelTotalIngresos.setForeground(Color.BLACK);
        labelTotalIngresos.setBounds(550,590,400,60);
        labelTotalIngresos.setFont(fuenteDeTexto);
        frameManejoReportes.add(labelTotalIngresos);



        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(400, 650, 200, 30);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoReportes.dispose();
                try {
                    vistaAdmin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoReportes.add(botonRegresar);



        frameManejoReportes.repaint();
    }

    // Interfaz Vista Usuario
    public static void vistaUsuario() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameVistaUsuario = new JFrame("Usac-Delivery");
        frameVistaUsuario.setLayout(null);
        frameVistaUsuario.setVisible(true);
        frameVistaUsuario.setResizable(true);
        frameVistaUsuario.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameVistaUsuario.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameVistaUsuario.setSize(700,600);



        JLabel tituloEmpresa = new JLabel("User: "+dpiLoggeado);
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameVistaUsuario.add(tituloEmpresa);


        JButton botonRegistroTarjeta = new JButton("Registro Tarjetas");
        botonRegistroTarjeta.setLayout(null);
        botonRegistroTarjeta.setVisible(true);
        botonRegistroTarjeta.setBounds(215, 80, 300, 60);
        botonRegistroTarjeta.setFont(fuenteDeTexto);
        botonRegistroTarjeta.setBackground(Color.WHITE);
        botonRegistroTarjeta.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaUsuario.dispose();
                try {
                    manejoTarjetas();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        frameVistaUsuario.add(botonRegistroTarjeta);



        JButton botonDatosFacturacion = new JButton("Datos Facturación");
        botonDatosFacturacion.setLayout(null);
        botonDatosFacturacion.setVisible(true);
        botonDatosFacturacion.setBounds(215, 180, 300, 60);
        botonDatosFacturacion.setFont(fuenteDeTexto);
        botonDatosFacturacion.setBackground(Color.WHITE);
        botonDatosFacturacion.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaUsuario.dispose();
                try {
                    manejoDatosFacturaion();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameVistaUsuario.add(botonDatosFacturacion);

        JButton botonCotizacionPaquetes = new JButton("Cotizar/Compra");
        botonCotizacionPaquetes.setLayout(null);
        botonCotizacionPaquetes.setVisible(true);
        botonCotizacionPaquetes.setBounds(215, 280, 300, 60);
        botonCotizacionPaquetes.setFont(fuenteDeTexto);
        botonCotizacionPaquetes.setBackground(Color.WHITE);
        botonCotizacionPaquetes.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaUsuario.dispose();
                try {
                    manejoCotizaciones();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameVistaUsuario.add(botonCotizacionPaquetes);


        JButton botonDescargaFactura = new JButton("Descargas/Envíos");
        botonDescargaFactura.setLayout(null);
        botonDescargaFactura.setVisible(true);
        botonDescargaFactura.setBounds(215, 380, 300, 60);
        botonDescargaFactura.setFont(fuenteDeTexto);
        botonDescargaFactura.setBackground(Color.WHITE);
        botonDescargaFactura.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaUsuario.dispose();
                try {
                    manejoDescargasEnvios();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        frameVistaUsuario.add(botonDescargaFactura);


        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setLayout(null);
        botonCerrarSesion.setVisible(true);
        botonCerrarSesion.setBounds(215, 480, 300, 60);
        botonCerrarSesion.setFont(fuenteDeTexto);
        botonCerrarSesion.setBackground(Color.WHITE);
        botonCerrarSesion.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameVistaUsuario.dispose();
                try {
                    inicioDeSesion();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        frameVistaUsuario.add(botonCerrarSesion);

        frameVistaUsuario.repaint();
    }

    // Interfaz manejo de tarjetas del usuario
    public static void manejoTarjetas() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameManejoTarjetas = new JFrame("Usac-Delivery");
        frameManejoTarjetas.setLayout(null);
        frameManejoTarjetas.setVisible(true);
        frameManejoTarjetas.setResizable(true);
        frameManejoTarjetas.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoTarjetas.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoTarjetas.setSize(1000,650);



        JLabel tituloEmpresa = new JLabel("Vista Tarjetas");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(375,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoTarjetas.add(tituloEmpresa);


        int n=0;
        String[] header = {"Nombre","Número","Vencimiento","Tipo"};
        JTable tablaTarjetas = new JTable(devolverTarjetasUsuario(dpiLoggeado), header);
        JScrollPane scrollTarjetas= new JScrollPane(tablaTarjetas);

        scrollTarjetas.getViewport().setBackground(Color.white);


        tablaTarjetas.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaTarjetas.getTableHeader().setForeground(Color.WHITE);
        tablaTarjetas.getTableHeader().setFont(fuenteDeTexto);
        tablaTarjetas.getColumnModel().getColumn(0).setPreferredWidth(220);
        tablaTarjetas.getColumnModel().getColumn(1).setPreferredWidth(250);
        tablaTarjetas.getColumnModel().getColumn(2).setPreferredWidth(250);
        tablaTarjetas.getColumnModel().getColumn(2).setWidth(80);
        tablaTarjetas.setRowHeight(40);
        tablaTarjetas.setFont(fuenteDeTexto);
        scrollTarjetas.setBounds(70, 100, 850, 300);
        scrollTarjetas.setViewportView(tablaTarjetas);
        frameManejoTarjetas.add(scrollTarjetas);



        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(400, 450, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoTarjetas.dispose();
                try {
                    vistaUsuario();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoTarjetas.add(botonRegresar);


        JButton botonAgregarTarjeta = new JButton("Agregar");
        botonAgregarTarjeta.setLayout(null);
        botonAgregarTarjeta.setVisible(true);
        botonAgregarTarjeta.setBounds(400, 550, 200, 60);
        botonAgregarTarjeta.setFont(fuenteDeTexto);
        botonAgregarTarjeta.setBackground(Color.WHITE);
        botonAgregarTarjeta.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoTarjetas.dispose();
                try {
                    agregarNuevaTarjeta();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoTarjetas.add(botonAgregarTarjeta);



        frameManejoTarjetas.repaint();
    }

    // Interfaz creación de tarjetas
    public static void agregarNuevaTarjeta() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameAgregarTarjeta = new JFrame("Usac-Delivery");
        frameAgregarTarjeta.setLayout(null);
        frameAgregarTarjeta.setVisible(true);
        frameAgregarTarjeta.setResizable(true);
        frameAgregarTarjeta.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameAgregarTarjeta.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameAgregarTarjeta.setSize(700,650);



        JLabel tituloEmpresa = new JLabel("Nueva Tarjeta");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(tituloEmpresa);

        JLabel labelTipo = new JLabel("Tipo: ");
        labelTipo.setLayout(null);
        labelTipo.setVisible(true);
        labelTipo.setForeground(Color.BLACK);
        labelTipo.setBounds(10,150,250,30);
        labelTipo.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(labelTipo);

        JComboBox<String> comboboxTipo = new JComboBox<>("D,C".split(","));
        comboboxTipo.setLayout(null);
        comboboxTipo.setVisible(true);
        comboboxTipo.setBounds(190,150,350,30);
        comboboxTipo.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(comboboxTipo);

        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setLayout(null);
        labelNombre.setVisible(true);
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setBounds(10,250,250,30);
        labelNombre.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setLayout(null);
        campoNombre.setVisible(true);
        campoNombre.setBounds(190,250,350,40);
        campoNombre.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(campoNombre);


        JLabel labelNumero = new JLabel("Número: ");
        labelNumero.setLayout(null);
        labelNumero.setVisible(true);
        labelNumero.setForeground(Color.BLACK);
        labelNumero.setBounds(10,350,300,30);
        labelNumero.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(labelNumero);

        JTextField campoNumero = new JTextField();
        campoNumero.setBounds(190,350,350,40);
        campoNumero.setVisible(true);
        campoNumero.setLayout(null);
        campoNumero.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(campoNumero);

        JLabel labelFecha = new JLabel("Vencimiento: ");
        labelFecha.setLayout(null);
        labelFecha.setVisible(true);
        labelFecha.setForeground(Color.BLACK);
        labelFecha.setBounds(20,450,260,30);
        labelFecha.setFont(fuenteDeTexto);
        frameAgregarTarjeta.add(labelFecha);

        JLabel labelDia = new JLabel("dd ");
        labelDia.setLayout(null);
        labelDia.setVisible(true);
        labelDia.setForeground(Color.BLACK);
        labelDia.setBounds(250,450,30,30);
        labelDia.setFont(new Font("SansSerif",Font.BOLD,18));
        frameAgregarTarjeta.add(labelDia);

        JTextField campoDia = new JTextField();
        campoDia.setLayout(null);
        campoDia.setVisible(true);
        campoDia.setBounds(280,450,30,30);
        campoDia.setFont(new Font("SansSerif",Font.BOLD,18));
        frameAgregarTarjeta.add(campoDia);

        JLabel labelMes = new JLabel("mm ");
        labelMes.setLayout(null);
        labelMes.setVisible(true);
        labelMes.setForeground(Color.BLACK);
        labelMes.setBounds(360,450,40,30);
        labelMes.setFont(new Font("SansSerif",Font.BOLD,18));
        frameAgregarTarjeta.add(labelMes);

        JTextField campoMes = new JTextField();
        campoMes.setLayout(null);
        campoMes.setVisible(true);
        campoMes.setBounds(390,450,30,30);
        campoMes.setFont(new Font("SansSerif",Font.BOLD,18));
        frameAgregarTarjeta.add(campoMes);

        JLabel labelAnio = new JLabel("yy ");
        labelAnio.setLayout(null);
        labelAnio.setVisible(true);
        labelAnio.setForeground(Color.BLACK);
        labelAnio.setBounds(470,450,40,30);
        labelAnio.setFont(new Font("SansSerif",Font.BOLD,18));
        frameAgregarTarjeta.add(labelAnio);

        JTextField campoAnio = new JTextField();
        campoAnio.setLayout(null);
        campoAnio.setVisible(true);
        campoAnio.setBounds(500,450,100,30);
        campoAnio.setFont(new Font("SansSerif",Font.BOLD,18));
        frameAgregarTarjeta.add(campoAnio);


        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(80, 500, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameAgregarTarjeta.dispose();
                try {
                    manejoTarjetas();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameAgregarTarjeta.add(botonRegresar);


        JButton botonAgregarTarjeta = new JButton("Agregar");
        botonAgregarTarjeta.setLayout(null);
        botonAgregarTarjeta.setVisible(true);
        botonAgregarTarjeta.setBounds(305, 500, 300, 60);
        botonAgregarTarjeta.setFont(fuenteDeTexto);
        botonAgregarTarjeta.setBackground(Color.WHITE);
        botonAgregarTarjeta.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoNombre.getText(), "") && !Objects.equals(campoNumero.getText(), "") && !Objects.equals(campoAnio.getText(), "") && !Objects.equals(campoDia.getText(), "")  && !Objects.equals(campoMes.getText(), "")){
                    for (Usuario usuario : usuarios) {
                        if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                            usuario.getTarjetas().add(new Tarjeta(campoNombre.getText(),comboboxTipo.getSelectedItem().toString(),campoDia.getText()+"/"+campoMes.getText()+"/"+campoAnio.getText(),campoNumero.getText()));
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Tarjeta Registrada con éxito!!</p></html>" );
                    frameAgregarTarjeta.dispose();
                    try {
                        manejoTarjetas();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos para registrar la tarjeta!!</p></html>" );
                }
            }
        });
        frameAgregarTarjeta.add(botonAgregarTarjeta);

        frameAgregarTarjeta.repaint();
    }

    // Interfaz manejo de datos de facturación del usuario
    public static void manejoDatosFacturaion() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameManejoDatosFacturacion = new JFrame("Usac-Delivery");
        frameManejoDatosFacturacion.setLayout(null);
        frameManejoDatosFacturacion.setVisible(true);
        frameManejoDatosFacturacion.setResizable(true);
        frameManejoDatosFacturacion.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoDatosFacturacion.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoDatosFacturacion.setSize(1000,650);



        JLabel tituloEmpresa = new JLabel("Datos Facturación");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(375,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoDatosFacturacion.add(tituloEmpresa);


        int n=0;
        String[] header = {"Nombre","Dirección","Nit"};
        JTable tablaDatosFacturacion = new JTable(devolverDatosFacturacionUsuario(dpiLoggeado), header);
        JScrollPane scrollDatosFacturacion= new JScrollPane(tablaDatosFacturacion);

        scrollDatosFacturacion.getViewport().setBackground(Color.white);


        tablaDatosFacturacion.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaDatosFacturacion.getTableHeader().setForeground(Color.WHITE);
        tablaDatosFacturacion.getTableHeader().setFont(fuenteDeTexto);
        tablaDatosFacturacion.getColumnModel().getColumn(0).setPreferredWidth(220);
        tablaDatosFacturacion.getColumnModel().getColumn(1).setPreferredWidth(250);
        tablaDatosFacturacion.getColumnModel().getColumn(2).setPreferredWidth(250);
        tablaDatosFacturacion.setRowHeight(40);
        tablaDatosFacturacion.setFont(fuenteDeTexto);
        scrollDatosFacturacion.setBounds(70, 100, 850, 300);
        scrollDatosFacturacion.setViewportView(tablaDatosFacturacion);
        frameManejoDatosFacturacion.add(scrollDatosFacturacion);



        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(400, 450, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoDatosFacturacion.dispose();
                try {
                    vistaUsuario();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoDatosFacturacion.add(botonRegresar);


        JButton botonAgregarTarjeta = new JButton("Agregar");
        botonAgregarTarjeta.setLayout(null);
        botonAgregarTarjeta.setVisible(true);
        botonAgregarTarjeta.setBounds(400, 550, 200, 60);
        botonAgregarTarjeta.setFont(fuenteDeTexto);
        botonAgregarTarjeta.setBackground(Color.WHITE);
        botonAgregarTarjeta.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoDatosFacturacion.dispose();
                try {
                    agregarDatoFacturacion();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoDatosFacturacion.add(botonAgregarTarjeta);



        frameManejoDatosFacturacion.repaint();
    }

    // Interfaz creación de datos de facturación
    public static void agregarDatoFacturacion() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameAgregarDatoFacturacion = new JFrame("Usac-Delivery");
        frameAgregarDatoFacturacion.setLayout(null);
        frameAgregarDatoFacturacion.setVisible(true);
        frameAgregarDatoFacturacion.setResizable(true);
        frameAgregarDatoFacturacion.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameAgregarDatoFacturacion.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameAgregarDatoFacturacion.setSize(700,550);



        JLabel tituloEmpresa = new JLabel("Nuevo Dato Fac.");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(225,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(tituloEmpresa);

        JLabel labelDireccion = new JLabel("Dirección: ");
        labelDireccion.setLayout(null);
        labelDireccion.setVisible(true);
        labelDireccion.setForeground(Color.BLACK);
        labelDireccion.setBounds(10,150,250,30);
        labelDireccion.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(labelDireccion);

        JTextField campoDireccion = new JTextField();
        campoDireccion.setLayout(null);
        campoDireccion.setVisible(true);
        campoDireccion.setBounds(190,150,350,40);
        campoDireccion.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(campoDireccion);

        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setLayout(null);
        labelNombre.setVisible(true);
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setBounds(10,250,250,30);
        labelNombre.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setLayout(null);
        campoNombre.setVisible(true);
        campoNombre.setBounds(190,250,350,40);
        campoNombre.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(campoNombre);


        JLabel labelNit = new JLabel("Nit: ");
        labelNit.setLayout(null);
        labelNit.setVisible(true);
        labelNit.setForeground(Color.BLACK);
        labelNit.setBounds(10,350,300,30);
        labelNit.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(labelNit);

        JTextField campoNit = new JTextField();
        campoNit.setBounds(190,350,350,40);
        campoNit.setVisible(true);
        campoNit.setLayout(null);
        campoNit.setFont(fuenteDeTexto);
        frameAgregarDatoFacturacion.add(campoNit);


        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(80, 425, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameAgregarDatoFacturacion.dispose();
                try {
                    manejoDatosFacturaion();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameAgregarDatoFacturacion.add(botonRegresar);


        JButton botonAgregarDatoFacturacion = new JButton("Agregar");
        botonAgregarDatoFacturacion.setLayout(null);
        botonAgregarDatoFacturacion.setVisible(true);
        botonAgregarDatoFacturacion.setBounds(305, 425, 300, 60);
        botonAgregarDatoFacturacion.setFont(fuenteDeTexto);
        botonAgregarDatoFacturacion.setBackground(Color.WHITE);
        botonAgregarDatoFacturacion.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(!Objects.equals(campoNombre.getText(), "") && !Objects.equals(campoDireccion.getText(), "") && !Objects.equals(campoNit.getText(), "") ){
                    for (Usuario usuario : usuarios) {
                        if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                            usuario.getDatosFacturacion().add(new DatoFacturacion(campoNombre.getText(),campoDireccion.getText(),campoNit.getText()));
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Dato De Facturación Registrado con éxito!!</p></html>" );
                    frameAgregarDatoFacturacion.dispose();
                    try {
                        manejoDatosFacturaion();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos para registrar el dato de facturación!!</p></html>" );
                }
            }
        });
        frameAgregarDatoFacturacion.add(botonAgregarDatoFacturacion);

        frameAgregarDatoFacturacion.repaint();
    }

    //Interfaz Cotizaciones/Compra
    public static void manejoCotizaciones() throws IOException {
        totalCotizacion=0.00;
        //Fuente utilizad
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,18);

        // Creación del frame
        JFrame frameCotizaciones = new JFrame("USAC-DELIVERY");
        frameCotizaciones.setLayout(null);
        frameCotizaciones.setVisible(true);
        frameCotizaciones.setResizable(true);
        frameCotizaciones.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameCotizaciones.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        int h= sizeScreen.height;
        int w= sizeScreen.width;
        frameCotizaciones.setSize(1000,620);
        frameCotizaciones.setLocation(w/4,h/4);



        JLabel labelTitulo = new JLabel("Cotización");
        labelTitulo.setLayout(null);
        labelTitulo.setVisible(true);
        labelTitulo.setForeground(Color.BLACK);
        labelTitulo.setBounds(240,10,200,30);
        labelTitulo.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelTitulo);

        // Origen
        JLabel labelOrigen = new JLabel("Origen: ");
        labelOrigen.setLayout(null);
        labelOrigen.setVisible(true);
        labelOrigen.setForeground(Color.BLACK);
        labelOrigen.setBounds(20,60,100,30);
        labelOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelOrigen);

        JLabel labelDepartamentoOrigen = new JLabel("Dep: ");
        labelDepartamentoOrigen.setLayout(null);
        labelDepartamentoOrigen.setVisible(true);
        labelDepartamentoOrigen.setForeground(Color.BLACK);
        labelDepartamentoOrigen.setBounds(150,60,50,30);
        labelDepartamentoOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelDepartamentoOrigen);

        JComboBox<String> comboboxDepartamentoOrigen = new JComboBox<>(devolverCodigosDepartamentos());
        comboboxDepartamentoOrigen.setLayout(null);
        comboboxDepartamentoOrigen.setVisible(true);
        comboboxDepartamentoOrigen.setBounds(200,60,175,30);
        comboboxDepartamentoOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxDepartamentoOrigen);


        JLabel labelMunicipioOrigen = new JLabel("Mun: ");
        labelMunicipioOrigen.setLayout(null);
        labelMunicipioOrigen.setVisible(true);
        labelMunicipioOrigen.setForeground(Color.BLACK);
        labelMunicipioOrigen.setBounds(400,60,50,30);
        labelMunicipioOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelMunicipioOrigen);

        JComboBox<String> comboboxMunicipioOrigen = new JComboBox<>(devolverCodigosMunicipios());
        comboboxMunicipioOrigen.setLayout(null);
        comboboxMunicipioOrigen.setVisible(true);
        comboboxMunicipioOrigen.setBounds(450,60,175,30);
        comboboxMunicipioOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxMunicipioOrigen);

        JLabel labelDireccionOrigen = new JLabel("Dir: ");
        labelDireccionOrigen.setLayout(null);
        labelDireccionOrigen.setVisible(true);
        labelDireccionOrigen.setForeground(Color.BLACK);
        labelDireccionOrigen.setBounds(650,60,50,30);
        labelDireccionOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelDireccionOrigen);

        JTextField campoDireccionOrigen = new JTextField();
        campoDireccionOrigen.setLayout(null);
        campoDireccionOrigen.setVisible(true);
        campoDireccionOrigen.setBounds(700,60,275,30);
        campoDireccionOrigen.setFont(fuenteDeTexto);
        frameCotizaciones.add(campoDireccionOrigen);

        // Destino
        JLabel labelDestino = new JLabel("Destino: ");
        labelDestino.setLayout(null);
        labelDestino.setVisible(true);
        labelDestino.setForeground(Color.BLACK);
        labelDestino.setBounds(20,100,100,30);
        labelDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelDestino);

        JLabel labelDepartamentoDestino = new JLabel("Dep: ");
        labelDepartamentoDestino.setLayout(null);
        labelDepartamentoDestino.setVisible(true);
        labelDepartamentoDestino.setForeground(Color.BLACK);
        labelDepartamentoDestino.setBounds(150,100,50,30);
        labelDepartamentoDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelDepartamentoDestino);

        JComboBox<String> comboboxDepartamentoDestino = new JComboBox<>(devolverCodigosDepartamentos());
        comboboxDepartamentoDestino.setLayout(null);
        comboboxDepartamentoDestino.setVisible(true);
        comboboxDepartamentoDestino.setBounds(200,100,175,30);
        comboboxDepartamentoDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxDepartamentoDestino);


        JLabel labelMunicipioDestino = new JLabel("Mun: ");
        labelMunicipioDestino.setLayout(null);
        labelMunicipioDestino.setVisible(true);
        labelMunicipioDestino.setForeground(Color.BLACK);
        labelMunicipioDestino.setBounds(400,100,50,30);
        labelMunicipioDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelMunicipioDestino);

        JComboBox<String> comboboxMunicipioDestino = new JComboBox<>(devolverCodigosMunicipios());
        comboboxMunicipioDestino.setLayout(null);
        comboboxMunicipioDestino.setVisible(true);
        comboboxMunicipioDestino.setBounds(450,100,175,30);
        comboboxMunicipioDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxMunicipioDestino);

        JLabel labelDireccionDestino = new JLabel("Dir: ");
        labelDireccionDestino.setLayout(null);
        labelDireccionDestino.setVisible(true);
        labelDireccionDestino.setForeground(Color.BLACK);
        labelDireccionDestino.setBounds(650,100,50,30);
        labelDireccionDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelDireccionDestino);

        JTextField campoDireccionDestino = new JTextField();
        campoDireccionDestino.setLayout(null);
        campoDireccionDestino.setVisible(true);
        campoDireccionDestino.setBounds(700,100,275,30);
        campoDireccionDestino.setFont(fuenteDeTexto);
        frameCotizaciones.add(campoDireccionDestino);

        JLabel labelPaquetesAEnviar = new JLabel("Num. Paquetes: ");
        labelPaquetesAEnviar.setLayout(null);
        labelPaquetesAEnviar.setVisible(true);
        labelPaquetesAEnviar.setForeground(Color.BLACK);
        labelPaquetesAEnviar.setBounds(20,140,150,30);
        labelPaquetesAEnviar.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelPaquetesAEnviar);

        JTextField campoPaquetesAEnviar = new JTextField();
        campoPaquetesAEnviar.setLayout(null);
        campoPaquetesAEnviar.setVisible(true);
        campoPaquetesAEnviar.setBounds(200,140,400,30);
        campoPaquetesAEnviar.setFont(fuenteDeTexto);
        frameCotizaciones.add(campoPaquetesAEnviar);

        JLabel labelPeso = new JLabel("Peso Total: ");
        labelPeso.setLayout(null);
        labelPeso.setVisible(true);
        labelPeso.setForeground(Color.BLACK);
        labelPeso.setBounds(20,180,200,30);
        labelPeso.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelPeso);

        JTextField campoPeso = new JTextField();
        campoPeso.setLayout(null);
        campoPeso.setVisible(true);
        campoPeso.setBounds(200,180,400,30);
        campoPeso.setFont(fuenteDeTexto);
        frameCotizaciones.add(campoPeso);



        String[] header = {"Código","Nombre","P. Normal","P. Especial","Envíos"};
        JTable tablaRegiones = new JTable(devolverDatosRegiones(), header);
        JScrollPane scrollRegiones= new JScrollPane(tablaRegiones);
        scrollRegiones.getViewport().setBackground(Color.white);
        tablaRegiones.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaRegiones.getTableHeader().setForeground(Color.WHITE);
        tablaRegiones.getTableHeader().setFont(fuenteDeTexto);
        tablaRegiones.getColumnModel().getColumn(0).setPreferredWidth(140);
        tablaRegiones.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaRegiones.getColumnModel().getColumn(2).setPreferredWidth(190);
        tablaRegiones.getColumnModel().getColumn(3).setPreferredWidth(190);
        tablaRegiones.getColumnModel().getColumn(4).setPreferredWidth(130);
        tablaRegiones.setRowHeight(20);
        tablaRegiones.setFont(fuenteDeTexto);
        scrollRegiones.setBounds(70, 220, 850, 150);
        scrollRegiones.setViewportView(tablaRegiones);
        frameCotizaciones.add(scrollRegiones);

        JLabel labelTipoPrecio = new JLabel("Tipo Precio: ");
        labelTipoPrecio.setLayout(null);
        labelTipoPrecio.setVisible(true);
        labelTipoPrecio.setForeground(Color.BLACK);
        labelTipoPrecio.setBounds(20,390,200,30);
        labelTipoPrecio.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelTipoPrecio);

        JComboBox<String> comboboxTipoPrecio = new JComboBox<>("normal,especial".split(","));
        comboboxTipoPrecio.setLayout(null);
        comboboxTipoPrecio.setVisible(true);
        comboboxTipoPrecio.setBounds(200,390,175,30);
        comboboxTipoPrecio.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxTipoPrecio);



        JLabel labelTotalServicio = new JLabel("Total De Servicio:   Q.");
        labelTotalServicio.setLayout(null);
        labelTotalServicio.setVisible(true);
        labelTotalServicio.setForeground(Color.BLACK);
        labelTotalServicio.setBounds(20,430,200,30);
        labelTotalServicio.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelTotalServicio);

        JLabel labelMostrarTotal = new JLabel(String.valueOf(totalCotizacion));
        labelMostrarTotal.setLayout(null);
        labelMostrarTotal.setVisible(true);
        labelMostrarTotal.setForeground(Color.BLACK);
        labelMostrarTotal.setBounds(240,430,200,30);
        labelMostrarTotal.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelMostrarTotal);


        JButton botonCotizar = new JButton("Cotizar");
        botonCotizar.setLayout(null);
        botonCotizar.setVisible(true);
        botonCotizar.setBounds(400, 390, 120, 30);
        botonCotizar.setBackground(Color.WHITE);
        botonCotizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                if(!Objects.equals(campoDireccionOrigen.getText(), "") && !Objects.equals(campoDireccionOrigen.getText(), "") && !Objects.equals(campoPaquetesAEnviar.getText(), "") && !Objects.equals(campoPeso.getText(), "")){
                    totalCotizacion=(devolverPrecioRegion(comboboxDepartamentoDestino.getSelectedItem().toString(),comboboxTipoPrecio.getSelectedItem().toString()))* Double.parseDouble(campoPaquetesAEnviar.getText())* Double.parseDouble(campoPeso.getText());
                    labelMostrarTotal.setText(String.valueOf(totalCotizacion));
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos para poder realizar la cotizacion!!</p></html>" );
                }
            }
        });
        frameCotizaciones.add(botonCotizar);



        JButton generarHtml = new JButton("HTML");
        generarHtml.setLayout(null);
        generarHtml.setVisible(true);
        generarHtml.setBounds(650, 390, 100, 30);
        generarHtml.setBackground(Color.WHITE);
        generarHtml.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                if(!Objects.equals(campoDireccionOrigen.getText(), "") && !Objects.equals(campoDireccionOrigen.getText(), "") && !Objects.equals(campoPaquetesAEnviar.getText(), "") && !Objects.equals(campoPeso.getText(), "")){
                    totalCotizacion=(devolverPrecioRegion(comboboxDepartamentoDestino.getSelectedItem().toString(),comboboxTipoPrecio.getSelectedItem().toString()))* Double.parseDouble(campoPaquetesAEnviar.getText())* Double.parseDouble(campoPeso.getText());
                    labelMostrarTotal.setText(String.valueOf(totalCotizacion));
                    generarHtmlCotizacion(campoDireccionOrigen.getText(),campoDireccionDestino.getText(),campoPaquetesAEnviar.getText(),campoPeso.getText(),String.valueOf(totalCotizacion),comboboxTipoPrecio.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">HTML generado con éxito, revisa la carpeta raíz!!</p></html>" );
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos para poder realizar la cotizacion en HTML!!</p></html>" );
                }

            }
        });
        frameCotizaciones.add(generarHtml);

        JLabel labelTipoPago = new JLabel("Tipo De Pago: ");
        labelTipoPago.setLayout(null);
        labelTipoPago.setVisible(true);
        labelTipoPago.setForeground(Color.BLACK);
        labelTipoPago.setBounds(20,460,200,30);
        labelTipoPago.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelTipoPago);

        JComboBox<String> comboboxTipoPago = new JComboBox<>("Efectivo,Tarjeta".split(","));
        comboboxTipoPago.setLayout(null);
        comboboxTipoPago.setVisible(true);
        comboboxTipoPago.setBounds(200,460,175,30);
        comboboxTipoPago.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxTipoPago);

        JLabel labelEscogerTarjeta = new JLabel("Escoger Tarjeta: ");
        labelEscogerTarjeta.setLayout(null);
        labelEscogerTarjeta.setVisible(true);
        labelEscogerTarjeta.setForeground(Color.BLACK);
        labelEscogerTarjeta.setBounds(400,460,200,30);
        labelEscogerTarjeta.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelEscogerTarjeta);

        JComboBox<String> comboboxEscogerTarjeta = new JComboBox<>(devolverNumerosTarjetasUsuario(dpiLoggeado));
        comboboxEscogerTarjeta.setLayout(null);
        comboboxEscogerTarjeta.setVisible(true);
        comboboxEscogerTarjeta.setBounds(560,460,175,30);
        comboboxEscogerTarjeta.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxEscogerTarjeta);

        JLabel labelCvv = new JLabel("cvv: ");
        labelCvv.setLayout(null);
        labelCvv.setVisible(true);
        labelCvv.setForeground(Color.BLACK);
        labelCvv.setBounds(750,460,50,30);
        labelCvv.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelCvv);

        JTextField campoCvv = new JTextField();
        campoCvv.setLayout(null);
        campoCvv.setVisible(true);
        campoCvv.setBounds(800,460,50,30);
        campoCvv.setFont(fuenteDeTexto);
        frameCotizaciones.add(campoCvv);

        JLabel labelDatoFacturacion = new JLabel("Dato Fac. : ");
        labelDatoFacturacion.setLayout(null);
        labelDatoFacturacion.setVisible(true);
        labelDatoFacturacion.setForeground(Color.BLACK);
        labelDatoFacturacion.setBounds(20,500,200,30);
        labelDatoFacturacion.setFont(fuenteDeTexto);
        frameCotizaciones.add(labelDatoFacturacion);

        JComboBox<String> comboboxDatosFacturacion = new JComboBox<>(devolverListaDatosFacturacion(dpiLoggeado));
        comboboxDatosFacturacion.setLayout(null);
        comboboxDatosFacturacion.setVisible(true);
        comboboxDatosFacturacion.setBounds(200,500,400,30);
        comboboxDatosFacturacion.setFont(fuenteDeTexto);
        frameCotizaciones.add(comboboxDatosFacturacion);

        JButton botonComprar = new JButton("comprar");
        botonComprar.setLayout(null);
        botonComprar.setVisible(true);
        botonComprar.setBounds(660, 500, 100, 30);
        botonComprar.setBackground(Color.WHITE);
        botonComprar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                if(!Objects.equals(campoDireccionOrigen.getText(), "") && !Objects.equals(campoDireccionOrigen.getText(), "") && !Objects.equals(campoPaquetesAEnviar.getText(), "") && !Objects.equals(campoPeso.getText(), "")){
                    if(Objects.equals(comboboxTipoPago.getSelectedItem().toString(), "Tarjeta") && !Objects.equals(comboboxEscogerTarjeta.getSelectedItem().toString(), "No-Aplica")){
                        if(!Objects.equals(campoCvv.getText(), "")){
                            java.util.Random rand = new java.util.Random();
                            String cadenaAleatoria = "IPC1E";
                            int n = almacenamiento.length();
                            for (int i = 0; i < 5; i++) {
                                cadenaAleatoria += almacenamiento.charAt(rand.nextInt(n));
                            }
                            ArrayList<String> datosFactura=new ArrayList<>();
                            ArrayList<String> datosGuia=new ArrayList<>();

                            //Factura
                            datosFactura.add(String.valueOf(numeroFactura));
                            numeroFactura++;
                            datosFactura.add(cadenaAleatoria);
                            datosFactura.add(campoDireccionOrigen.getText());
                            datosFactura.add(campoDireccionDestino.getText());
                            datosFactura.add(comboboxDatosFacturacion.getSelectedItem().toString());
                            datosFactura.add(comboboxTipoPago.getSelectedItem().toString());
                            datosFactura.add(campoPeso.getText());
                            datosFactura.add(campoPaquetesAEnviar.getText());
                            datosFactura.add(String.valueOf(totalCotizacion));
                            //Guia
                            datosGuia.add(cadenaAleatoria);
                            datosGuia.add(campoDireccionOrigen.getText());
                            datosGuia.add(campoDireccionDestino.getText());
                            datosGuia.add(comboboxTipoPago.getSelectedItem().toString());
                            datosGuia.add(campoPeso.getText());
                            datosGuia.add(campoPaquetesAEnviar.getText());
                            java.time.LocalDate fechaActual=java.time.LocalDate.now();
                            datosGuia.add(fechaActual.toString());
                            datosGuia.add(String.valueOf(totalCotizacion));
                            for (Usuario usuario : usuarios) {
                                if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                                    usuario.getEnvios().add(new Envio(cadenaAleatoria,comboboxTipoPrecio.getSelectedItem().toString(),campoDireccionDestino.getText(),totalCotizacion,comboboxTipoPago.getSelectedItem().toString(),datosFactura,datosGuia));
                                    usuario.setPaquetesEnviados(usuario.getPaquetesEnviados()+Integer.parseInt(campoPaquetesAEnviar.getText()));
                                    break;
                                }
                            }
                            agregarEnvioRegion(comboboxDepartamentoOrigen.getSelectedItem().toString());
                            totalEnviosGeneral+=Integer.parseInt(campoPaquetesAEnviar.getText());
                            ingresosTotales+=totalCotizacion;
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Compra Realizada con éxito!!</p></html>" );
                            frameCotizaciones.dispose();
                            try {
                                vistaUsuario();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                        }else{
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Al hacer una compra con tarjeta debes colocar el número CVV de la tarjeta!!</p></html>" );
                        }

                    }else if(Objects.equals(comboboxTipoPago.getSelectedItem().toString(), "Efectivo")){
                        java.util.Random rand = new java.util.Random();
                        String cadenaAleatoria = "IPC1E";
                        int n = almacenamiento.length();
                        for (int i = 0; i < 5; i++) {
                            cadenaAleatoria += almacenamiento.charAt(rand.nextInt(n));
                        }
                        ArrayList<String> datosFactura=new ArrayList<>();
                        ArrayList<String> datosGuia=new ArrayList<>();

                        //Factura
                        datosFactura.add(String.valueOf(numeroFactura));
                        numeroFactura++;
                        datosFactura.add(cadenaAleatoria);
                        datosFactura.add(campoDireccionOrigen.getText());
                        datosFactura.add(campoDireccionDestino.getText());
                        datosFactura.add(comboboxDatosFacturacion.getSelectedItem().toString());
                        datosFactura.add(comboboxTipoPago.getSelectedItem().toString());
                        datosFactura.add(campoPeso.getText());
                        datosFactura.add(campoPaquetesAEnviar.getText());
                        datosFactura.add(String.valueOf(totalCotizacion));
                        //Guia
                        datosGuia.add(cadenaAleatoria);
                        datosGuia.add(campoDireccionOrigen.getText());
                        datosGuia.add(campoDireccionDestino.getText());
                        datosGuia.add(comboboxTipoPago.getSelectedItem().toString());
                        datosGuia.add(campoPeso.getText());
                        datosGuia.add(campoPaquetesAEnviar.getText());
                        java.time.LocalDate fechaActual=java.time.LocalDate.now();
                        datosGuia.add(fechaActual.toString());
                        datosGuia.add(String.valueOf(totalCotizacion));

                        for (Usuario usuario : usuarios) {
                            if (Objects.equals(usuario.getDpi(), dpiLoggeado)) {
                                usuario.getEnvios().add(new Envio(cadenaAleatoria,comboboxTipoPrecio.getSelectedItem().toString(),campoDireccionDestino.getText(),totalCotizacion+5.00,comboboxTipoPago.getSelectedItem().toString(),datosFactura,datosGuia));
                                usuario.setPaquetesEnviados(usuario.getPaquetesEnviados()+Integer.parseInt(campoPaquetesAEnviar.getText()));
                                break;
                            }
                        }
                        agregarEnvioRegion(comboboxDepartamentoOrigen.getSelectedItem().toString());
                        totalEnviosGeneral+=Integer.parseInt(campoPaquetesAEnviar.getText());
                        ingresosTotales+=totalCotizacion;
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Compra Realizada con éxito!!</p></html>" );
                        frameCotizaciones.dispose();
                        try {
                            vistaUsuario();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes seleccionar una tarjeta válida para poder realizar una compra con tarjeta, si no tienes, selecciona efectivo!!</p></html>" );
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Debes llenar todos los campos para poder realizar la compra!!</p></html>" );
                }
            }
        });
        frameCotizaciones.add(botonComprar);


        JButton botonSalir = new JButton("Volver");
        botonSalir.setLayout(null);
        botonSalir.setVisible(true);
        botonSalir.setBounds(450, 540, 100, 30);
        botonSalir.setBackground(Color.WHITE);
        botonSalir.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                frameCotizaciones.dispose();
                try {
                    vistaUsuario();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        frameCotizaciones.add(botonSalir);



        frameCotizaciones.repaint();
    }

    // Interfaz Descargas/Ver Envíos
    public static void manejoDescargasEnvios() throws IOException{

        //Fuente utilizada
        Font fuenteDeTexto =new Font("SansSerif",Font.BOLD,30);
        // Creación del frame
        JFrame frameManejoDescargasEnvios = new JFrame("Usac-Delivery");
        frameManejoDescargasEnvios.setLayout(null);
        frameManejoDescargasEnvios.setVisible(true);
        frameManejoDescargasEnvios.setResizable(true);
        frameManejoDescargasEnvios.getContentPane().setBackground(Color.LIGHT_GRAY);
        frameManejoDescargasEnvios.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Le agreamos un tamaño al frame login
        frameManejoDescargasEnvios.setSize(1200,650);



        JLabel tituloEmpresa = new JLabel("Envíos-Descargas");
        tituloEmpresa.setLayout(null);
        tituloEmpresa.setVisible(true);
        tituloEmpresa.setForeground(Color.BLACK);
        tituloEmpresa.setBounds(470,10,400,60);
        tituloEmpresa.setFont(fuenteDeTexto);
        frameManejoDescargasEnvios.add(tituloEmpresa);



        String[] header = {"Código","Tipo Servicio","Destinatario","Total Envío","Tipo Pago"};
        JTable tablaEnvios = new JTable(devolverEnviosUsuario(dpiLoggeado), header);
        JScrollPane scrollEnvios= new JScrollPane(tablaEnvios);

        scrollEnvios.getViewport().setBackground(Color.white);


        tablaEnvios.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        tablaEnvios.getTableHeader().setForeground(Color.WHITE);
        tablaEnvios.getTableHeader().setFont(fuenteDeTexto);
        tablaEnvios.getColumnModel().getColumn(0).setPreferredWidth(140);
        tablaEnvios.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaEnvios.getColumnModel().getColumn(2).setPreferredWidth(190);
        tablaEnvios.getColumnModel().getColumn(3).setPreferredWidth(190);
        tablaEnvios.getColumnModel().getColumn(4).setPreferredWidth(130);
        tablaEnvios.setRowHeight(40);
        tablaEnvios.setFont(fuenteDeTexto);
        scrollEnvios.setBounds(70, 100, 1050, 300);
        scrollEnvios.setViewportView(tablaEnvios);
        frameManejoDescargasEnvios.add(scrollEnvios);


        JLabel labelCodigoEnvio = new JLabel("Código Del Envio: ");
        labelCodigoEnvio.setLayout(null);
        labelCodigoEnvio.setVisible(true);
        labelCodigoEnvio.setForeground(Color.BLACK);
        labelCodigoEnvio.setBounds(20,450,300,30);
        labelCodigoEnvio.setFont(fuenteDeTexto);
        frameManejoDescargasEnvios.add(labelCodigoEnvio);

        JTextField campoCodigoEnvio = new JTextField();
        campoCodigoEnvio.setLayout(null);
        campoCodigoEnvio.setVisible(true);
        campoCodigoEnvio.setBounds(350,450,400,30);
        campoCodigoEnvio.setFont(fuenteDeTexto);
        frameManejoDescargasEnvios.add(campoCodigoEnvio);





        JButton botonGuia = new JButton("Guia");
        botonGuia.setLayout(null);
        botonGuia.setVisible(true);
        botonGuia.setBounds(775, 450, 70, 30);
        botonGuia.setBackground(Color.WHITE);
        botonGuia.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                if(existeElPaquete(campoCodigoEnvio.getText())){
                    generarHtmlGuia(campoCodigoEnvio.getText());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Guia en Html generada con éxito!!</p></html>" );
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Código inexistente, intenta de nuevo!!</p></html>" );
                }

            }
        });
        frameManejoDescargasEnvios.add(botonGuia);

        JButton botonFactura = new JButton("Factura");
        botonFactura.setLayout(null);
        botonFactura.setVisible(true);
        botonFactura.setBounds(875, 450, 100, 30);
        botonFactura.setBackground(Color.WHITE);
        botonFactura.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e4){
                if(existeElPaquete(campoCodigoEnvio.getText())){
                    generarHtmlFactura(campoCodigoEnvio.getText());
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Factura en Html generada con éxito!!</p></html>" );
                }else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:blue; font:10px;\">Código inexistente, intenta de nuevo!!</p></html>" );
                }

            }
        });
        frameManejoDescargasEnvios.add(botonFactura);



        JButton botonRegresar = new JButton("Volver");
        botonRegresar.setLayout(null);
        botonRegresar.setVisible(true);
        botonRegresar.setBounds(520, 550, 200, 60);
        botonRegresar.setFont(fuenteDeTexto);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                frameManejoDescargasEnvios.dispose();
                try {
                    vistaUsuario();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frameManejoDescargasEnvios.add(botonRegresar);



        frameManejoDescargasEnvios.repaint();
    }

}