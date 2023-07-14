package org.chakuy.hbsis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class insertar_registro extends AppCompatActivity {
    ImageButton btn_add;
    EditText nombre, area, descripcion, fecha;
    Spinner tipoSpinner;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_registro);

        // Titulo para volver

        mfirestore = FirebaseFirestore.getInstance();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  //      this.setTitle("Registro Ubicacion");

        // Asignacion de variables y recursos
        tipoSpinner = findViewById(R.id.estado);
        nombre = findViewById(R.id.nombre);
        area = findViewById(R.id.area);
        descripcion = findViewById(R.id.descripcion);
        fecha = findViewById(R.id.fecha);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_producto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(adapter);

        btn_add = findViewById(R.id.btn_imgregistrar);

        // Evento del botón
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rnombre = nombre.getText().toString().trim();
                String rarea = area.getText().toString().trim();
                String restado = tipoSpinner.getSelectedItem().toString().trim();
                String rdescripcion = descripcion.getText().toString().trim();
                String rfecha = fecha.getText().toString().trim();

                if (rnombre.isEmpty() && rarea.isEmpty() && restado.isEmpty() && rdescripcion.isEmpty() && rfecha.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    Posthb(rnombre, rarea, restado, rdescripcion, rfecha);
                }
            }
        });

        // Obtener fecha actual en formato "día, mes, año"
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd, MMMM, yyyy");
        String formattedDate = dateFormat.format(currentDate);

        // Establecer fecha actual en el campo de texto
        fecha.setText(formattedDate);
    }

    private void Posthb(String rnombre, String rarea, String restado, String rdescripcion, String rfecha) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", rnombre);
        map.put("area", rarea);
        map.put("estado", restado);
        map.put("descripcion", rdescripcion);
        map.put("fecha", rfecha);

        mfirestore.collection("hbdb").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Registro Existoso", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al Ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
