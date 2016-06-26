package com.theironyard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AlbumRepository extends CrudRepository<Album, Integer> {


}

