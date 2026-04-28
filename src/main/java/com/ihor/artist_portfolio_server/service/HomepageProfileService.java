package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.HomepageProfileCreateDTO;
import com.ihor.artist_portfolio_server.dto.HomepageProfileUpdateDTO;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.HomepageProfileMapper;
import com.ihor.artist_portfolio_server.model.HomepageProfile;
import com.ihor.artist_portfolio_server.repository.HomepageProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomepageProfileService {

    private final HomepageProfileRepository homepageProfileRepository;
    private final HomepageProfileMapper homepageProfileMapper;

    public HomepageProfile getHomepageProfile() {
        return homepageProfileRepository.findAll().stream().findFirst().orElse(null);
    }

    public HomepageProfile createHomepageProfile(HomepageProfileCreateDTO dto) {
        HomepageProfile profile = homepageProfileMapper.toModel(dto);
        return homepageProfileRepository.save(profile);
    }

    public HomepageProfile updateHomepageProfile(HomepageProfileUpdateDTO dto) {
        HomepageProfile existingProfile = homepageProfileRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        homepageProfileMapper.updateModel(dto, existingProfile);
        return homepageProfileRepository.save(existingProfile);
    }
}
