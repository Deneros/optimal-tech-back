package org.nicolas.optimaltech.repository;

import org.nicolas.optimaltech.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
