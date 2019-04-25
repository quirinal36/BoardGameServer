package kr.bacoder.coding.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OPRecord {

	int id;
	String patientId;
	String patientName;
	String opdate;
	String doctor;
	String dx;
	String anesthesia;
	String opname;
	String opfinding;
	String opProcedure;
	String opfee;
	String search;
	String ref;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getOpdate() {
		return opdate;
	}
	public void setOpdate(String opdate) {
		this.opdate = opdate;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDx() {
		return dx;
	}
	public void setDx(String dx) {
		this.dx = dx;
	}
	public String getAnesthesia() {
		return anesthesia;
	}
	public void setAnesthesia(String anesthesia) {
		this.anesthesia = anesthesia;
	}
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	public String getOpfinding() {
		return opfinding;
	}
	public void setOpfinding(String opfinding) {
		this.opfinding = opfinding;
	}
	public String getOpProcedure() {
		return opProcedure;
	}
	public void setOpProcedure(String opProcedure) {
		this.opProcedure = opProcedure;
	}
	public String getOpfee() {
		return opfee;
	}
	public void setOpfee(String opfee) {
		this.opfee = opfee;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
