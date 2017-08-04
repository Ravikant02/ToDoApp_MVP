package com.ravikant.todo_mvp.presenters;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ravikant.todo_mvp.R;
import com.ravikant.todo_mvp.config.AppConfig;
import com.ravikant.todo_mvp.interfaces.SignUpView;
import com.ravikant.todo_mvp.models.User;
import com.ravikant.todo_mvp.views.ToDoApplication;

/**
 * Created by ravikant on 1/8/17.
 **/

public class SignUpPresenter {
    private SignUpView signUpView;
    private FirebaseAuth firebaseAuth;

    public SignUpPresenter(SignUpView signUpView){
        this.signUpView = signUpView;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void doSingUp(final String name, final String email, final String password, final Uri photoUrl){
        if (name.isEmpty() && email.isEmpty() && password.isEmpty()){
            signUpView.onErrorShow(ToDoApplication.getContext().getString(R.string.please_provide_required_details));
            return;
        }
        if (name.isEmpty()){
            signUpView.onErrorShow(ToDoApplication.getContext().getString(R.string.name_required));
            return;
        }
        if (email.isEmpty()){
            signUpView.onErrorShow(ToDoApplication.getContext().getString(R.string.email_required));
            return;
        }
        if (password.isEmpty()){
            signUpView.onErrorShow(ToDoApplication.getContext().getString(R.string.password_required));
            return;
        }
        if (photoUrl==null){
            signUpView.onErrorShow(ToDoApplication.getContext().getString(R.string.please_select_profile_image));
            return;
        }
        signUpView.onShowProgress(ToDoApplication.getContext().getString(R.string.hold_on_we_are_loading));
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ToDoApplication.getInstance().getCurrentActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user!=null) {
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                StorageReference reference = storage.getReference();
                                StorageMetadata metadata = new StorageMetadata.Builder()
                                        .setContentType("image/jpeg")
                                        .build();
                                UploadTask uploadTask = reference.child(AppConfig.FIREBASE_PROFILE_IMAGE_FOLDER_NAME + "/" + user.getUid()).putFile(photoUrl, metadata);
                                uploadTask
                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                Uri downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
                                                UserProfileChangeRequest request;
                                                if (downloadUrl!=null) {
                                                    request = new UserProfileChangeRequest.Builder()
                                                            .setDisplayName(name)
                                                            .setPhotoUri(downloadUrl)
                                                            .build();
                                                }else{
                                                    request = new UserProfileChangeRequest.Builder()
                                                            .setDisplayName(name)
                                                            .build();
                                                }
                                                user.updateProfile(request)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    signUpView.onSignUpSuccess();
                                                                }
                                                            }
                                                        });
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                            }else{
                                signUpView.onSignUpFailed(ToDoApplication.getContext().getString(R.string.error_while_sign_up));
                            }
                        }
                        else {
                            signUpView.onSignUpFailed(ToDoApplication.getContext().getString(R.string.error_while_sign_up));
                        }
                    }
                });
    }


    /*public void insertUserDetails(final User user){
        DatabaseReference databaseReference
    }*/
}
