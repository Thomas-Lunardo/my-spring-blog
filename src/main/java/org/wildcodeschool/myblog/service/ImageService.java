package org.wildcodeschool.myblog.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.CategoryDTO;
import org.wildcodeschool.myblog.dto.ImageDTO;
import org.wildcodeschool.myblog.exception.NoContentException;
import org.wildcodeschool.myblog.exception.ResourceNotFoundException;
import org.wildcodeschool.myblog.mapper.ImageMapper;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.Category;
import org.wildcodeschool.myblog.model.Image;
import org.wildcodeschool.myblog.repository.ImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageService(
            ImageRepository imageRepository,
            ImageMapper imageMapper
    ) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> getAllImages() {
        List<Image> images = imageRepository.findAll();

        if (images.isEmpty()) {
            throw new NoContentException("Aucune image trouvée.");
        }

        return images.stream().map(imageMapper::convertToDTO).collect(Collectors.toList());
    }

    public ImageDTO getImageById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'image avec l'id " + id + " n'a pas été trouvée."));

        if (image == null) {
            return null;
        }

        return imageMapper.convertToDTO(image);
    }

    public ImageDTO createImage(Image image) {

        image.setUrl(image.getUrl());

        Image savedImage = imageRepository.save(image);

        return imageMapper.convertToDTO(savedImage);
    }

    public ImageDTO updateImage(Long id, Image imageDetails) {

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'image avec l'id " + id + " n'a pas été trouvée."));

        if (image == null) {
            return null;
        }

        image.setUrl(imageDetails.getUrl());

        Image updatedImage = imageRepository.save(image);

        return imageMapper.convertToDTO(updatedImage);
    }

    public boolean deleteImage(Long id) {

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'image avec l'id " + id + " n'a pas été trouvée."));

        if (image == null) {
            return false;
        }

        imageRepository.delete(image);
        return true;
    }
}
