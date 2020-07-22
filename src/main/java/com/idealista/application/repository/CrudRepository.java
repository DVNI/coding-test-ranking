package com.idealista.application.repository;

import com.idealista.domain.persistence.AdVO;

import java.util.List;

public interface CrudRepository {

    List<AdVO> findAll();

    List<AdVO> saveAll(List<AdVO> ads);

}
