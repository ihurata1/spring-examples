package com.hurata.repository;

import com.hurata.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

//Repository'yi annotation olarak eklememize gerek yok extends ettiğimiz interface bunu söylüyor.
public interface UserRepository extends MongoRepository<User, String /*Primary Key*/> {

}
