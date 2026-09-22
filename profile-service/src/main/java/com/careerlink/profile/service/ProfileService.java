package com.careerlink.profile.service;

import com.careerlink.profile.entity.Profile;
import com.careerlink.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Profile> getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile updateProfile(Long id, Profile profile) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setUserId(profile.getUserId());
        existingProfile.setName(profile.getName());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setPhone(profile.getPhone());
        existingProfile.setSkills(profile.getSkills());
        existingProfile.setEducation(profile.getEducation());
        existingProfile.setExperience(profile.getExperience());

        return profileRepository.save(existingProfile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}