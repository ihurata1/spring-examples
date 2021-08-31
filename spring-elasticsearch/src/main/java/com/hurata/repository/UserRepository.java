package com.hurata.repository;

import com.hurata.entity.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User,String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"id\": \"?0\"}}]}}") //Elastic Search Query yöntemi ile arama
    List<User> getByCustomQuery(String search);

    List<User> findByNameOrSurnameLike(String name, String surname); //Spring data'nın vermiş olduğu yöntem ile arama

}
