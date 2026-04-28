package com.ihor.artist_portfolio_server.service;


import com.ihor.artist_portfolio_server.dto.CommissionTopicDTO;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.CommissionTopicMapper;
import com.ihor.artist_portfolio_server.model.CommissionTopic;
import com.ihor.artist_portfolio_server.repository.CommissionTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionTopicService {
    private final CommissionTopicRepository commissionTopicRepository;
    private final CommissionTopicMapper commissionTopicMapper;

    public List<CommissionTopic> getAllCommissionTopics(){
        return commissionTopicRepository.findAll();
    }

    public CommissionTopic getCommissionTopicById(String id){
        return commissionTopicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Commission topic not found"));
    }

    public CommissionTopic createCommissionTopic(CommissionTopicDTO commissionTopicDTO){
        CommissionTopic commissionTopic = commissionTopicMapper.toModel(commissionTopicDTO);
        return commissionTopicRepository.save(commissionTopic);
    }

    public void deleteCommissionTopic(String id){
        commissionTopicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Commission topic not found"));
        commissionTopicRepository.deleteById(id);
    }

    public CommissionTopic updateCommissionTopic(String id,CommissionTopicDTO commissionTopicDTO){
        CommissionTopic commissionTopic = commissionTopicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Commission topic not found"));
        commissionTopicMapper.updateModel(commissionTopicDTO, commissionTopic);
        return commissionTopicRepository.save(commissionTopic);
    }
}
