package org.androidLost.server.utils.service.interfaces;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.androidLost.server.utils.returnObject;
import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;
import org.androidLost.server.utils.service.ServiceConstants;
import org.androidLost.server.utils.service.ServiceName;
import org.androidLost.server.utils.service.beans.request.Usuario_AparelhoRequest;
import org.androidLost.server.utils.service.beans.request.HistoricoLocalizacaoRequest;
import org.androidLost.server.utils.service.beans.response.AparelhoResponse;
import org.androidLost.server.utils.service.beans.response.ListAparelhosResponse;
import org.androidLost.server.utils.service.beans.response.LocalizacaoResponse;
import org.androidLost.server.utils.service.beans.response.TokenResponse;
import org.androidLost.server.utils.service.beans.response.Usuario_AparelhoResponse;

@Path(value = ServiceName.USUARIO_APARELHO_SERVICE)
@Produces(ServiceConstants.MEDIA_TYPE)
@Consumes(ServiceConstants.MEDIA_TYPE)
public interface IUsuario_AparelhoService extends IGenericService<Usuario_AparelhoEntity, Long> {

	@POST
	@Path(value = "/retornarAparelho")
	public Usuario_AparelhoResponse retornarAparelho(Usuario_AparelhoRequest usuario_AparelhoRequest);
	
	@POST
	@Path(value = "/retornarConfiguracoes")
	public Usuario_AparelhoResponse retornarConfiguracoes(Usuario_AparelhoRequest usuario_AparelhoRequest);
	
	@POST
	@Path(value = "/atualizaConfiguracoes")
	public Usuario_AparelhoResponse atualizaConfiguracoes(Usuario_AparelhoRequest usuario_AparelhoRequest);

	@POST
	@Path(value = "/listarAparelhos")
	public ListAparelhosResponse listarAparelhos(TokenEntity tokenEntity);

	@POST
	@Path(value = "/listarHistorico")
	public returnObject listarHistorico(long usuario, long aparelho, Date inicio, Date fim, String token);
	
	@POST
	@Path(value = "/ultimaLocalizacao")
	public LocalizacaoResponse ultimaLocalizacao(Usuario_AparelhoRequest usuario_AparelhoRequest);

	@POST
	@Path(value = "/inserirAparelho")
	public AparelhoResponse inserirAparelho(Usuario_AparelhoRequest usuario_AparelhoRequest);

	@POST
	@Path(value = "/inserirHistorico")
	public TokenResponse inserirHistorico(HistoricoLocalizacaoRequest historicoLocalizacaoRequest);

	@POST
	@Path(value = "/inativarAparelho")
	public returnObject inativarAparelho(long usuario, String IMEI, String token);

}
