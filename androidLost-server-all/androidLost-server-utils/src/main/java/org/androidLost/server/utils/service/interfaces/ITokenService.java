package org.androidLost.server.utils.service.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.service.ServiceConstants;
import org.androidLost.server.utils.service.ServiceName;
import org.androidLost.server.utils.service.beans.request.UsuarioEntityRequest;
import org.androidLost.server.utils.service.beans.response.TokenResponse;

@Path(value = ServiceName.TOKEN_SERVICE) // String que indica a URI utilizada pelo serviço.
@Produces(ServiceConstants.MEDIA_TYPE) // Tipo de dado utilizado no retorno do serviço (JSON).
@Consumes(ServiceConstants.MEDIA_TYPE) // Tipo de dado utilizado para receber informações (JSON)
public interface ITokenService extends IGenericService<TokenEntity, Long> {
	@POST // Operação HTTP que irá consumir o método. 
	@Path(value = "/gerarToken") // padrão URI que será usado para consumo do método.
	public TokenResponse gerarToken(UsuarioEntityRequest usuarioEntityRequest);

	@POST
	@Path(value = "/validaToken")
	public TokenResponse validaToken(TokenEntity tokenEntity);

	@POST
	@Path(value = "/inativaToken")
	public TokenResponse inativaToken(TokenEntity tokenEntity);
}
