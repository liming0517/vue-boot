package app.entity;

import java.util.Date;

import com.wiserun.common.expression.annotation.LikeDoubleField;
import com.wiserun.common.expression.annotation.ValueField;
import com.wiserun.common.support.SearchEasyUI;

/**
 * 负责接收页面查询参数的类 2012-09-21
 */
public class EasyUISearchStudentEntity extends SearchEasyUI {

	private Integer idSch;
	private String nameSch;
	private Integer politicsStateSch;
	private String nationalitySch;
	private String stuNoSch;
	private Byte genderSch;
	private Integer departmentSch;
	private String addressSch;
	private String mobileSch;
	private Date registDateSch;
	private Date birthdaySch;

	public Integer getIdSch() {
		return idSch;
	}

	public void setIdSch(Integer idSch) {
		this.idSch = idSch;
	}

	@LikeDoubleField(column="name")
	public String getNameSch() {
		return nameSch;
	}

	public void setNameSch(String nameSch) {
		this.nameSch = nameSch;
	}

	public Integer getPoliticsStateSch() {
		return politicsStateSch;
	}

	public void setPoliticsStatusSch(Integer politicsStateSch) {
		this.politicsStateSch = politicsStateSch;
	}

	public String getNationalitySch() {
		return nationalitySch;
	}

	public void setNationalitySch(String nationalitySch) {
		this.nationalitySch = nationalitySch;
	}

	@ValueField(column="stu_no")
	public String getStuNoSch() {
		return stuNoSch;
	}

	public void setStuNoSch(String stuNoSch) {
		this.stuNoSch = stuNoSch;
	}

	public Byte getGenderSch() {
		return genderSch;
	}

	public void setGenderSch(Byte genderSch) {
		this.genderSch = genderSch;
	}

	public Integer getDepartmentSch() {
		return departmentSch;
	}

	public void setDepartmentSch(Integer departmentSch) {
		this.departmentSch = departmentSch;
	}

	public String getAddressSch() {
		return addressSch;
	}

	public void setAddressSch(String addressSch) {
		this.addressSch = addressSch;
	}

	public String getMobileSch() {
		return mobileSch;
	}

	public void setMobileSch(String mobileSch) {
		this.mobileSch = mobileSch;
	}

	public Date getRegistDateSch() {
		return registDateSch;
	}

	public void setRegistDateSch(Date registDateSch) {
		this.registDateSch = registDateSch;
	}

	public Date getBirthdaySch() {
		return birthdaySch;
	}

	public void setBirthdaySch(Date birthdaySch) {
		this.birthdaySch = birthdaySch;
	}

}
