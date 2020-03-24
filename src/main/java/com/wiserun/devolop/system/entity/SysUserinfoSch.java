package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;
import com.wiserun.common.expression.annotation.LikeDoubleField;
import com.wiserun.common.expression.annotation.ValueField;
import com.wiserun.common.support.SearchEasyUI;

/**
 * 创建人：代码生成器 Create date: **请补充** 功能说明：sys.userinfo SearchEntity查询用途类
 * 修订信息(**请补充**) 修订日期:**请补充** 原因:**请补充** 修订人:**请补充**
 **/

public class SysUserinfoSch extends SearchEasyUI {

	private String idSch; // 等于条件
	private String idLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String idMore; // 大于条件
	private String idLess; // 小于条件

	private String createDeptIdSch; // 等于条件
	private String createDeptIdLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createDeptIdMore; // 大于条件
	private String createDeptIdLess; // 小于条件

	private String createDeptCodeSch; // 等于条件
	private String createDeptCodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createDeptCodeMore; // 大于条件
	private String createDeptCodeLess; // 小于条件

	private String createDeptNameSch; // 等于条件
	private String createDeptNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createDeptNameMore; // 大于条件
	private String createDeptNameLess; // 小于条件

	private String createAreaIdSch; // 等于条件
	private String createAreaIdLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createAreaIdMore; // 大于条件
	private String createAreaIdLess; // 小于条件

	private String createAreaCodeSch; // 等于条件
	private String createAreaCodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createAreaCodeMore; // 大于条件
	private String createAreaCodeLess; // 小于条件

	private String createAreaNameSch; // 等于条件
	private String createAreaNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createAreaNameMore; // 大于条件
	private String createAreaNameLess; // 小于条件

	private String createUserIDSch; // 等于条件
	private String createUserIDLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createUserIDMore; // 大于条件
	private String createUserIDLess; // 小于条件

	private String createUserCodeSch; // 等于条件
	private String createUserCodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createUserCodeMore; // 大于条件
	private String createUserCodeLess; // 小于条件

	private String createUserNameSch; // 等于条件
	private String createUserNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String createUserNameMore; // 大于条件
	private String createUserNameLess; // 小于条件

	private Date createDateSch; // 等于条件
	private Date createDateMore; // 大于条件
	private Date createDateLess; // 小于条件

	private String updateUserIDSch; // 等于条件
	private String updateUserIDLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String updateUserIDMore; // 大于条件
	private String updateUserIDLess; // 小于条件

	private String updateUserCodeSch; // 等于条件
	private String updateUserCodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String updateUserCodeMore; // 大于条件
	private String updateUserCodeLess; // 小于条件

	private String updateUserNameSch; // 等于条件
	private String updateUserNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String updateUserNameMore; // 大于条件
	private String updateUserNameLess; // 小于条件

	private Date updateDateSch; // 等于条件
	private Date updateDateMore; // 大于条件
	private Date updateDateLess; // 小于条件

	private Integer rowOrderSch; // 等于条件
	private Integer rowOrderLike; // 模糊 LIKE 条件 仅限字符型字段 int
	private Integer rowOrderMore; // 大于条件
	private Integer rowOrderLess; // 小于条件

	private String remarkSch; // 等于条件
	private String remarkLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String remarkMore; // 大于条件
	private String remarkLess; // 小于条件

	private String stateSch; // 等于条件
	private String stateLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String stateMore; // 大于条件
	private String stateLess; // 小于条件

	private String usernameSch; // 等于条件
	private String usernameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String usernameMore; // 大于条件
	private String usernameLess; // 小于条件

	private String passwordSch; // 等于条件
	private String passwordLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String passwordMore; // 大于条件
	private String passwordLess; // 小于条件

	private String lastlogindateSch; // 等于条件
	private String lastlogindateLike; // 模糊 LIKE 条件 仅限字符型字段 datetime2
	private String lastlogindateMore; // 大于条件
	private String lastlogindateLess; // 小于条件

	private String trueNameSch; // 等于条件
	private String trueNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String trueNameMore; // 大于条件
	private String trueNameLess; // 小于条件

	private String usercodeSch; // 等于条件
	private String usercodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String usercodeMore; // 大于条件
	private String usercodeLess; // 小于条件

	private String phoneNoSch; // 等于条件
	private String phoneNoLike; // 模糊 LIKE 条件 仅限字符型字段 nchar
	private String phoneNoMore; // 大于条件
	private String phoneNoLess; // 小于条件

	private String idnumberSch; // 等于条件
	private String idnumberLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String idnumberMore; // 大于条件
	private String idnumberLess; // 小于条件

	private String weixinNoSch; // 等于条件
	private String weixinNoLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String weixinNoMore; // 大于条件
	private String weixinNoLess; // 小于条件

	private String loginNameSch; // 等于条件
	private String loginNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String loginNameMore; // 大于条件
	private String loginNameLess; // 小于条件

	private String headPortraitSch; // 等于条件
	private String headPortraitLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String headPortraitMore; // 大于条件
	private String headPortraitLess; // 小于条件

	private String ext1Sch; // 等于条件
	private String ext1Like; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String ext1More; // 大于条件
	private String ext1Less; // 小于条件

	private String ext2Sch; // 等于条件
	private String ext2Like; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String ext2More; // 大于条件
	private String ext2Less; // 小于条件

	private String mainDeptIdSch; // 等于条件
	private String mainDeptIdLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String mainDeptIdMore; // 大于条件
	private String mainDeptIdLess; // 小于条件

	private String mainDeptCodeSch; // 等于条件
	private String mainDeptCodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String mainDeptCodeMore; // 大于条件
	private String mainDeptCodeLess; // 小于条件

	private String mainDeptNameSch; // 等于条件
	private String mainDeptNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String mainDeptNameMore; // 大于条件
	private String mainDeptNameLess; // 小于条件

	private String mainRoleIdSch; // 等于条件
	private String mainRoleIdLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String mainRoleIdMore; // 大于条件
	private String mainRoleIdLess; // 小于条件

	private String mainRoleCodeSch; // 等于条件
	private String mainRoleCodeLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String mainRoleCodeMore; // 大于条件
	private String mainRoleCodeLess; // 小于条件

	private String mainRoleNameSch; // 等于条件
	private String mainRoleNameLike; // 模糊 LIKE 条件 仅限字符型字段 nvarchar
	private String mainRoleNameMore; // 大于条件
	private String mainRoleNameLess; // 小于条件

	// 等于条件
	public void setIdSch(String idSch) {
		this.idSch = idSch;
	}

	@ValueField(column = "id") // 等于条件
	public String getIdSch() {
		return this.idSch;
	}

	// 模糊条件-仅限字符型字段
	public void setIdLike(String idLike) {
		this.idLike = idLike;
	}

	@LikeDoubleField(column = "id") // 模糊条件-仅限字符型字段
	public String getIdLike() {

		return this.idLike;
	}

	// 大于条件
	public void setIdMore(String idMore) {
		this.idMore = idMore;
	}

	@ValueField(column = "id", equal = ">=") // 大于条件
	public String getIdMore() {
		return this.idMore;
	}

	// 小于条件
	public void setIdLess(String idLess) {
		this.idLess = idLess;
	}

	@ValueField(column = "id", equal = "<=") // 小于条件
	public String getIdLess() {
		return this.idLess;
	}

	// 等于条件
	public void setCreateDeptIdSch(String createDeptIdSch) {
		this.createDeptIdSch = createDeptIdSch;
	}

	@ValueField(column = "createDeptId") // 等于条件
	public String getCreateDeptIdSch() {
		return this.createDeptIdSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateDeptIdLike(String createDeptIdLike) {
		this.createDeptIdLike = createDeptIdLike;
	}

	@LikeDoubleField(column = "createDeptId") // 模糊条件-仅限字符型字段
	public String getCreateDeptIdLike() {

		return this.createDeptIdLike;
	}

	// 大于条件
	public void setCreateDeptIdMore(String createDeptIdMore) {
		this.createDeptIdMore = createDeptIdMore;
	}

	@ValueField(column = "createDeptId", equal = ">=") // 大于条件
	public String getCreateDeptIdMore() {
		return this.createDeptIdMore;
	}

	// 小于条件
	public void setCreateDeptIdLess(String createDeptIdLess) {
		this.createDeptIdLess = createDeptIdLess;
	}

	@ValueField(column = "createDeptId", equal = "<=") // 小于条件
	public String getCreateDeptIdLess() {
		return this.createDeptIdLess;
	}

	// 等于条件
	public void setCreateDeptCodeSch(String createDeptCodeSch) {
		this.createDeptCodeSch = createDeptCodeSch;
	}

	@ValueField(column = "createDeptCode") // 等于条件
	public String getCreateDeptCodeSch() {
		return this.createDeptCodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateDeptCodeLike(String createDeptCodeLike) {
		this.createDeptCodeLike = createDeptCodeLike;
	}

	@LikeDoubleField(column = "createDeptCode") // 模糊条件-仅限字符型字段
	public String getCreateDeptCodeLike() {

		return this.createDeptCodeLike;
	}

	// 大于条件
	public void setCreateDeptCodeMore(String createDeptCodeMore) {
		this.createDeptCodeMore = createDeptCodeMore;
	}

	@ValueField(column = "createDeptCode", equal = ">=") // 大于条件
	public String getCreateDeptCodeMore() {
		return this.createDeptCodeMore;
	}

	// 小于条件
	public void setCreateDeptCodeLess(String createDeptCodeLess) {
		this.createDeptCodeLess = createDeptCodeLess;
	}

	@ValueField(column = "createDeptCode", equal = "<=") // 小于条件
	public String getCreateDeptCodeLess() {
		return this.createDeptCodeLess;
	}

	// 等于条件
	public void setCreateDeptNameSch(String createDeptNameSch) {
		this.createDeptNameSch = createDeptNameSch;
	}

	@ValueField(column = "createDeptName") // 等于条件
	public String getCreateDeptNameSch() {
		return this.createDeptNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateDeptNameLike(String createDeptNameLike) {
		this.createDeptNameLike = createDeptNameLike;
	}

	@LikeDoubleField(column = "createDeptName") // 模糊条件-仅限字符型字段
	public String getCreateDeptNameLike() {

		return this.createDeptNameLike;
	}

	// 大于条件
	public void setCreateDeptNameMore(String createDeptNameMore) {
		this.createDeptNameMore = createDeptNameMore;
	}

	@ValueField(column = "createDeptName", equal = ">=") // 大于条件
	public String getCreateDeptNameMore() {
		return this.createDeptNameMore;
	}

	// 小于条件
	public void setCreateDeptNameLess(String createDeptNameLess) {
		this.createDeptNameLess = createDeptNameLess;
	}

	@ValueField(column = "createDeptName", equal = "<=") // 小于条件
	public String getCreateDeptNameLess() {
		return this.createDeptNameLess;
	}

	// 等于条件
	public void setCreateAreaIdSch(String createAreaIdSch) {
		this.createAreaIdSch = createAreaIdSch;
	}

	@ValueField(column = "createAreaId") // 等于条件
	public String getCreateAreaIdSch() {
		return this.createAreaIdSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateAreaIdLike(String createAreaIdLike) {
		this.createAreaIdLike = createAreaIdLike;
	}

	@LikeDoubleField(column = "createAreaId") // 模糊条件-仅限字符型字段
	public String getCreateAreaIdLike() {

		return this.createAreaIdLike;
	}

	// 大于条件
	public void setCreateAreaIdMore(String createAreaIdMore) {
		this.createAreaIdMore = createAreaIdMore;
	}

	@ValueField(column = "createAreaId", equal = ">=") // 大于条件
	public String getCreateAreaIdMore() {
		return this.createAreaIdMore;
	}

	// 小于条件
	public void setCreateAreaIdLess(String createAreaIdLess) {
		this.createAreaIdLess = createAreaIdLess;
	}

	@ValueField(column = "createAreaId", equal = "<=") // 小于条件
	public String getCreateAreaIdLess() {
		return this.createAreaIdLess;
	}

	// 等于条件
	public void setCreateAreaCodeSch(String createAreaCodeSch) {
		this.createAreaCodeSch = createAreaCodeSch;
	}

	@ValueField(column = "createAreaCode") // 等于条件
	public String getCreateAreaCodeSch() {
		return this.createAreaCodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateAreaCodeLike(String createAreaCodeLike) {
		this.createAreaCodeLike = createAreaCodeLike;
	}

	@LikeDoubleField(column = "createAreaCode") // 模糊条件-仅限字符型字段
	public String getCreateAreaCodeLike() {

		return this.createAreaCodeLike;
	}

	// 大于条件
	public void setCreateAreaCodeMore(String createAreaCodeMore) {
		this.createAreaCodeMore = createAreaCodeMore;
	}

	@ValueField(column = "createAreaCode", equal = ">=") // 大于条件
	public String getCreateAreaCodeMore() {
		return this.createAreaCodeMore;
	}

	// 小于条件
	public void setCreateAreaCodeLess(String createAreaCodeLess) {
		this.createAreaCodeLess = createAreaCodeLess;
	}

	@ValueField(column = "createAreaCode", equal = "<=") // 小于条件
	public String getCreateAreaCodeLess() {
		return this.createAreaCodeLess;
	}

	// 等于条件
	public void setCreateAreaNameSch(String createAreaNameSch) {
		this.createAreaNameSch = createAreaNameSch;
	}

	@ValueField(column = "createAreaName") // 等于条件
	public String getCreateAreaNameSch() {
		return this.createAreaNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateAreaNameLike(String createAreaNameLike) {
		this.createAreaNameLike = createAreaNameLike;
	}

	@LikeDoubleField(column = "createAreaName") // 模糊条件-仅限字符型字段
	public String getCreateAreaNameLike() {

		return this.createAreaNameLike;
	}

	// 大于条件
	public void setCreateAreaNameMore(String createAreaNameMore) {
		this.createAreaNameMore = createAreaNameMore;
	}

	@ValueField(column = "createAreaName", equal = ">=") // 大于条件
	public String getCreateAreaNameMore() {
		return this.createAreaNameMore;
	}

	// 小于条件
	public void setCreateAreaNameLess(String createAreaNameLess) {
		this.createAreaNameLess = createAreaNameLess;
	}

	@ValueField(column = "createAreaName", equal = "<=") // 小于条件
	public String getCreateAreaNameLess() {
		return this.createAreaNameLess;
	}

	// 等于条件
	public void setCreateUserIDSch(String createUserIDSch) {
		this.createUserIDSch = createUserIDSch;
	}

	@ValueField(column = "createUserID") // 等于条件
	public String getCreateUserIDSch() {
		return this.createUserIDSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateUserIDLike(String createUserIDLike) {
		this.createUserIDLike = createUserIDLike;
	}

	@LikeDoubleField(column = "createUserID") // 模糊条件-仅限字符型字段
	public String getCreateUserIDLike() {

		return this.createUserIDLike;
	}

	// 大于条件
	public void setCreateUserIDMore(String createUserIDMore) {
		this.createUserIDMore = createUserIDMore;
	}

	@ValueField(column = "createUserID", equal = ">=") // 大于条件
	public String getCreateUserIDMore() {
		return this.createUserIDMore;
	}

	// 小于条件
	public void setCreateUserIDLess(String createUserIDLess) {
		this.createUserIDLess = createUserIDLess;
	}

	@ValueField(column = "createUserID", equal = "<=") // 小于条件
	public String getCreateUserIDLess() {
		return this.createUserIDLess;
	}

	// 等于条件
	public void setCreateUserCodeSch(String createUserCodeSch) {
		this.createUserCodeSch = createUserCodeSch;
	}

	@ValueField(column = "createUserCode") // 等于条件
	public String getCreateUserCodeSch() {
		return this.createUserCodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateUserCodeLike(String createUserCodeLike) {
		this.createUserCodeLike = createUserCodeLike;
	}

	@LikeDoubleField(column = "createUserCode") // 模糊条件-仅限字符型字段
	public String getCreateUserCodeLike() {

		return this.createUserCodeLike;
	}

	// 大于条件
	public void setCreateUserCodeMore(String createUserCodeMore) {
		this.createUserCodeMore = createUserCodeMore;
	}

	@ValueField(column = "createUserCode", equal = ">=") // 大于条件
	public String getCreateUserCodeMore() {
		return this.createUserCodeMore;
	}

	// 小于条件
	public void setCreateUserCodeLess(String createUserCodeLess) {
		this.createUserCodeLess = createUserCodeLess;
	}

	@ValueField(column = "createUserCode", equal = "<=") // 小于条件
	public String getCreateUserCodeLess() {
		return this.createUserCodeLess;
	}

	// 等于条件
	public void setCreateUserNameSch(String createUserNameSch) {
		this.createUserNameSch = createUserNameSch;
	}

	@ValueField(column = "createUserName") // 等于条件
	public String getCreateUserNameSch() {
		return this.createUserNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setCreateUserNameLike(String createUserNameLike) {
		this.createUserNameLike = createUserNameLike;
	}

	@LikeDoubleField(column = "createUserName") // 模糊条件-仅限字符型字段
	public String getCreateUserNameLike() {

		return this.createUserNameLike;
	}

	// 大于条件
	public void setCreateUserNameMore(String createUserNameMore) {
		this.createUserNameMore = createUserNameMore;
	}

	@ValueField(column = "createUserName", equal = ">=") // 大于条件
	public String getCreateUserNameMore() {
		return this.createUserNameMore;
	}

	// 小于条件
	public void setCreateUserNameLess(String createUserNameLess) {
		this.createUserNameLess = createUserNameLess;
	}

	@ValueField(column = "createUserName", equal = "<=") // 小于条件
	public String getCreateUserNameLess() {
		return this.createUserNameLess;
	}

	// 等于条件
	public void setCreateDateSch(Date createDateSch) {
		this.createDateSch = createDateSch;
	}

	@ValueField(column = "createDate") // 等于条件
	public Date getCreateDateSch() {
		return this.createDateSch;
	}

	// 大于条件
	public void setCreateDateMore(Date createDateMore) {
		this.createDateMore = createDateMore;
	}

	@ValueField(column = "createDate", equal = ">=") // 大于条件
	public Date getCreateDateMore() {
		return this.createDateMore;
	}

	// 小于条件
	public void setCreateDateLess(Date createDateLess) {
		this.createDateLess = createDateLess;
	}

	@ValueField(column = "createDate", equal = "<=") // 小于条件
	public Date getCreateDateLess() {
		return this.createDateLess;
	}

	// 等于条件
	public void setUpdateUserIDSch(String updateUserIDSch) {
		this.updateUserIDSch = updateUserIDSch;
	}

	@ValueField(column = "updateUserID") // 等于条件
	public String getUpdateUserIDSch() {
		return this.updateUserIDSch;
	}

	// 模糊条件-仅限字符型字段
	public void setUpdateUserIDLike(String updateUserIDLike) {
		this.updateUserIDLike = updateUserIDLike;
	}

	@LikeDoubleField(column = "updateUserID") // 模糊条件-仅限字符型字段
	public String getUpdateUserIDLike() {

		return this.updateUserIDLike;
	}

	// 大于条件
	public void setUpdateUserIDMore(String updateUserIDMore) {
		this.updateUserIDMore = updateUserIDMore;
	}

	@ValueField(column = "updateUserID", equal = ">=") // 大于条件
	public String getUpdateUserIDMore() {
		return this.updateUserIDMore;
	}

	// 小于条件
	public void setUpdateUserIDLess(String updateUserIDLess) {
		this.updateUserIDLess = updateUserIDLess;
	}

	@ValueField(column = "updateUserID", equal = "<=") // 小于条件
	public String getUpdateUserIDLess() {
		return this.updateUserIDLess;
	}

	// 等于条件
	public void setUpdateUserCodeSch(String updateUserCodeSch) {
		this.updateUserCodeSch = updateUserCodeSch;
	}

	@ValueField(column = "updateUserCode") // 等于条件
	public String getUpdateUserCodeSch() {
		return this.updateUserCodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setUpdateUserCodeLike(String updateUserCodeLike) {
		this.updateUserCodeLike = updateUserCodeLike;
	}

	@LikeDoubleField(column = "updateUserCode") // 模糊条件-仅限字符型字段
	public String getUpdateUserCodeLike() {

		return this.updateUserCodeLike;
	}

	// 大于条件
	public void setUpdateUserCodeMore(String updateUserCodeMore) {
		this.updateUserCodeMore = updateUserCodeMore;
	}

	@ValueField(column = "updateUserCode", equal = ">=") // 大于条件
	public String getUpdateUserCodeMore() {
		return this.updateUserCodeMore;
	}

	// 小于条件
	public void setUpdateUserCodeLess(String updateUserCodeLess) {
		this.updateUserCodeLess = updateUserCodeLess;
	}

	@ValueField(column = "updateUserCode", equal = "<=") // 小于条件
	public String getUpdateUserCodeLess() {
		return this.updateUserCodeLess;
	}

	// 等于条件
	public void setUpdateUserNameSch(String updateUserNameSch) {
		this.updateUserNameSch = updateUserNameSch;
	}

	@ValueField(column = "updateUserName") // 等于条件
	public String getUpdateUserNameSch() {
		return this.updateUserNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setUpdateUserNameLike(String updateUserNameLike) {
		this.updateUserNameLike = updateUserNameLike;
	}

	@LikeDoubleField(column = "updateUserName") // 模糊条件-仅限字符型字段
	public String getUpdateUserNameLike() {

		return this.updateUserNameLike;
	}

	// 大于条件
	public void setUpdateUserNameMore(String updateUserNameMore) {
		this.updateUserNameMore = updateUserNameMore;
	}

	@ValueField(column = "updateUserName", equal = ">=") // 大于条件
	public String getUpdateUserNameMore() {
		return this.updateUserNameMore;
	}

	// 小于条件
	public void setUpdateUserNameLess(String updateUserNameLess) {
		this.updateUserNameLess = updateUserNameLess;
	}

	@ValueField(column = "updateUserName", equal = "<=") // 小于条件
	public String getUpdateUserNameLess() {
		return this.updateUserNameLess;
	}

	// 等于条件
	public void setUpdateDateSch(Date updateDateSch) {
		this.updateDateSch = updateDateSch;
	}

	@ValueField(column = "updateDate") // 等于条件
	public Date getUpdateDateSch() {
		return this.updateDateSch;
	}

	// 大于条件
	public void setUpdateDateMore(Date updateDateMore) {
		this.updateDateMore = updateDateMore;
	}

	@ValueField(column = "updateDate", equal = ">=") // 大于条件
	public Date getUpdateDateMore() {
		return this.updateDateMore;
	}

	// 小于条件
	public void setUpdateDateLess(Date updateDateLess) {
		this.updateDateLess = updateDateLess;
	}

	@ValueField(column = "updateDate", equal = "<=") // 小于条件
	public Date getUpdateDateLess() {
		return this.updateDateLess;
	}

	// 等于条件
	public void setRowOrderSch(Integer rowOrderSch) {
		this.rowOrderSch = rowOrderSch;
	}

	@ValueField(column = "rowOrder") // 等于条件
	public Integer getRowOrderSch() {
		return this.rowOrderSch;
	}

	// 模糊条件-仅限字符型字段
	public void setRowOrderLike(Integer rowOrderLike) {
		this.rowOrderLike = rowOrderLike;
	}

	@LikeDoubleField(column = "rowOrder") // 模糊条件-仅限字符型字段
	public Integer getRowOrderLike() {

		return this.rowOrderLike;
	}

	// 大于条件
	public void setRowOrderMore(Integer rowOrderMore) {
		this.rowOrderMore = rowOrderMore;
	}

	@ValueField(column = "rowOrder", equal = ">=") // 大于条件
	public Integer getRowOrderMore() {
		return this.rowOrderMore;
	}

	// 小于条件
	public void setRowOrderLess(Integer rowOrderLess) {
		this.rowOrderLess = rowOrderLess;
	}

	@ValueField(column = "rowOrder", equal = "<=") // 小于条件
	public Integer getRowOrderLess() {
		return this.rowOrderLess;
	}

	// 等于条件
	public void setRemarkSch(String remarkSch) {
		this.remarkSch = remarkSch;
	}

	@ValueField(column = "remark") // 等于条件
	public String getRemarkSch() {
		return this.remarkSch;
	}

	// 模糊条件-仅限字符型字段
	public void setRemarkLike(String remarkLike) {
		this.remarkLike = remarkLike;
	}

	@LikeDoubleField(column = "remark") // 模糊条件-仅限字符型字段
	public String getRemarkLike() {

		return this.remarkLike;
	}

	// 大于条件
	public void setRemarkMore(String remarkMore) {
		this.remarkMore = remarkMore;
	}

	@ValueField(column = "remark", equal = ">=") // 大于条件
	public String getRemarkMore() {
		return this.remarkMore;
	}

	// 小于条件
	public void setRemarkLess(String remarkLess) {
		this.remarkLess = remarkLess;
	}

	@ValueField(column = "remark", equal = "<=") // 小于条件
	public String getRemarkLess() {
		return this.remarkLess;
	}

	// 等于条件
	public void setStateSch(String stateSch) {
		this.stateSch = stateSch;
	}

	@ValueField(column = "state") // 等于条件
	public String getStateSch() {
		return this.stateSch;
	}

	// 模糊条件-仅限字符型字段
	public void setStateLike(String stateLike) {
		this.stateLike = stateLike;
	}

	@LikeDoubleField(column = "state") // 模糊条件-仅限字符型字段
	public String getStateLike() {

		return this.stateLike;
	}

	// 大于条件
	public void setStateMore(String stateMore) {
		this.stateMore = stateMore;
	}

	@ValueField(column = "state", equal = ">=") // 大于条件
	public String getStateMore() {
		return this.stateMore;
	}

	// 小于条件
	public void setStateLess(String stateLess) {
		this.stateLess = stateLess;
	}

	@ValueField(column = "state", equal = "<=") // 小于条件
	public String getStateLess() {
		return this.stateLess;
	}

	// 等于条件
	public void setUsernameSch(String usernameSch) {
		this.usernameSch = usernameSch;
	}

	@ValueField(column = "username") // 等于条件
	public String getUsernameSch() {
		return this.usernameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setUsernameLike(String usernameLike) {
		this.usernameLike = usernameLike;
	}

	@LikeDoubleField(column = "username") // 模糊条件-仅限字符型字段
	public String getUsernameLike() {

		return this.usernameLike;
	}

	// 大于条件
	public void setUsernameMore(String usernameMore) {
		this.usernameMore = usernameMore;
	}

	@ValueField(column = "username", equal = ">=") // 大于条件
	public String getUsernameMore() {
		return this.usernameMore;
	}

	// 小于条件
	public void setUsernameLess(String usernameLess) {
		this.usernameLess = usernameLess;
	}

	@ValueField(column = "username", equal = "<=") // 小于条件
	public String getUsernameLess() {
		return this.usernameLess;
	}

	// 等于条件
	public void setPasswordSch(String passwordSch) {
		this.passwordSch = passwordSch;
	}

	@ValueField(column = "password") // 等于条件
	public String getPasswordSch() {
		return this.passwordSch;
	}

	// 模糊条件-仅限字符型字段
	public void setPasswordLike(String passwordLike) {
		this.passwordLike = passwordLike;
	}

	@LikeDoubleField(column = "password") // 模糊条件-仅限字符型字段
	public String getPasswordLike() {

		return this.passwordLike;
	}

	// 大于条件
	public void setPasswordMore(String passwordMore) {
		this.passwordMore = passwordMore;
	}

	@ValueField(column = "password", equal = ">=") // 大于条件
	public String getPasswordMore() {
		return this.passwordMore;
	}

	// 小于条件
	public void setPasswordLess(String passwordLess) {
		this.passwordLess = passwordLess;
	}

	@ValueField(column = "password", equal = "<=") // 小于条件
	public String getPasswordLess() {
		return this.passwordLess;
	}

	// 等于条件
	public void setLastlogindateSch(String lastlogindateSch) {
		this.lastlogindateSch = lastlogindateSch;
	}

	@ValueField(column = "lastlogindate") // 等于条件
	public String getLastlogindateSch() {
		return this.lastlogindateSch;
	}

	// 模糊条件-仅限字符型字段
	public void setLastlogindateLike(String lastlogindateLike) {
		this.lastlogindateLike = lastlogindateLike;
	}

	@LikeDoubleField(column = "lastlogindate") // 模糊条件-仅限字符型字段
	public String getLastlogindateLike() {

		return this.lastlogindateLike;
	}

	// 大于条件
	public void setLastlogindateMore(String lastlogindateMore) {
		this.lastlogindateMore = lastlogindateMore;
	}

	@ValueField(column = "lastlogindate", equal = ">=") // 大于条件
	public String getLastlogindateMore() {
		return this.lastlogindateMore;
	}

	// 小于条件
	public void setLastlogindateLess(String lastlogindateLess) {
		this.lastlogindateLess = lastlogindateLess;
	}

	@ValueField(column = "lastlogindate", equal = "<=") // 小于条件
	public String getLastlogindateLess() {
		return this.lastlogindateLess;
	}

	// 等于条件
	public void setTrueNameSch(String trueNameSch) {
		this.trueNameSch = trueNameSch;
	}

	@ValueField(column = "trueName") // 等于条件
	public String getTrueNameSch() {
		return this.trueNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setTrueNameLike(String trueNameLike) {
		this.trueNameLike = trueNameLike;
	}

	@LikeDoubleField(column = "trueName") // 模糊条件-仅限字符型字段
	public String getTrueNameLike() {

		return this.trueNameLike;
	}

	// 大于条件
	public void setTrueNameMore(String trueNameMore) {
		this.trueNameMore = trueNameMore;
	}

	@ValueField(column = "trueName", equal = ">=") // 大于条件
	public String getTrueNameMore() {
		return this.trueNameMore;
	}

	// 小于条件
	public void setTrueNameLess(String trueNameLess) {
		this.trueNameLess = trueNameLess;
	}

	@ValueField(column = "trueName", equal = "<=") // 小于条件
	public String getTrueNameLess() {
		return this.trueNameLess;
	}

	// 等于条件
	public void setUsercodeSch(String usercodeSch) {
		this.usercodeSch = usercodeSch;
	}

	@ValueField(column = "usercode") // 等于条件
	public String getUsercodeSch() {
		return this.usercodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setUsercodeLike(String usercodeLike) {
		this.usercodeLike = usercodeLike;
	}

	@LikeDoubleField(column = "usercode") // 模糊条件-仅限字符型字段
	public String getUsercodeLike() {

		return this.usercodeLike;
	}

	// 大于条件
	public void setUsercodeMore(String usercodeMore) {
		this.usercodeMore = usercodeMore;
	}

	@ValueField(column = "usercode", equal = ">=") // 大于条件
	public String getUsercodeMore() {
		return this.usercodeMore;
	}

	// 小于条件
	public void setUsercodeLess(String usercodeLess) {
		this.usercodeLess = usercodeLess;
	}

	@ValueField(column = "usercode", equal = "<=") // 小于条件
	public String getUsercodeLess() {
		return this.usercodeLess;
	}

	// 等于条件
	public void setPhoneNoSch(String phoneNoSch) {
		this.phoneNoSch = phoneNoSch;
	}

	@ValueField(column = "phoneNo") // 等于条件
	public String getPhoneNoSch() {
		return this.phoneNoSch;
	}

	// 模糊条件-仅限字符型字段
	public void setPhoneNoLike(String phoneNoLike) {
		this.phoneNoLike = phoneNoLike;
	}

	@LikeDoubleField(column = "phoneNo") // 模糊条件-仅限字符型字段
	public String getPhoneNoLike() {

		return this.phoneNoLike;
	}

	// 大于条件
	public void setPhoneNoMore(String phoneNoMore) {
		this.phoneNoMore = phoneNoMore;
	}

	@ValueField(column = "phoneNo", equal = ">=") // 大于条件
	public String getPhoneNoMore() {
		return this.phoneNoMore;
	}

	// 小于条件
	public void setPhoneNoLess(String phoneNoLess) {
		this.phoneNoLess = phoneNoLess;
	}

	@ValueField(column = "phoneNo", equal = "<=") // 小于条件
	public String getPhoneNoLess() {
		return this.phoneNoLess;
	}

	// 等于条件
	public void setIdnumberSch(String idnumberSch) {
		this.idnumberSch = idnumberSch;
	}

	@ValueField(column = "idnumber") // 等于条件
	public String getIdnumberSch() {
		return this.idnumberSch;
	}

	// 模糊条件-仅限字符型字段
	public void setIdnumberLike(String idnumberLike) {
		this.idnumberLike = idnumberLike;
	}

	@LikeDoubleField(column = "idnumber") // 模糊条件-仅限字符型字段
	public String getIdnumberLike() {

		return this.idnumberLike;
	}

	// 大于条件
	public void setIdnumberMore(String idnumberMore) {
		this.idnumberMore = idnumberMore;
	}

	@ValueField(column = "idnumber", equal = ">=") // 大于条件
	public String getIdnumberMore() {
		return this.idnumberMore;
	}

	// 小于条件
	public void setIdnumberLess(String idnumberLess) {
		this.idnumberLess = idnumberLess;
	}

	@ValueField(column = "idnumber", equal = "<=") // 小于条件
	public String getIdnumberLess() {
		return this.idnumberLess;
	}

	// 等于条件
	public void setWeixinNoSch(String weixinNoSch) {
		this.weixinNoSch = weixinNoSch;
	}

	@ValueField(column = "weixinNo") // 等于条件
	public String getWeixinNoSch() {
		return this.weixinNoSch;
	}

	// 模糊条件-仅限字符型字段
	public void setWeixinNoLike(String weixinNoLike) {
		this.weixinNoLike = weixinNoLike;
	}

	@LikeDoubleField(column = "weixinNo") // 模糊条件-仅限字符型字段
	public String getWeixinNoLike() {

		return this.weixinNoLike;
	}

	// 大于条件
	public void setWeixinNoMore(String weixinNoMore) {
		this.weixinNoMore = weixinNoMore;
	}

	@ValueField(column = "weixinNo", equal = ">=") // 大于条件
	public String getWeixinNoMore() {
		return this.weixinNoMore;
	}

	// 小于条件
	public void setWeixinNoLess(String weixinNoLess) {
		this.weixinNoLess = weixinNoLess;
	}

	@ValueField(column = "weixinNo", equal = "<=") // 小于条件
	public String getWeixinNoLess() {
		return this.weixinNoLess;
	}

	// 等于条件
	public void setLoginNameSch(String loginNameSch) {
		this.loginNameSch = loginNameSch;
	}

	@ValueField(column = "loginName") // 等于条件
	public String getLoginNameSch() {
		return this.loginNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setLoginNameLike(String loginNameLike) {
		this.loginNameLike = loginNameLike;
	}

	@LikeDoubleField(column = "loginName") // 模糊条件-仅限字符型字段
	public String getLoginNameLike() {

		return this.loginNameLike;
	}

	// 大于条件
	public void setLoginNameMore(String loginNameMore) {
		this.loginNameMore = loginNameMore;
	}

	@ValueField(column = "loginName", equal = ">=") // 大于条件
	public String getLoginNameMore() {
		return this.loginNameMore;
	}

	// 小于条件
	public void setLoginNameLess(String loginNameLess) {
		this.loginNameLess = loginNameLess;
	}

	@ValueField(column = "loginName", equal = "<=") // 小于条件
	public String getLoginNameLess() {
		return this.loginNameLess;
	}

	// 等于条件
	public void setHeadPortraitSch(String headPortraitSch) {
		this.headPortraitSch = headPortraitSch;
	}

	@ValueField(column = "headPortrait") // 等于条件
	public String getHeadPortraitSch() {
		return this.headPortraitSch;
	}

	// 模糊条件-仅限字符型字段
	public void setHeadPortraitLike(String headPortraitLike) {
		this.headPortraitLike = headPortraitLike;
	}

	@LikeDoubleField(column = "headPortrait") // 模糊条件-仅限字符型字段
	public String getHeadPortraitLike() {

		return this.headPortraitLike;
	}

	// 大于条件
	public void setHeadPortraitMore(String headPortraitMore) {
		this.headPortraitMore = headPortraitMore;
	}

	@ValueField(column = "headPortrait", equal = ">=") // 大于条件
	public String getHeadPortraitMore() {
		return this.headPortraitMore;
	}

	// 小于条件
	public void setHeadPortraitLess(String headPortraitLess) {
		this.headPortraitLess = headPortraitLess;
	}

	@ValueField(column = "headPortrait", equal = "<=") // 小于条件
	public String getHeadPortraitLess() {
		return this.headPortraitLess;
	}

	// 等于条件
	public void setExt1Sch(String ext1Sch) {
		this.ext1Sch = ext1Sch;
	}

	@ValueField(column = "ext1") // 等于条件
	public String getExt1Sch() {
		return this.ext1Sch;
	}

	// 模糊条件-仅限字符型字段
	public void setExt1Like(String ext1Like) {
		this.ext1Like = ext1Like;
	}

	@LikeDoubleField(column = "ext1") // 模糊条件-仅限字符型字段
	public String getExt1Like() {

		return this.ext1Like;
	}

	// 大于条件
	public void setExt1More(String ext1More) {
		this.ext1More = ext1More;
	}

	@ValueField(column = "ext1", equal = ">=") // 大于条件
	public String getExt1More() {
		return this.ext1More;
	}

	// 小于条件
	public void setExt1Less(String ext1Less) {
		this.ext1Less = ext1Less;
	}

	@ValueField(column = "ext1", equal = "<=") // 小于条件
	public String getExt1Less() {
		return this.ext1Less;
	}

	// 等于条件
	public void setExt2Sch(String ext2Sch) {
		this.ext2Sch = ext2Sch;
	}

	@ValueField(column = "ext2") // 等于条件
	public String getExt2Sch() {
		return this.ext2Sch;
	}

	// 模糊条件-仅限字符型字段
	public void setExt2Like(String ext2Like) {
		this.ext2Like = ext2Like;
	}

	@LikeDoubleField(column = "ext2") // 模糊条件-仅限字符型字段
	public String getExt2Like() {

		return this.ext2Like;
	}

	// 大于条件
	public void setExt2More(String ext2More) {
		this.ext2More = ext2More;
	}

	@ValueField(column = "ext2", equal = ">=") // 大于条件
	public String getExt2More() {
		return this.ext2More;
	}

	// 小于条件
	public void setExt2Less(String ext2Less) {
		this.ext2Less = ext2Less;
	}

	@ValueField(column = "ext2", equal = "<=") // 小于条件
	public String getExt2Less() {
		return this.ext2Less;
	}

	// 等于条件
	public void setMainDeptIdSch(String mainDeptIdSch) {
		this.mainDeptIdSch = mainDeptIdSch;
	}

	@ValueField(column = "mainDeptId") // 等于条件
	public String getMainDeptIdSch() {
		return this.mainDeptIdSch;
	}

	// 模糊条件-仅限字符型字段
	public void setMainDeptIdLike(String mainDeptIdLike) {
		this.mainDeptIdLike = mainDeptIdLike;
	}

	@LikeDoubleField(column = "mainDeptId") // 模糊条件-仅限字符型字段
	public String getMainDeptIdLike() {

		return this.mainDeptIdLike;
	}

	// 大于条件
	public void setMainDeptIdMore(String mainDeptIdMore) {
		this.mainDeptIdMore = mainDeptIdMore;
	}

	@ValueField(column = "mainDeptId", equal = ">=") // 大于条件
	public String getMainDeptIdMore() {
		return this.mainDeptIdMore;
	}

	// 小于条件
	public void setMainDeptIdLess(String mainDeptIdLess) {
		this.mainDeptIdLess = mainDeptIdLess;
	}

	@ValueField(column = "mainDeptId", equal = "<=") // 小于条件
	public String getMainDeptIdLess() {
		return this.mainDeptIdLess;
	}

	// 等于条件
	public void setMainDeptCodeSch(String mainDeptCodeSch) {
		this.mainDeptCodeSch = mainDeptCodeSch;
	}

	@ValueField(column = "mainDeptCode") // 等于条件
	public String getMainDeptCodeSch() {
		return this.mainDeptCodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setMainDeptCodeLike(String mainDeptCodeLike) {
		this.mainDeptCodeLike = mainDeptCodeLike;
	}

	@LikeDoubleField(column = "mainDeptCode") // 模糊条件-仅限字符型字段
	public String getMainDeptCodeLike() {

		return this.mainDeptCodeLike;
	}

	// 大于条件
	public void setMainDeptCodeMore(String mainDeptCodeMore) {
		this.mainDeptCodeMore = mainDeptCodeMore;
	}

	@ValueField(column = "mainDeptCode", equal = ">=") // 大于条件
	public String getMainDeptCodeMore() {
		return this.mainDeptCodeMore;
	}

	// 小于条件
	public void setMainDeptCodeLess(String mainDeptCodeLess) {
		this.mainDeptCodeLess = mainDeptCodeLess;
	}

	@ValueField(column = "mainDeptCode", equal = "<=") // 小于条件
	public String getMainDeptCodeLess() {
		return this.mainDeptCodeLess;
	}

	// 等于条件
	public void setMainDeptNameSch(String mainDeptNameSch) {
		this.mainDeptNameSch = mainDeptNameSch;
	}

	@ValueField(column = "mainDeptName") // 等于条件
	public String getMainDeptNameSch() {
		return this.mainDeptNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setMainDeptNameLike(String mainDeptNameLike) {
		this.mainDeptNameLike = mainDeptNameLike;
	}

	@LikeDoubleField(column = "mainDeptName") // 模糊条件-仅限字符型字段
	public String getMainDeptNameLike() {

		return this.mainDeptNameLike;
	}

	// 大于条件
	public void setMainDeptNameMore(String mainDeptNameMore) {
		this.mainDeptNameMore = mainDeptNameMore;
	}

	@ValueField(column = "mainDeptName", equal = ">=") // 大于条件
	public String getMainDeptNameMore() {
		return this.mainDeptNameMore;
	}

	// 小于条件
	public void setMainDeptNameLess(String mainDeptNameLess) {
		this.mainDeptNameLess = mainDeptNameLess;
	}

	@ValueField(column = "mainDeptName", equal = "<=") // 小于条件
	public String getMainDeptNameLess() {
		return this.mainDeptNameLess;
	}

	// 等于条件
	public void setMainRoleIdSch(String mainRoleIdSch) {
		this.mainRoleIdSch = mainRoleIdSch;
	}

	@ValueField(column = "mainRoleId") // 等于条件
	public String getMainRoleIdSch() {
		return this.mainRoleIdSch;
	}

	// 模糊条件-仅限字符型字段
	public void setMainRoleIdLike(String mainRoleIdLike) {
		this.mainRoleIdLike = mainRoleIdLike;
	}

	@LikeDoubleField(column = "mainRoleId") // 模糊条件-仅限字符型字段
	public String getMainRoleIdLike() {

		return this.mainRoleIdLike;
	}

	// 大于条件
	public void setMainRoleIdMore(String mainRoleIdMore) {
		this.mainRoleIdMore = mainRoleIdMore;
	}

	@ValueField(column = "mainRoleId", equal = ">=") // 大于条件
	public String getMainRoleIdMore() {
		return this.mainRoleIdMore;
	}

	// 小于条件
	public void setMainRoleIdLess(String mainRoleIdLess) {
		this.mainRoleIdLess = mainRoleIdLess;
	}

	@ValueField(column = "mainRoleId", equal = "<=") // 小于条件
	public String getMainRoleIdLess() {
		return this.mainRoleIdLess;
	}

	// 等于条件
	public void setMainRoleCodeSch(String mainRoleCodeSch) {
		this.mainRoleCodeSch = mainRoleCodeSch;
	}

	@ValueField(column = "mainRoleCode") // 等于条件
	public String getMainRoleCodeSch() {
		return this.mainRoleCodeSch;
	}

	// 模糊条件-仅限字符型字段
	public void setMainRoleCodeLike(String mainRoleCodeLike) {
		this.mainRoleCodeLike = mainRoleCodeLike;
	}

	@LikeDoubleField(column = "mainRoleCode") // 模糊条件-仅限字符型字段
	public String getMainRoleCodeLike() {

		return this.mainRoleCodeLike;
	}

	// 大于条件
	public void setMainRoleCodeMore(String mainRoleCodeMore) {
		this.mainRoleCodeMore = mainRoleCodeMore;
	}

	@ValueField(column = "mainRoleCode", equal = ">=") // 大于条件
	public String getMainRoleCodeMore() {
		return this.mainRoleCodeMore;
	}

	// 小于条件
	public void setMainRoleCodeLess(String mainRoleCodeLess) {
		this.mainRoleCodeLess = mainRoleCodeLess;
	}

	@ValueField(column = "mainRoleCode", equal = "<=") // 小于条件
	public String getMainRoleCodeLess() {
		return this.mainRoleCodeLess;
	}

	// 等于条件
	public void setMainRoleNameSch(String mainRoleNameSch) {
		this.mainRoleNameSch = mainRoleNameSch;
	}

	@ValueField(column = "mainRoleName") // 等于条件
	public String getMainRoleNameSch() {
		return this.mainRoleNameSch;
	}

	// 模糊条件-仅限字符型字段
	public void setMainRoleNameLike(String mainRoleNameLike) {
		this.mainRoleNameLike = mainRoleNameLike;
	}

	@LikeDoubleField(column = "mainRoleName") // 模糊条件-仅限字符型字段
	public String getMainRoleNameLike() {

		return this.mainRoleNameLike;
	}

	// 大于条件
	public void setMainRoleNameMore(String mainRoleNameMore) {
		this.mainRoleNameMore = mainRoleNameMore;
	}

	@ValueField(column = "mainRoleName", equal = ">=") // 大于条件
	public String getMainRoleNameMore() {
		return this.mainRoleNameMore;
	}

	// 小于条件
	public void setMainRoleNameLess(String mainRoleNameLess) {
		this.mainRoleNameLess = mainRoleNameLess;
	}

	@ValueField(column = "mainRoleName", equal = "<=") // 小于条件
	public String getMainRoleNameLess() {
		return this.mainRoleNameLess;
	}

}
