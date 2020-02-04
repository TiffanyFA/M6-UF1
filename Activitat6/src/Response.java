/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class Response {
	
	private Row[] rows;
 
	
	public Row[] getRows() {
		return rows;
	}
	public void setRows(Row[] rows) {
		this.rows = rows;
	}
 
}
