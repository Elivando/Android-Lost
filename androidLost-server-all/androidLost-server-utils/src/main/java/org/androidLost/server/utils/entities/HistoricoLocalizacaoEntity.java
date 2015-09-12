package org.androidLost.server.utils.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.androidLost.server.utils.BaseEntity;
import org.androidLost.server.utils.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@XmlRootElement
@Table(name = "HistoricoLocalizacao")
@AttributeOverride(name = "id", column = @Column(name = "CodHistorico"))
public class HistoricoLocalizacaoEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 4145513888438234634L;

	@ManyToOne
	@JoinColumn(name = "CodUsuario_Aparelho", referencedColumnName = "CodUsuario_Aparelho")
	private Usuario_AparelhoEntity usuario_AparelhoEntity;

	@NotNull
	private String longitude;
	
	@NotNull
	private String latitude;
	
	@NotNull
	private Date data;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Usuario_AparelhoEntity getUsuario_AparelhoEntity() {
		return usuario_AparelhoEntity;
	}

	public void setUsuario_AparelhoEntity(
			Usuario_AparelhoEntity usuario_AparelhoEntity) {
		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
