package com.example.expensetracker;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static android.app.Activity.RESULT_OK;

public class Profile extends Fragment {
TextView changeprofile,setlimit,delacc,changepass;
EditText username,email;
Button btnemail,btnusername;
ProgressDialog dialog;
    FirebaseStorage storage;
    StorageReference reference;
    FirebaseAuth fAuth;
    public Uri imageuri;
    ImageView profilepic;
    //private final int PICK_IMAGE_REQUEST = 71;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            imageuri = data.getData();
           uploadPicture(imageuri);
        }
    }
    private void uploadPicture(Uri uri) {
        //IMPORTANT : Add Firebase Storage dependency
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Uploading image");
        pd.show();
        final StorageReference fileRef = reference.child("users/"+fAuth.getCurrentUser().getUid()+"/profileimgs.jpg");
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(),"Image uploaded successfully",Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        pd.dismiss();
                        Picasso.get().load(uri).into(profilepic);
                        startActivity(new Intent(getActivity(),Home.class));
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getContext(),"Image upload failed",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                //double perc =(100.0 * (taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()));
                pd.setMessage("Please wait...");

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_profile, container, false);
changeprofile=v.findViewById(R.id.changeprofile);
setlimit=v.findViewById(R.id.setexpense);
delacc=v.findViewById(R.id.delete);
changepass=v.findViewById(R.id.changepass);

username=v.findViewById(R.id.user);
email=v.findViewById(R.id.em);
btnusername=v.findViewById(R.id.btnusername);
       storage = FirebaseStorage.getInstance();
        reference = storage.getReference();
        fAuth = FirebaseAuth.getInstance();
        profilepic=v.findViewById(R.id.profile_pic);
        StorageReference profileref = reference.child("users/"+fAuth.getCurrentUser().getUid()+"/profileimgs.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilepic);

            }
        });
        final String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        final FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("users").document(id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot documentSnapshot=task.getResult();
                        if ((task.isSuccessful())) {
                            if (documentSnapshot.exists()) {
                                String useremail = documentSnapshot.getString("email");
                                email.setText(useremail);
                            }
                        }
                    }
                });

btnusername.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String usname=username.getText().toString();
      DocumentReference p=db.collection("users").document(id);
      p.update("username",usname);
      Toast.makeText(getActivity(),"Username changed successfully",Toast.LENGTH_SHORT).show();
      Intent intent=new Intent(getActivity(),Home.class);
      startActivity(intent);


    }
});


changepass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        changepassword change=new changepassword();
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,change);
        fragmentTransaction.addToBackStack(null).commit();
    }
});
delacc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete account?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (user!=null){
                            dialog=new ProgressDialog(getActivity());
                            ((ProgressDialog) dialog).setMessage("Deactivating...Please Wait");
                            ((ProgressDialog) dialog).show();
                            FirebaseFirestore db=FirebaseFirestore.getInstance();
                            String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            db.collection("users").document(id)
                                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                        Toast.makeText(getActivity(),"Account deactivated",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getActivity(),Login.class));
                                    }
                                    else {
                                        Toast.makeText(getActivity(),"Account could not be deactivated",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }).setNegativeButton("CANCEL",null);
        AlertDialog alert= builder.create();
        alert.show();

    }
});
setlimit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,new SetLimit());
        fragmentTransaction.addToBackStack(null).commit();

    }
});
changeprofile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(intent,1);
       // startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
});
       return v;
    }
}
