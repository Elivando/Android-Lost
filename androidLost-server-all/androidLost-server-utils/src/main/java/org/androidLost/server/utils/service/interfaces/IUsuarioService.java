package org.androidLost.server.utils.service.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.androidLost.server.utils.returnObject;
import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.UsuarioEntity;
import org.androidLost.server.utils.service.ServiceConstants;
import org.androidLost.server.utils.service.ServiceName;
import org.androidLost.server.utils.service.beans.response.TokenResponse;

@Path(value = ServiceName.USUARIO_SERVICE)
@Produces(ServiceConstants.MEDIA_TYPE)
@Consumes(ServiceConstants.MEDIA_TYPE)
public interface IUsuarioService extends IGenericService<UsuarioEntity, Long> {

	@POST
	@Path(value = "/cadastraUsuario")
	public TokenResponse cadastraUsuario(UsuarioEntity usuario);

	@POST
	@Path(value = "/atualizaUsuario")
	public TokenEntity atualizaUsuario(TokenEntity Token);

	@POST
	@Path(value = "/inativaUsuario")
	public returnObject inativaUsuario(TokenEntity Token);

}
