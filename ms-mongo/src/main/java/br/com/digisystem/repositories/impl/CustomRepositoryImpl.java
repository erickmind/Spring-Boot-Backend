package br.com.digisystem.repositories.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.repositories.CustomRepository;

@Repository
public class CustomRepositoryImpl implements CustomRepository{
	
	@Autowired
	private MongoTemplate mongoTemp;
	
	@Override
	public UserEntity updateUser(String id, String name) {

		Query query = new Query();
		
		// {name: <name>}
		query.addCriteria(Criteria.where("id").is(id));
	
		Update update = new Update();
		update.set("name", name);
		
		mongoTemp.findAndModify(query, update, UserEntity.class);
		
		return mongoTemp.findOne(query, UserEntity.class);
	}
}	