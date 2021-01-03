package lab3_stack_20244554_EscobarZamorano;

import java.util.ArrayList;

public class Stack {
    //------* Atributos *------
    private ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
    private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private Usuario usuarioActivo;
    
    //------* Constructor *------
    public Stack(){
        this.listaPreguntas = new ArrayList<Pregunta>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.usuarioActivo = new Usuario(null, null, null);
    }
    
    public Stack(Usuario usuarioActivo) {
        this.listaPreguntas = new ArrayList<Pregunta>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.usuarioActivo = usuarioActivo;
    }
    
    //------* Setters *------
    public void setListaPreguntas(ArrayList<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }
    
    //------* Getters *------
    public ArrayList<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }
    
    //------* Metodos *------
    //------ REGISTER ------
    //1)VERIFICAR QUE EL USUARIO NO ESTE REGISTRADO
    public Stack register(String username, String password){
        //Se crea un usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(username,password,0);
        
        for (int i = 0; i < this.getListaUsuarios().size() ; i++) {
            if (this.getListaUsuarios().get(i).usuariosIgualesRegister(nuevoUsuario)) {
                System.out.println("EL USUARIO ESTA REGISTRADO xd");
                return this;
            }
        }
        //En el caso de que no se haya encontrado al usuario se registra en la lista
        this.getListaUsuarios().add(nuevoUsuario);
        System.out.println("EL USUARIO NO ESTA REGISTRADO");
        return this;
    }
    
    //------ LOGIN ------
    public Stack login(String username, String password){
        //Se crea un usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(username,password,0);
        
        //Se verifica que las credenciales ingresadas esten correctas
        for (int i = 0; i < this.getListaUsuarios().size(); i++) {
            if (this.getListaUsuarios().get(i).usuariosIgualesLogin(nuevoUsuario)) {
                //Si las credenciales coinciden se deja al usuario como usuario activo
                this.setUsuarioActivo(nuevoUsuario);
                System.out.println("SE PUDO LOGEAR CORRECTAMENTE");
                return this;
            }
        }
        //En caso de que el usuario no coincida o no este registrado se retorna el mismo stack
        System.out.println("NO SE PUDO LOGEAR");
        return this; 
    }
    
    //------ LOGOUT ------
    public Stack logout(String username, String password){
        //Se crea un usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(username,password,0);
        
        //Se verifica que haya una sesion iniciada
        if (this.getUsuarioActivo().getUsername() == null) {
            System.out.println("NO HAY SESION INICIADA");
            return this;
        }
        
        //Se verifica que el usuario activo sea el usuario que quiere cerrar sesion
        if (this.getUsuarioActivo().usuariosIgualesLogin(nuevoUsuario)) {
            this.setUsuarioActivo(new Usuario(null,null,null));
            System.out.println("SE HA CERRADO SESION");
            return this;
        }
        
        System.out.println("LAS CREDENCIALES NO COINCIDEN");
        return this;
    }
    
    //------ ASK ------
    public Stack ask(String tituloPregunta, String contenidoPregunta, ArrayList<Etiqueta> listaEtiquetas){
        //VERIFICAR SESION INICIADA
        if (this.getUsuarioActivo().getUsername() == null) {
            //SI NO HAY USUARIO CON SESION INICIADA NO SE PUEDE EJECUTAR LA OPERACION
            return this;  
        }
        int idNuevo = this.getListaPreguntas().size();
        
        Pregunta preguntaNueva = new Pregunta(idNuevo, tituloPregunta, contenidoPregunta, "12-12-2020", this.getUsuarioActivo(), "NO ACEPTADA", 0);
        
        preguntaNueva.setListaEtiquetas(listaEtiquetas);
        
        this.getListaPreguntas().add(preguntaNueva);

        return this;
    }
    
    
    
    
}
