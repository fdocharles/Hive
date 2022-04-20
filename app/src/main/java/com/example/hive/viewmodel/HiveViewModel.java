package com.example.hive.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.hive.model.HiveUser;
import com.example.hive.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HiveViewModel extends ViewModel {
    private static String TAG = "com.example.hive.viewmodel.HiveViewModel";

    private boolean isLoginSuccessful;
    //stores whether user isLoggedIn or not
    private boolean isUserLoggedIn = false;
    private HiveUser user;

    //Stores the type of user using the app
    private UserType loggedInUserType = UserType.Customer;

    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

/*    public void setUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }
*/
    public UserType getLoggedInUserType() {
        return loggedInUserType;
    }

  /*  public void setLoggedInUserType(UserType userType) {
        this.loggedInUserType = userType;
    }*/


}
