package com.example.cuadrostexto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user, edt_pass, edt_veriPass,edt_Email;
    private Button ok;

    private String str_pass;
    private String str_passVerif;
    private String Nombre_user;
    private String NombreEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Estabelcer();
        Escuchando();
    }

    public void Estabelcer(){
        edt_pass =(EditText)findViewById(R.id.id_pass);
        edt_veriPass =(EditText)findViewById(R.id.id_pass2);
        user=(EditText)findViewById(R.id.id_usr);
        ok=(Button)findViewById(R.id.id_ok);
        edt_Email=(EditText)findViewById(R.id.id_mail);
    }

    public void Escuchando(){
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Registrar()&&ValidaEmail() ){
                    MostrarAlert();
                };
            }
        });
    }

    public boolean Registrar(){
        Nombre_user=user.getText().toString();//dando valor a la variable Nombre_user

        str_pass= edt_pass.getText().toString();
        str_passVerif= edt_veriPass.getText().toString();
        if(Nombre_user.isEmpty()){
            user.setError("Campo Obligatorio");
            return false;
        }else if(str_pass.isEmpty()){
            edt_pass.setError("Ingrese una contraseña");
            return false;
        }else if(str_passVerif.isEmpty()){
            edt_veriPass.setError("Verifica Contraseña");
            return false;
        }if(!str_pass.equals(str_passVerif)){
            Toast.makeText(this,"Las Contraseñas\n NO Coinsiden",Toast.LENGTH_LONG).show();
            return  false;
        }else{
            return true;
        }
    }

    private boolean ValidaEmail(){
        NombreEmail=edt_Email.getText().toString().trim();
        if(NombreEmail.isEmpty()){
            edt_Email.setError("Campo Obligatorio");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(NombreEmail).matches()){
            edt_Email.setError("Correo no valido");
            return false;
        }else{
            edt_Email.setError(null);
            return true;
        }
    }
    public void MostrarAlert(){
        AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);//crea un alert Dialog
        alert.setMessage("Usuario: "+Nombre_user+"\nEmail: "+NombreEmail+"\nPass: "+str_pass).setCancelable(false).setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog titulo=alert.create();
        titulo.setTitle("salir?");//Nombre_user+"\n"+NombreEmail+"\n"+str_pass);
        titulo.show();
    }
}
