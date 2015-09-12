package org.androidLost.server.utils.service.interfaces;

import java.io.Serializable;
import org.androidLost.server.utils.BaseEntity;

public interface IGenericService<T extends BaseEntity<PK>, PK extends Serializable> {
/*
	@GET
	public List<T> findAll();

	@POST
	public void insert(Object jsonEntityObject);

	@POST 
	@Path(value = "/delete")
	public void delete(Object jsonEntityObject);

	@POST 
	@Path(value = "/update")
	public void update(Object jsonEntityObject);
*/
}
