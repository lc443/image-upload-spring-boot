package com.leron.multipartfile.repository;

import com.leron.multipartfile.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ImageStorageRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String imageName);
}
