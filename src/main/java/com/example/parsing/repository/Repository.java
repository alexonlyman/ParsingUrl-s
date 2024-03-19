package com.example.parsing.repository;

import com.example.parsing.entity.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<TitleEntity,Integer> {

}
