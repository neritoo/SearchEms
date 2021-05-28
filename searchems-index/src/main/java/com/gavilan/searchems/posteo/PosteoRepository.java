package com.gavilan.searchems.posteo;

import com.gavilan.searchems.posteo.entities.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteoRepository extends JpaRepository<Posteo, String> {

}
