package edu.co.eci.roomandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
import java.util.Random;

import edu.co.eci.roomandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        UserDao userDao = db.userDao();

        binding.add.setOnClickListener(view -> {
            String textName = binding.editTextText.getText().toString();
            String textLastName = binding.editTextText2.getText().toString();
            //Create User
            Log.d(TAG, "onCreate: " + textName + " " + textLastName);
            if (!textName.equals("") && !textLastName.equals("")) {
                userDao.insertAll(new User(textName, textLastName));
                binding.errorText.setText("");
            } else {
                binding.errorText.setText("Llene los espacios por favor");
            }
        });

        binding.button2.setOnClickListener(view -> {
            String textName = binding.searchName.getText().toString();
            String textLastName = binding.searchLastName.getText().toString();
            //Log.d(TAG, "onCreate: " + textName + " " + textLastName);
            try {
                if (!textName.equals("") && !textLastName.equals("")) {
                    //Log.d(TAG, "onCreate: " + userDao.getAll().toString());
                    User user = userDao.findByName(textName, textLastName);
                    //Log.d(TAG, "onCreate: " + userDao.findByName(textName, textLastName));
                    binding.namePerson.setText(user.firstName + " " + user.lastName);
                } else {
                    binding.namePerson.setText("No se encontro la persona");
                }
            } catch (Exception e) {
                binding.namePerson.setText("No se encontro la persona");
            }
        });
    }
}
