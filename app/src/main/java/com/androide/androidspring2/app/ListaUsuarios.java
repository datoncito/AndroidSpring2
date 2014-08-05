package com.androide.androidspring2.app;

/**
 * Created by campitos on 14/06/14.
 */
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.json.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

@JsonRootName("usuarios")
public class ListaUsuarios {

    ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
