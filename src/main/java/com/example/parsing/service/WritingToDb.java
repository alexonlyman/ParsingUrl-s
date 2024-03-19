package com.example.parsing.service;

import com.example.parsing.entity.TitleEntity;
import com.example.parsing.repository.Repository;

import org.springframework.stereotype.Service;

/*/
database writing service
 */
@Service
public class WritingToDb {

    private final Repository repository;

    public WritingToDb(Repository repository) {
        this.repository = repository;
    }
    public void writeToDatabase(String titles) {
        if (titles != null && !titles.isBlank()) {
            TitleEntity titleEntity = new TitleEntity();
            titleEntity.setTitle(titles);
            repository.save(titleEntity);
        }
    }

}
