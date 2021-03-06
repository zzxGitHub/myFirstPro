package com.example.demo.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Content;
import com.example.demo.service.ElasticSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void addInfo(Content con) {
		// TODO Auto-generated method stub
		IndexRequest indexRequest = new IndexRequest("firstesindex", "news",
				UUID.randomUUID().toString().replace("-", ""));
		String json = null;
		try {
			json = mapper.writeValueAsString(con);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 写入完成立即刷新
		indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
		indexRequest.source(json, XContentType.JSON);
		// 异步写入
		client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
			@Override
			public void onResponse(IndexResponse response) {
				log.info(response.getIndex() + "--" + response.getType() + "--" + response.getResult().getLowercase()
						+ " ：" + response.getId());
			}

			@Override
			public void onFailure(Exception e) {
				log.error("index Exception", e);
			}
		});
	}

	/**
	 * 创建index
	 */
	@Override
	public void addIndex(Content con) {
		CreateIndexRequest request = new CreateIndexRequest("zzxindex");
		request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
		try {
			CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
			boolean acknowledged = createIndexResponse.isAcknowledged();
			log.info("createIndex " + "zzxIndex" + ": {}", acknowledged);
			// boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除index
	 */
	@Override
	public void deleteIndex(String indexName) {
		DeleteIndexRequest request = new DeleteIndexRequest(indexName);
		try {
			AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
			boolean acknowledged = deleteIndexResponse.isAcknowledged();
			log.info("deleteIndex " + indexName + ": {}", acknowledged);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

	@Override
	public void putMapping(String indexName) {
		PutMappingRequest request = new PutMappingRequest("zzxindex");
		InputStream inputStream = this.getClass().getResourceAsStream("/mapping/mapping.json");
		try {
			String mapping = IOUtils.toString(inputStream, "UTF-8");
			request.type("zzxtype");
			request.source(mapping, XContentType.JSON);
			AcknowledgedResponse putMappingResponse = client.indices().putMapping(request, RequestOptions.DEFAULT);
			boolean acknowledged = putMappingResponse.isAcknowledged();
			log.info("putmapping" + indexName + ":{}", acknowledged);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
