package org.androidLost.server.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.UsuarioEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;
import org.androidLost.server.utils.service.beans.request.UsuarioEntityRequest;
import org.androidLost.server.utils.service.beans.request.Usuario_AparelhoRequest;
import org.androidLost.server.utils.service.beans.response.LocalizacaoResponse;
import org.androidLost.server.utils.service.beans.response.TokenResponse;
import org.androidLost.server.utils.service.interfaces.ITokenService;
import org.androidLost.server.utils.service.interfaces.IUsuarioService;
import org.androidLost.server.utils.service.interfaces.IUsuario_AparelhoService;
import org.androidLost.server.web.utils.BaseBean;
import org.primefaces.context.RequestContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

@ManagedBean
@SessionScoped
@Named
public class ControlBean extends BaseBean {
	private static final long serialVersionUID = 2446848947384387934L;
	private static final String PLATAFORMA = "WEB";

	@Inject
	private ITokenService tokenService;

	@Inject
	private IUsuarioService usuarioService;

	@Inject
	private IUsuario_AparelhoService usuario_AparelhoService;

	private UsuarioBean usuario;
	private MapBean map;
	private List<Usuario_AparelhoEntity> aparelhos;
	private TokenEntity tokenEntity;

	@PostConstruct
	public void init() {
		if (this.usuario == null) {
			this.usuario = new UsuarioBean();
		}
		if (this.map == null) {
			this.map = new MapBean();
		}
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public MapBean getMap() {
		return map;
	}

	public void setMap(MapBean map) {
		this.map = map;
	}

	public List<Usuario_AparelhoEntity> getAparelhos() {
		return aparelhos;
	}

	public void setAparelhos(List<Usuario_AparelhoEntity> aparelhos) {
		this.aparelhos = aparelhos;
	}

	public TokenEntity getTokenEntity() {
		return tokenEntity;
	}

	public void setTokenEntity(TokenEntity tokenEntity) {
		this.tokenEntity = tokenEntity;
	}

	public void login(ActionEvent actionEvent) {
		try {
			RequestContext context = RequestContext.getCurrentInstance();
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesMessage msg = null;

			String login = this.usuario.getLogin();
			String senha = this.usuario.getSenha();

			if (login != null && senha != null) {
				UsuarioEntity usuarioEntity = new UsuarioEntity();
				usuarioEntity.setLogin(login);
				usuarioEntity.setSenha(senha);

				UsuarioEntityRequest usuarioEntityRequest = new UsuarioEntityRequest(PLATAFORMA, usuarioEntity);

				TokenResponse tokenResponse = tokenService.gerarToken(usuarioEntityRequest);

				if (!tokenResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", tokenResponse.getMensagem());
				} else {
					this.tokenEntity = tokenResponse.getToken();

					this.aparelhos = usuario_AparelhoService.listarAparelhos(this.tokenEntity).getAparelhos();
					this.usuario = new UsuarioBean(tokenResponse.getToken().getUsuarioEntity());
					this.usuario.setSenha(senha);
					this.map = new MapBean();
					
					markersUpdate();

					extContext.redirect("pages/index.faces");
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Insira o usuário e a senha!");
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
			context.addCallbackParam("loggedIn", false);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exeption", e.toString()));
		}
	}
	
	public void logout(ActionEvent actionEvent) {
		this.usuario = new UsuarioBean();
		this.tokenEntity = new TokenEntity();
		this.aparelhos = null;
		
		verificaToken();
	}

	public void atualizaUsuario(ActionEvent actionEvent) {
		try {
			this.tokenEntity.setUsuarioEntity(this.usuario.getUsuarioEntity());
			TokenEntity dbToken = usuarioService.atualizaUsuario(this.tokenEntity);
			if (dbToken != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Dados atualizados!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Dados não foram salvos!"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Exception", e.getMessage().toString()));
		}
	}

	public void cadastrarUsuario(ActionEvent actionEvent) {
		try {
			TokenResponse response = usuarioService.cadastraUsuario(this.usuario.getUsuarioEntity());
			if (response.getStatus().equalsIgnoreCase("SUCCESS")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!", ""));
				this.tokenEntity = response.getToken();

				ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
				extContext.redirect("pages/index.faces");
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getMensagem(), ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage().toString(), ""));
		}
	}

	public void verificaToken() {
		try {
			String login = this.usuario.getLogin();
			String senha = this.usuario.getSenha();

			if (login != null && senha != null) {
				UsuarioEntity usuarioEntity = new UsuarioEntity();
				usuarioEntity.setLogin(login);
				usuarioEntity.setSenha(senha);

				UsuarioEntityRequest usuarioEntityRequest = new UsuarioEntityRequest(PLATAFORMA, usuarioEntity);

				TokenResponse tokenResponse = tokenService.gerarToken(usuarioEntityRequest);

				if (tokenResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
					this.tokenEntity = tokenResponse.getToken();

					this.map = new MapBean();
					this.aparelhos = usuario_AparelhoService.listarAparelhos(this.tokenEntity).getAparelhos();
//					markersUpdate();
				}
			}
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			if (this.tokenEntity != null) {
				TokenResponse tokenResponse = tokenService.validaToken(tokenEntity);
				if (tokenResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
					this.tokenEntity = tokenResponse.getToken();
				} else {
					extContext.redirect("../login.faces");
				}
			} else {
				extContext.redirect("../index.jsp");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exeption", e.toString()));
		}
	}

	public void markersUpdate() {
		DefaultMapModel model = new DefaultMapModel();

		if (this.aparelhos != null) {
			for (Usuario_AparelhoEntity aparelho : this.aparelhos) {
				try {
					Usuario_AparelhoRequest usuario_AparelhoRequest = new Usuario_AparelhoRequest(this.tokenEntity, aparelho);
					LocalizacaoResponse local = usuario_AparelhoService.ultimaLocalizacao(usuario_AparelhoRequest);

					if (local.getStatus().equalsIgnoreCase("SUCCESS")) {
						double lat = Double.parseDouble(local.getLocalizacao().getLatitude());
						double lng = Double.parseDouble(local.getLocalizacao().getLongitude());
						String descricao = local.getLocalizacao().getUsuario_AparelhoEntity().getAparelhoEntity().getDescricao();

						model.addOverlay(new Marker(new LatLng(lat, lng), descricao, "", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
					}
				} catch (Exception e) {
				}

			}
		}
		this.map.setModel(model);

	}
	
}
