package org.androidLost.server.service.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.androidLost.server.utils.BaseEntity;
import org.androidLost.server.utils.service.interfaces.IGenericService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractGenericService<T extends BaseEntity<PK>, PK extends Serializable>
		implements IGenericService<T, PK> {

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	private Logger logger = Logger.getLogger(this.getClass());

	private Class<T> entityType;

	private Class<PK> keyType;

	static {
		jsonMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@SuppressWarnings("unchecked")
	public AbstractGenericService() {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(String.format("Creating a REST service [%s].",
					this.getClass().getGenericSuperclass()));
		}

		try {
			ParameterizedType parameterizedType = (ParameterizedType) this
					.getClass().getGenericSuperclass();
			Type[] genericTypes = parameterizedType.getActualTypeArguments();
			entityType = (Class<T>) genericTypes[0];
			setKeyType((Class<PK>) genericTypes[1]);
		} catch (RuntimeException e) {
			this.logger.error(e.getMessage(), e);
			// Re-throw the exception.
			throw e;
		}
	}

	protected abstract JpaRepository<T, PK> getGenericRepository();

	/*
	 * @Override public void insert(Object jsonEntityObject) { T entityObject =
	 * jsonMapper.convertValue(jsonEntityObject, entityType);
	 * 
	 * this.getGenericRepository().saveAndFlush(entityObject); }
	 * 
	 * @Override public void delete(Object jsonEntityObject) { T entityObject =
	 * jsonMapper.convertValue(jsonEntityObject, entityType);
	 * 
	 * this.getGenericRepository().delete(entityObject.getId()); }
	 * 
	 * @Override public void update(Object jsonEntityObject) { T entityObject =
	 * jsonMapper.convertValue(jsonEntityObject, entityType);
	 * 
	 * this.getGenericRepository().save(entityObject); }
	 * 
	 * @Override public List<T> findAll() { return
	 * this.getGenericRepository().findAll(); }
	 */
	protected Class<PK> getKeyType() {
		return keyType;
	}

	protected void setKeyType(Class<PK> keyType) {
		this.keyType = keyType;
	}

	protected Class<T> getEntityType() {
		return entityType;
	}

	protected void setEntityType(Class<T> entityType) {
		this.entityType = entityType;
	}

}