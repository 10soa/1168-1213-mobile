    package com.example.andao_apk.Utilisateur;

    import android.Manifest;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.graphics.Bitmap;
    import android.net.Uri;
    import android.os.Bundle;
    import android.os.Environment;
    import android.provider.MediaStore;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.ProgressBar;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.Volley;
    import com.example.andao_apk.Constante.Constante;
    import com.example.andao_apk.MainActivity;
    import com.example.andao_apk.Multimedia.Onglet.OngletActivity;
    import com.example.andao_apk.R;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.android.material.floatingactionbutton.FloatingActionButton;
    import com.google.android.material.textfield.TextInputEditText;
    import com.google.firebase.FirebaseApp;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.storage.FirebaseStorage;
    import com.google.firebase.storage.OnProgressListener;
    import com.google.firebase.storage.StorageReference;
    import com.google.firebase.storage.UploadTask;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Locale;

    public class UserShareActivity extends AppCompatActivity {

        private static final int REQUEST_IMAGE_CAPTURE = 1;
        private String capturedImagePath;
        FirebaseStorage storage;
        String urlImage;
        StorageReference storageReference;
        ImageView usershareImage;
        private Uri image;
        private ShareClass shareClass;
        private final int PICK_IMAGE_REQUEST = 71;
        private String localisationValue;
        private String noteValue;
        private String descriptionValue;
        private String libelleValue;

        private ProgressBar progressBar;

        private String sessionUtilisateur="64c6748bef6335afe586e7c2";
        private RequestQueue requestQueue;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_share);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            FirebaseApp.initializeApp(UserShareActivity.this);
            storageReference=FirebaseStorage.getInstance().getReference();


            Button publierButton = findViewById(R.id.publier_bouton);

            usershareImage = findViewById(R.id.usershare_image);
            FloatingActionButton takePhotosFab = findViewById(R.id.usershare_takephotos);

            takePhotosFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(UserShareActivity.this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(UserShareActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                    } else {
                        openCamera();
                    }
                }
            });

            publierButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInAnonymously();
                    System.out.println(urlImage);
                }
            });

            ImageView imageBack=findViewById(R.id.partage_user_back);
            imageBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        private void openCamera(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                capturedImagePath = saveImageToStorage(imageBitmap);
                Log.d("Image : ",capturedImagePath);
                usershareImage.setImageBitmap(imageBitmap);
            }
        }

        private String saveImageToStorage(Bitmap imageBitmap) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + ".jpg";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            File imageFile = new File(storageDir, imageFileName);

            try {
                FileOutputStream outStream = new FileOutputStream(imageFile);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.flush();
                outStream.close();

                return imageFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void uploadImage() {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if (currentUser != null) {
                image = Uri.fromFile(new File(capturedImagePath));
                StorageReference imageRef = storageReference.child("images/" + image.getLastPathSegment());
                UploadTask uploadTask = imageRef.putFile(image);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUri) {
                                urlImage= downloadUri.toString();
                                TextInputEditText libelleEditText = findViewById(R.id.share_libelle);
                                TextInputEditText descriptionEditText = findViewById(R.id.share_description);
                                TextInputEditText noteEditText = findViewById(R.id.share_note);
                                TextInputEditText localisationEditText = findViewById(R.id.share_localisation);
                                libelleValue = libelleEditText.getText().toString();
                                descriptionValue = descriptionEditText.getText().toString();
                                noteValue = noteEditText.getText().toString();
                                localisationValue = localisationEditText.getText().toString();
                                Toast.makeText(UserShareActivity.this, "Image upload successfully", Toast.LENGTH_SHORT).show();
                                postData(libelleValue,descriptionValue,localisationValue,Integer.valueOf(noteValue),urlImage);
                                Intent intent = new Intent(UserShareActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserShareActivity.this, "Erreur lors du téléchargement de l'image", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        // Suivre la progression du téléchargement ici
                        double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        Log.d("Progress", "Upload is " + progress + "% done");
                    }
                });
            } else {
                Toast.makeText(UserShareActivity.this, "Veuillez vous connecter avant de télécharger une image", Toast.LENGTH_SHORT).show();
            }
        }


        private void signInAnonymously() {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UserShareActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                uploadImage();
                            } else {
                                Toast.makeText(UserShareActivity.this, "Échec de la connexion anonyme", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        private void postData(String lib,String desc,String loc,int note,String img) {
            requestQueue = Volley.newRequestQueue(this);
            JSONObject body = new JSONObject();
            try {
                body.put("libelle", lib);
                body.put("description", desc);
                body.put("localisation", loc);
                body.put("note", note);
                body.put("image", img);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            String url = Constante.api_url +"Utilisateur/utilisateurs/"+sessionUtilisateur;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, body,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Traitement de la réponse en cas de succès
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonObjectRequest);
        }

    }
