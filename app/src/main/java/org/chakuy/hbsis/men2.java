package org.chakuy.hbsis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.BuildConfig;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class men2 extends AppCompatActivity {

    private static final String TAG = "men2";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mtpdbRef = db.collection("hbdb");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men2);

        LinearLayout linearLayoutAgregar = findViewById(R.id.linearLayoutAgregar);
        LinearLayout listado = findViewById(R.id.listado);
        LinearLayout descargar = findViewById(R.id.descargar);
        LinearLayout cerrarsesion = findViewById(R.id.cerrarsesion);


        linearLayoutAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(men2.this, insertar_registro.class);
            startActivity(intent);
        });

        listado.setOnClickListener(v -> {
            Intent intent = new Intent(men2.this, listapr.class);
            startActivity(intent);
        });

        cerrarsesion.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            finish();
            startActivity(new Intent(men2.this, login.class));
        });


/*
        descargar.setOnClickListener(view -> {
            List<MTPData> dataList = new ArrayList<>();
            Query query = mtpdbRef;
            query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    MTPData data = document.toObject(MTPData.class);
                    dataList.add(data);
                }

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Datos MTP");

                int rowNum = 0;
                for (MTPData data : dataList) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(data.getNombre());
                    row.createCell(1).setCellValue(data.getApellido());
                    row.createCell(2).setCellValue(data.getFecha());
                    row.createCell(3).setCellValue(data.getTipo());
                    row.createCell(4).setCellValue(data.getDetalle());
                    row.createCell(5).setCellValue(data.getPedido());
                    row.createCell(6).setCellValue(data.getCantidad());
                    row.createCell(7).setCellValue(data.getPrecio());
                    row.createCell(8).setCellValue(data.getUbicacion());
                    row.createCell(9).setCellValue(data.getColor());
                }

                String filePath = getExternalFilesDir(null).getPath().toString() + "/datos_mtp.xlsx";
                try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    workbook.write(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri fileUri = FileProvider.getUriForFile(men2.this, BuildConfig.APPLICATION_ID + ".provider", new File(filePath));
                grantUriPermission(getPackageName(), fileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.ms-excel");
                intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(men2.this, BuildConfig.APPLICATION_ID + ".provider", new File(filePath)));
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(Intent.EXTRA_STREAM, fileUri);
                startActivity(Intent.createChooser(intent, "Compartir archivo"));


            });
        });



    }





    private String getCurrentDateWithoutTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getNextDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date currentDate = sdf.parse(date);
            long nextDateMillis = currentDate.getTime() + 24 * 60 * 60 * 1000; // Add 24 hours in milliseconds
            Date nextDate = new Date(nextDateMillis);
            return sdf.format(nextDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

 */
    }
}
