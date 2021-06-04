package com.example.androidapp.ui.settings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.Profile;
import com.example.androidapp.repository.ProfileRepository;
import com.example.androidapp.repository.UserRepository;

import java.util.List;

public class SettingsActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private ProfileRepository profileRepository;

    public SettingsActivityViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
        profileRepository = ProfileRepository.getInstance();
    }

    public void signOut() {
        userRepository.signOut();
    }

    public String getCurrentEmail() {
        return userRepository.getCurrentUser().getValue().getEmail();
    }

    public LiveData<List<Profile>> getProfiles() {
        profileRepository.requestProfiles();
        return profileRepository.getProfiles();
    }

    public LiveData<Integer> getActiveProfile() {
        profileRepository.requestActiveProfile();
        return profileRepository.getActiveProfileId();
    }

    public void addProfile(Profile newProfile) {
        profileRepository.addProfile(newProfile);
    }

    public void updateProfile(Profile updatedProfile) {
        profileRepository.updateProfile(updatedProfile);
    }

    public void deleteProfile(int profileId) {
        profileRepository.deleteProfile(profileId);
    }

    public void setActiveProfile(int profileId) {
        profileRepository.setActiveProfile(profileId);
    }
}
