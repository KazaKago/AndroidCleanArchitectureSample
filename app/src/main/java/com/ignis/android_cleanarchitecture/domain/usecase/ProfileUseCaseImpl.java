package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.model.ProfileModel;
import com.ignis.android_cleanarchitecture.domain.repository.ProfileRepository;

import java.util.List;

/**
 * Profile UseCase Implement
 *
 * @author Kensuke
 */
public class ProfileUseCaseImpl implements ProfileUseCase {

    private ProfileRepository profileRepository;

    public ProfileUseCaseImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileModel> getProfileList() {
        return profileRepository.selectAll();
    }

}
