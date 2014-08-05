package com.androide.androidspring2.app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;


public class ConexionAcitivty extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conexion_acitivty);
    }


public void conectarse(View v){
    final TextView texto=(TextView)findViewById(R.id.resultado);
 texto.setText("tu conexion de la vergaaaa eh????");
    new AsyncTask<String , Integer, String>(){
        String mensaje="nada";
        ArrayList<Usuario> usuarios;
        Usuario u=new Usuario();

        @Override
        protected void onPreExecute() {
            texto.setText("Cargando tu chingadera...");
        }
        public String doInBackground(String...x){
            try {

               // u = leerJasonPorNombre("nada");
                usuarios=leerJasonTodos("nada");
          //  u=   enviarUsuarioJason("nada");
            }catch(Exception e){}
            return mensaje;
        }
        public void onPostExecute(String mensaje){
            try {
               //  texto.setText(u.getNombre());
               texto.setText("Usuarios leidos:" +usuarios.size());

            }catch(Exception e){
                texto.setText(e.getMessage());
            }
        }
    }.execute(null,null,null);

}



    public ArrayList<Usuario> leerJasonTodos(String miurl)throws Exception {
        String leido="nada se leyo :(";
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
        HttpEntity<?> requestEntity=new HttpEntity<Object>(requestHeaders);

        String url="http://campitos-ley.whelastic.net/servicios/usuario";
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // leido=restTemplate.getForObject(url,String.class,"Android");
        ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.GET, requestEntity,String.class);
        leido= responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, ArrayList<Usuario>> mapausuarios=   mapper.readValue(leido,new TypeReference<Map<String,ArrayList<Usuario>>>(){});
        ArrayList<Usuario> usuarios=      (ArrayList<Usuario>) mapausuarios.get("usuarios");
        return usuarios;
    }

    public Usuario leerJasonPorNombre(String miurl) throws Exception{
        String leido="nada se leyo :(";
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
        HttpEntity<?> requestEntity=new HttpEntity<Object>(requestHeaders);

        String url="http://campitos-ley.whelastic.net/servicios/usuario";
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // leido=restTemplate.getForObject(url,String.class,"Android");
        ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.GET, requestEntity,String.class);
        leido= responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Usuario> usuario=   mapper.readValue(leido,new TypeReference<Map<String,Usuario>>(){});
Usuario u=usuario.get("usuarios");
        return u;
    }

    public Usuario enviarUsuarioJason(String miurl) throws Exception{
        String leido="nada se leyo :(";
        /* Para el caso del metodo post cuando envias no es necesario enviar  rootElement :"usuarios:" en este proyecto, solo es necesario
        enviar el objeto y tendras compatibilidad con sencha touch para el metodo post
         */
        Usuario usuario2=new Usuario(8,"Gato con botas",22);

        ObjectMapper mapper2=new ObjectMapper();
        String enviar= mapper2.writeValueAsString(usuario2);
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("Application","json")));
        HttpEntity<String> requestEntity=new HttpEntity<String>(enviar,requestHeaders);

        String url="http://campitos-ley.whelastic.net/servicios/usuario";
        RestTemplate restTemplate=new RestTemplate();

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

/*
Acuerdate que para leer usamos los json como Strings
 */
        // leido=restTemplate.getForObject(url,String.class,"Android");
        ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.POST, requestEntity,String.class);
        leido= responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Usuario> usuario=   mapper.readValue(leido,new TypeReference<Map<String,Usuario>>(){});
        Usuario u=usuario.get("usuarios");
        return u;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.conexion_acitivty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
