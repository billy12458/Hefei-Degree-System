package com.school.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResult;
import com.school.springboot.dto.SearchDTO;
import com.meilisearch.sdk.exceptions.MeilisearchException;

@Service(value = "meiliSearchService")
public class MeiliSearchService {
    
    @Autowired
    private Index userIndex;

    public SearchResult getUserInfoSearchResult(SearchDTO searchDTO) throws MeilisearchException {
        try{
            return userIndex.search(new SearchRequest(searchDTO.getContent()));
        }catch(Exception exception) {
            exception.printStackTrace();
        }return null;
    }
}
