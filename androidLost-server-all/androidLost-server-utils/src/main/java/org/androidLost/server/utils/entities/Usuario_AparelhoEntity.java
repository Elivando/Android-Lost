package org.androidLost.server.utils.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.androidLost.server.utils.BaseEntity;
import org.androidLost.server.utils.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@XmlRootElement
@Table(name = "Usuario_Aparelho")
@AttributeOverride(name = "id", column = @Column(name = "CodUsuario_Aparelho"))
public class Usuario_AparelhoEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = -3431193482596298108L;

	@NotNull
	private char status;

	@NotNull
	private Date data;
	
	@NotNull
	private char habilita3G;
	
	@NotNull
	private char habilitaWifi;

	@NotNull
	private char habilitaGPS;
	
	@NotNull
	private char autoStart;
	
	@NotNull
	private char modFurto;
	
	@NotNull
	private int syncTime;

	@ManyToOne
	@JoinColumn(name = "CodUsuario", referencedColumnName = "CodUsuario")
	private UsuarioEntity usuarioEntity;

	@NotNull
	@OneToOne
	@JoinColumn(name = "CodAparelho", referencedColumnName = "CodAparelho")
	private AparelhoEntity aparelhoEntity;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public char getHabilita3G() {
		return habilita3G;
	}

	public void setHabilita3G(char habilita3g) {
		habilita3G = habilita3g;
	}

	public char getHabilitaWifi() {
		return habilitaWifi;
	}

	public void setHabilitaWifi(char habilitaWifi) {
		this.habilitaWifi = habilitaWifi;
	}
	
	public char getHabilitaGPS() {
		return habilitaGPS;
	}

	public void setHabilitaGPS(char habilitaGPS) {
		this.habilitaGPS = habilitaGPS;
	}

	public char getAutoStart() {
		return autoStart;
	}

	public void setAutoStart(char autoStart) {
		this.autoStart = autoStart;
	}

	public char getModFurto() {
		return modFurto;
	}

	public void setModFurto(char modFurto) {
		this.modFurto = modFurto;
	}

	public int getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public AparelhoEntity getAparelhoEntity() {
		return aparelhoEntity;
	}

	public void setAparelhoEntity(AparelhoEntity aparelhoEntity) {
		this.aparelhoEntity = aparelhoEntity;
	}

}
