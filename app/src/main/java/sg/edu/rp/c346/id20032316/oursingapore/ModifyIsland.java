package sg.edu.rp.c346.id20032316.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class ModifyIsland extends AppCompatActivity {

    EditText etID, etName, etDescription, etSize;
    RatingBar ratingBar;
    Button btnUpdate, btnDelete, btnCancel;
    Island islands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_island);

        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etSize = findViewById(R.id.etSize);
        ratingBar = findViewById(R.id.ratingBar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        islands = (Island) intent.getSerializableExtra("island");

        etID.setText(islands.get_id() + "");
        etID.setEnabled(false);
        etName.setText(islands.getName());
        etDescription.setText(islands.getDescription());
        etSize.setText(islands.getSquareKm() + "");
        ratingBar.setRating(islands.getStars());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(ModifyIsland.this);

                islands.setName(etName.getText().toString());
                islands.setDescription(etDescription.getText().toString());
                islands.setSquareKm(Integer.parseInt(etSize.getText().toString()));
                float numStar = ratingBar.getRating();
                islands.setStars(numStar);
                dbh.updateIsland(islands);
                finish();
            }
        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyIsland.this);
//                myBuilder.setTitle("Danger");
//                myBuilder.setMessage("Are you sure you want to delete the island");
//                myBuilder.setCancelable(false);
//
//            }
//        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyIsland.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the island \n" + etName.getText().toString());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Cancel", null);

                myBuilder.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyIsland.this);
                        dbh.deleteIsland(islands.get_id());
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyIsland.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("do not discard", null);

                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}