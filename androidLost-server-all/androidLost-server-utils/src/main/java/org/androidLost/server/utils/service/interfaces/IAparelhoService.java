package org.androidLost.server.utils.service.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.androidLost.server.utils.entities.AparelhoEntity;
import org.androidLost.server.utils.service.ServiceConstants;
import org.androidLost.server.utils.service.ServiceName;

@Path(value = ServiceName.APARELHO_SERVICE)
@Produces(ServiceConstants.MEDIA_TYPE)
@Consumes(ServiceConstants.MEDIA_TYPE)
public interface IAparelhoService extends IGenericService<AparelhoEntity, Long> {
	
}
