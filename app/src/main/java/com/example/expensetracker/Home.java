package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseStorage storage;
    StorageReference reference;
    FirebaseUser currentuser;
ImageView g_profile;
TextView name,email;
View header;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logout:

                AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
                builder.setMessage("Are you sure you want to logout?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                signOut();
                            }
                        }).setNegativeButton("CANCEL",null);
                AlertDialog alert= builder.create();
                alert.show();
                break;
            case R.id.settings:
                TextView graph=findViewById(R.id.tooltitle);
                graph.setText("About App");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new graph()).addToBackStack(null).commit();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
return  true;
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    FirebaseAuth.getInstance().signOut(); // very important if you are using firebase.
                    Intent login_intent = new Intent(getApplicationContext(),Login.class);
                    login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // clear previous task (optional)
                    startActivity(login_intent);
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Home.this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Home.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        header=navigationView.getHeaderView(0);
        name=header.findViewById(R.id.name);
        email=header.findViewById(R.id.email);
        g_profile=header.findViewById(R.id.profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_home1()).commit();
        storage = FirebaseStorage.getInstance();
          reference=storage.getReference();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        StorageReference profileref = reference.child("users/"+fAuth.getCurrentUser().getUid()+"/profileimgs.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(g_profile);
            }
        });

        String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
         FirebaseFirestore db=FirebaseFirestore.getInstance();
         db.collection("users").document(id).get()
                 .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                         DocumentSnapshot documentSnapshot=task.getResult();
                         if ((task.isSuccessful())) {
                           if (documentSnapshot.exists()) {
                               String usertext = documentSnapshot.getString("username");
                               String useremail = documentSnapshot.getString("email");
                               name.setText(usertext);
                               email.setText(useremail);
                           }
                         }
                     }
                 });
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_home1()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                TextView home=findViewById(R.id.tooltitle);
                home.setText("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_home1()).addToBackStack(null).commit();
                break;
            case R.id.navigation_transaction:
                TextView addtrans=findViewById(R.id.tooltitle);
                addtrans.setText("Add Transaction");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new transaction()).addToBackStack(null).commit();
                break;
            case R.id.navigation_account_balance:
                TextView accbal=findViewById(R.id.tooltitle);
                accbal.setText("Contact us");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new account_balance()).addToBackStack(null).commit();
                break;
            case R.id.navigation_Summary:
                TextView summary=findViewById(R.id.tooltitle);
                summary.setText("Summary");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new summary()).addToBackStack(null).commit();
                break;
            case R.id.navigation_profile:
                TextView profile=findViewById(R.id.tooltitle);
                profile.setText("Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).addToBackStack(null).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* @Override
   public void onBackPressed() {
       // Fragment fragment=null;
        Toast.makeText(Home.this,"Double tap the Back button to exit",Toast.LENGTH_SHORT).show();
                        if(drawer.isDrawerOpen(GravityCompat.START)){
                            drawer.closeDrawer(GravityCompat.START);
                        } else{
                            AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
                            builder.setMessage("Are you sure you want to Exit?")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    }).setNegativeButton("CANCEL",null);
                            AlertDialog alert= builder.create();
                            alert.show();

                        }
    }*/
}

