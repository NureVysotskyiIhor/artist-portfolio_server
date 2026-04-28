package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.CommissionRequestCreateDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestUpdateArtistDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestUpdateDTO;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.CommissionRequestMapper;
import com.ihor.artist_portfolio_server.model.CommissionRequest;
import com.ihor.artist_portfolio_server.model.CommissionTopic;
import com.ihor.artist_portfolio_server.repository.ArtistRepository;
import com.ihor.artist_portfolio_server.repository.CommissionRequestRepository;
import com.ihor.artist_portfolio_server.repository.CommissionTopicRepository;
import com.ihor.artist_portfolio_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionRequestService {

    private final CommissionRequestRepository commissionRequestRepository;
    private final UserRepository userRepository;
    private final CommissionRequestMapper  commissionRequestMapper;
    private final ArtistRepository artistRepository;
    private final CommissionTopicRepository commissionTopicRepository;

    public List<CommissionRequest> getCommissionRequestsByUserId(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return commissionRequestRepository.findByUserId(userId);
    }

    public List<CommissionRequest> getAllCommissionRequests() {
        return commissionRequestRepository.findAll();
    }

    public CommissionRequest getCommissionRequestsById(String id) {
        return commissionRequestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Request not found"));
    }

    public CommissionRequest createCommissionRequest(CommissionRequestCreateDTO commissionRequestDTO) {
        userRepository.findById(commissionRequestDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        CommissionTopic forName=  commissionTopicRepository.findById(commissionRequestDTO.getTopicId()).orElseThrow(() -> new EntityNotFoundException("Commission topic not found"));

        CommissionRequest commissionRequest = commissionRequestMapper.toModel(commissionRequestDTO);

        commissionRequest.setTopicName(forName.getName());

        if (commissionRequestDTO.getLatitude() != null && commissionRequestDTO.getLongitude() != null) {
            commissionRequest.setLocation(new GeoJsonPoint(commissionRequestDTO.getLongitude(), commissionRequestDTO.getLatitude()));
        }

        return commissionRequestRepository.save(commissionRequest);
    }

    public CommissionRequest updateCommissionRequest(CommissionRequestUpdateDTO  commissionRequestDTO, String id) {
       CommissionRequest commissionRequest = commissionRequestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Request not found"));
        commissionRequestMapper.updateModel(commissionRequestDTO, commissionRequest);
        return  commissionRequestRepository.save(commissionRequest);
    }

    public CommissionRequest updateCommissionRequestByArtist(CommissionRequestUpdateArtistDTO commissionRequestDTO, String id) {
        CommissionRequest commissionRequest = commissionRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));
        commissionRequestMapper.updateModel(commissionRequestDTO, commissionRequest);
        return commissionRequestRepository.save(commissionRequest);
    }

    public void deleteCommissionRequest(String id) {
        commissionRequestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Request not found"));
        commissionRequestRepository.deleteById(id);
    }

    public List<CommissionRequest> findByRadiusKm(Double latitude, Double longitude, Double radiusKm) {
        Point point = new Point(longitude, latitude); // MongoDB Point(x=lon, y=lat)
        Distance distance = new Distance(radiusKm, Metrics.KILOMETERS);
        return commissionRequestRepository.findByLocationNear(point, distance);
    }
}