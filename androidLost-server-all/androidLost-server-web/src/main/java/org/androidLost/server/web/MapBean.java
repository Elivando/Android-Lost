package org.androidLost.server.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;
import org.androidLost.server.utils.service.beans.request.Usuario_AparelhoRequest;
import org.androidLost.server.utils.service.beans.response.LocalizacaoResponse;
import org.androidLost.server.utils.service.interfaces.IUsuario_AparelhoService;
import org.androidLost.server.web.utils.BaseBean;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@SessionScoped
@Named
public class MapBean extends BaseBean {
	private static final long serialVersionUID = 5896544034668245851L;

	@Inject
	private IUsuario_AparelhoService usuario_AparelhoService;
	
	private MapModel model;
	private Marker marker;
	
	public MapBean(){
		model = new DefaultMapModel();
	}
	
	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}	

	@SuppressWarnings("unchecked")
	public void loadMaps(ComponentSystemEvent event){
		List<Usuario_AparelhoEntity> aparelhos = (List<Usuario_AparelhoEntity>) event.getComponent().getAttributes().get("aparelhos");
		TokenEntity tokenEntity = (TokenEntity) event.getComponent().getAttributes().get("tokenEntity");
		
		if (aparelhos != null) {
			for (Usuario_AparelhoEntity aparelho : aparelhos) {
				try {
					Usuario_AparelhoRequest usuario_AparelhoRequest = new Usuario_AparelhoRequest(tokenEntity, aparelho);
					LocalizacaoResponse local = usuario_AparelhoService.ultimaLocalizacao(usuario_AparelhoRequest);

					if (local.getStatus().equalsIgnoreCase("SUCCESS")) {
						double lat = Double.parseDouble(local.getLocalizacao().getLatitude());
						double lng = Double.parseDouble(local.getLocalizacao().getLongitude());
						String deviceName = local.getLocalizacao().getUsuario_AparelhoEntity().getAparelhoEntity().getDescricao();
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						String lastPosition = dateFormat.format(local.getLocalizacao().getData()).replace(" ", "&nbsp;");
						
						String descricao = "<h3>" + deviceName + "</h3><br />" + lastPosition;

						model.addOverlay(new Marker(new LatLng(lat, lng), descricao, "", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
					}
				} catch (Exception e) {
				}

			}
		}
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
		this.marker = (Marker) event.getOverlay();
	}
}
