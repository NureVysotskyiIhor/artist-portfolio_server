package com.ihor.artist_portfolio_server.service;

import com.ihor.artist_portfolio_server.dto.PaintingCreateDTO;
import com.ihor.artist_portfolio_server.dto.PaintingUpdateDTO;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import com.ihor.artist_portfolio_server.mapper.PaintingMapper;
import com.ihor.artist_portfolio_server.model.Painting;
import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import com.ihor.artist_portfolio_server.repository.FavoriteRepository;
import com.ihor.artist_portfolio_server.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaintingService {

    private final PaintingRepository paintingRepository;
    private final FavoriteRepository favoriteRepository;
    private final PaintingMapper paintingMapper;

    public List<Painting> getAllPaintings() {
        return paintingRepository.findAll();
    }

    public Painting getPaintingById(String id) {
        return paintingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Painting not found"));
    }

    public Painting updatePainting(String id, PaintingUpdateDTO paintingDTO) {
        Painting existingPainting = paintingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Painting not found"));
        paintingMapper.updateModel(paintingDTO, existingPainting);
        return paintingRepository.save(existingPainting);
    }

    public Painting createPainting(PaintingCreateDTO paintingCreateDTO) {
        Painting painting = paintingMapper.toModel(paintingCreateDTO);
        return paintingRepository.save(painting);
    }

    public void deletePainting(String id) {
        paintingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Painting not found"));
        favoriteRepository.deleteByPaintingId(id);
        paintingRepository.deleteById(id);
    }

    public List<Painting> getPaintingsByStatusAndPriceBetween(PaintingStatus status, Double priceMin, Double priceMax) {
        if (status != null && priceMin != null && priceMax != null) {
            return paintingRepository.findByStatusAndPriceBetween(status, priceMin, priceMax);
        }
        if (status != null && priceMin != null) {
            return paintingRepository.findByStatusAndPriceGreaterThanEqual(status, priceMin);
        }
        if (status != null && priceMax != null) {
            return paintingRepository.findByStatusAndPriceLessThanEqual(status, priceMax);
        }
        if (status != null) {
            return paintingRepository.findByStatus(status);
        }
        if (priceMin != null && priceMax != null) {
            return paintingRepository.findByPriceBetween(priceMin, priceMax);
        }
        if (priceMin != null) {
            return paintingRepository.findByPriceGreaterThanEqual(priceMin);
        }
        if (priceMax != null) {
            return paintingRepository.findByPriceLessThanEqual(priceMax);
        }
        return paintingRepository.findAll();
    }
}
