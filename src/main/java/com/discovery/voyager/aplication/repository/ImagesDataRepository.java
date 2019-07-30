package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.ImagesData;
import org.springframework.data.repository.CrudRepository;

public interface ImagesDataRepository extends CrudRepository<ImagesData,Long >{

    ImagesData findByName(String name);

}
