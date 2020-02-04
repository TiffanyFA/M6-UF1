/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@XmlRootElement(name = "Row")

//@XmlAccessorType(XmlAccessType.FIELD) // This line was added
class Row {
	
        private String id;
        private String uuid;
        private int position;
        private String address;
        
        private String nom;
        private String adreça;
        private String codi_postal;
        private String municipi;
        private String comarca;
        private String modalitat;
        
        
        //atributs del xml
            
      	@XmlAttribute(name="id") 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		@XmlAttribute(name="uuid")
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		
		@XmlAttribute(name="position")
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		
		@XmlAttribute(name="address")
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		
		//resta de getters i setters
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getAdreça() {
			return adreça;
		}
		public void setAdreça(String adreça) {
			this.adreça = adreça;
		}
		public String getCodi_postal() {
			return codi_postal;
		}
		public void setCodi_postal(String codi_postal) {
			this.codi_postal = codi_postal;
		}
		public String getMunicipi() {
			return municipi;
		}
		public void setMunicipi(String municipi) {
			this.municipi = municipi;
		}
		public String getComarca() {
			return comarca;
		}
		public void setComarca(String comarca) {
			this.comarca = comarca;
		}
		public String getModalitat() {
			return modalitat;
		}
		public void setModalitat(String modalitat) {
			this.modalitat = modalitat;
		}
        
           
   
        
}
