package org.sid.inventoryservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ProductRepo extends JpaRepository<Product, Long> {

}
