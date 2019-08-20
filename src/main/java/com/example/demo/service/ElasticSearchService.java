package com.example.demo.service;

import com.example.demo.model.Content;

public interface ElasticSearchService {

	void addInfo(Content con);

	void addIndex(Content con);

	void deleteIndex(String indexName);

}
