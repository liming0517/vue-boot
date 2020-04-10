package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;
import com.wiserun.common.expression.annotation.LikeDoubleField;
import com.wiserun.common.expression.annotation.ValueField;
import com.wiserun.common.support.SearchEasyUI;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.commontype SearchEntity查询用途类
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

public class SysCommontypeSch extends SearchEasyUI {

    private String idSch; //等于条件
        private String idLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String idMore; //大于条件
    private String idLess; //小于条件
    
    private String createDeptIdSch; //等于条件
        private String createDeptIdLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createDeptIdMore; //大于条件
    private String createDeptIdLess; //小于条件
    
    private String createDeptCodeSch; //等于条件
        private String createDeptCodeLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createDeptCodeMore; //大于条件
    private String createDeptCodeLess; //小于条件
    
    private String createDeptNameSch; //等于条件
        private String createDeptNameLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createDeptNameMore; //大于条件
    private String createDeptNameLess; //小于条件
    
    private String createAreaIdSch; //等于条件
        private String createAreaIdLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createAreaIdMore; //大于条件
    private String createAreaIdLess; //小于条件
    
    private String createAreaCodeSch; //等于条件
        private String createAreaCodeLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createAreaCodeMore; //大于条件
    private String createAreaCodeLess; //小于条件
    
    private String createAreaNameSch; //等于条件
        private String createAreaNameLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createAreaNameMore; //大于条件
    private String createAreaNameLess; //小于条件
    
    private String createUserIDSch; //等于条件
        private String createUserIDLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createUserIDMore; //大于条件
    private String createUserIDLess; //小于条件
    
    private String createUserCodeSch; //等于条件
        private String createUserCodeLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createUserCodeMore; //大于条件
    private String createUserCodeLess; //小于条件
    
    private String createUserNameSch; //等于条件
        private String createUserNameLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String createUserNameMore; //大于条件
    private String createUserNameLess; //小于条件
    
    private Date createDateSch; //等于条件
            private Date createDateMore; //大于条件
    private Date createDateLess; //小于条件
    
    private String updateUserIDSch; //等于条件
        private String updateUserIDLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String updateUserIDMore; //大于条件
    private String updateUserIDLess; //小于条件
    
    private String updateUserCodeSch; //等于条件
        private String updateUserCodeLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String updateUserCodeMore; //大于条件
    private String updateUserCodeLess; //小于条件
    
    private String updateUserNameSch; //等于条件
        private String updateUserNameLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String updateUserNameMore; //大于条件
    private String updateUserNameLess; //小于条件
    
    private Date updateDateSch; //等于条件
            private Date updateDateMore; //大于条件
    private Date updateDateLess; //小于条件
    
    private Integer rowOrderSch; //等于条件
        private Integer rowOrderLike; //模糊 LIKE 条件 仅限字符型字段 int
        private Integer rowOrderMore; //大于条件
    private Integer rowOrderLess; //小于条件
    
    private String remarkSch; //等于条件
        private String remarkLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String remarkMore; //大于条件
    private String remarkLess; //小于条件
    
    private String stateSch; //等于条件
        private String stateLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String stateMore; //大于条件
    private String stateLess; //小于条件
    
    private String typecodeSch; //等于条件
        private String typecodeLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String typecodeMore; //大于条件
    private String typecodeLess; //小于条件
    
    private String typenameSch; //等于条件
        private String typenameLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String typenameMore; //大于条件
    private String typenameLess; //小于条件
    
    private String parentIdSch; //等于条件
        private String parentIdLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String parentIdMore; //大于条件
    private String parentIdLess; //小于条件
    
    private String parentCodeSch; //等于条件
        private String parentCodeLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String parentCodeMore; //大于条件
    private String parentCodeLess; //小于条件
    
    private String parentNameSch; //等于条件
        private String parentNameLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String parentNameMore; //大于条件
    private String parentNameLess; //小于条件
    
    private String typevalueSch; //等于条件
        private String typevalueLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String typevalueMore; //大于条件
    private String typevalueLess; //小于条件
    
    private String typedescSch; //等于条件
        private String typedescLike; //模糊 LIKE 条件 仅限字符型字段 nvarchar
        private String typedescMore; //大于条件
    private String typedescLess; //小于条件
    
    private Integer levelSch; //等于条件
        private Integer levelLike; //模糊 LIKE 条件 仅限字符型字段 int
        private Integer levelMore; //大于条件
    private Integer levelLess; //小于条件
    

       //等于条件
    public void setIdSch(String idSch){
        this.idSch = idSch;
    }
    @ValueField(column = "id") //等于条件
    public String getIdSch(){
        return this.idSch;
    }

              //模糊条件-仅限字符型字段
    public void setIdLike(String idLike){
        this.idLike = idLike;
    }
    @LikeDoubleField(column = "id") //模糊条件-仅限字符型字段
    public String getIdLike(){
    	    	    	        
        return this.idLike;
    }
    
	    //大于条件
    public void setIdMore(String idMore){
        this.idMore = idMore;
    }
    @ValueField(column = "id" ,equal=">=") //大于条件
    public String getIdMore(){
        return this.idMore;
    }

	    //小于条件
    public void setIdLess(String idLess){
        this.idLess = idLess;
    }
    @ValueField(column = "id" ,equal="<=") //小于条件
    public String getIdLess(){
        return this.idLess;
    }

       //等于条件
    public void setCreateDeptIdSch(String createDeptIdSch){
        this.createDeptIdSch = createDeptIdSch;
    }
    @ValueField(column = "createDeptId") //等于条件
    public String getCreateDeptIdSch(){
        return this.createDeptIdSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateDeptIdLike(String createDeptIdLike){
        this.createDeptIdLike = createDeptIdLike;
    }
    @LikeDoubleField(column = "createDeptId") //模糊条件-仅限字符型字段
    public String getCreateDeptIdLike(){
    	    	    	        
        return this.createDeptIdLike;
    }
    
	    //大于条件
    public void setCreateDeptIdMore(String createDeptIdMore){
        this.createDeptIdMore = createDeptIdMore;
    }
    @ValueField(column = "createDeptId" ,equal=">=") //大于条件
    public String getCreateDeptIdMore(){
        return this.createDeptIdMore;
    }

	    //小于条件
    public void setCreateDeptIdLess(String createDeptIdLess){
        this.createDeptIdLess = createDeptIdLess;
    }
    @ValueField(column = "createDeptId" ,equal="<=") //小于条件
    public String getCreateDeptIdLess(){
        return this.createDeptIdLess;
    }

       //等于条件
    public void setCreateDeptCodeSch(String createDeptCodeSch){
        this.createDeptCodeSch = createDeptCodeSch;
    }
    @ValueField(column = "createDeptCode") //等于条件
    public String getCreateDeptCodeSch(){
        return this.createDeptCodeSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateDeptCodeLike(String createDeptCodeLike){
        this.createDeptCodeLike = createDeptCodeLike;
    }
    @LikeDoubleField(column = "createDeptCode") //模糊条件-仅限字符型字段
    public String getCreateDeptCodeLike(){
    	    	    	        
        return this.createDeptCodeLike;
    }
    
	    //大于条件
    public void setCreateDeptCodeMore(String createDeptCodeMore){
        this.createDeptCodeMore = createDeptCodeMore;
    }
    @ValueField(column = "createDeptCode" ,equal=">=") //大于条件
    public String getCreateDeptCodeMore(){
        return this.createDeptCodeMore;
    }

	    //小于条件
    public void setCreateDeptCodeLess(String createDeptCodeLess){
        this.createDeptCodeLess = createDeptCodeLess;
    }
    @ValueField(column = "createDeptCode" ,equal="<=") //小于条件
    public String getCreateDeptCodeLess(){
        return this.createDeptCodeLess;
    }

       //等于条件
    public void setCreateDeptNameSch(String createDeptNameSch){
        this.createDeptNameSch = createDeptNameSch;
    }
    @ValueField(column = "createDeptName") //等于条件
    public String getCreateDeptNameSch(){
        return this.createDeptNameSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateDeptNameLike(String createDeptNameLike){
        this.createDeptNameLike = createDeptNameLike;
    }
    @LikeDoubleField(column = "createDeptName") //模糊条件-仅限字符型字段
    public String getCreateDeptNameLike(){
    	    	    	        
        return this.createDeptNameLike;
    }
    
	    //大于条件
    public void setCreateDeptNameMore(String createDeptNameMore){
        this.createDeptNameMore = createDeptNameMore;
    }
    @ValueField(column = "createDeptName" ,equal=">=") //大于条件
    public String getCreateDeptNameMore(){
        return this.createDeptNameMore;
    }

	    //小于条件
    public void setCreateDeptNameLess(String createDeptNameLess){
        this.createDeptNameLess = createDeptNameLess;
    }
    @ValueField(column = "createDeptName" ,equal="<=") //小于条件
    public String getCreateDeptNameLess(){
        return this.createDeptNameLess;
    }

       //等于条件
    public void setCreateAreaIdSch(String createAreaIdSch){
        this.createAreaIdSch = createAreaIdSch;
    }
    @ValueField(column = "createAreaId") //等于条件
    public String getCreateAreaIdSch(){
        return this.createAreaIdSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateAreaIdLike(String createAreaIdLike){
        this.createAreaIdLike = createAreaIdLike;
    }
    @LikeDoubleField(column = "createAreaId") //模糊条件-仅限字符型字段
    public String getCreateAreaIdLike(){
    	    	    	        
        return this.createAreaIdLike;
    }
    
	    //大于条件
    public void setCreateAreaIdMore(String createAreaIdMore){
        this.createAreaIdMore = createAreaIdMore;
    }
    @ValueField(column = "createAreaId" ,equal=">=") //大于条件
    public String getCreateAreaIdMore(){
        return this.createAreaIdMore;
    }

	    //小于条件
    public void setCreateAreaIdLess(String createAreaIdLess){
        this.createAreaIdLess = createAreaIdLess;
    }
    @ValueField(column = "createAreaId" ,equal="<=") //小于条件
    public String getCreateAreaIdLess(){
        return this.createAreaIdLess;
    }

       //等于条件
    public void setCreateAreaCodeSch(String createAreaCodeSch){
        this.createAreaCodeSch = createAreaCodeSch;
    }
    @ValueField(column = "createAreaCode") //等于条件
    public String getCreateAreaCodeSch(){
        return this.createAreaCodeSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateAreaCodeLike(String createAreaCodeLike){
        this.createAreaCodeLike = createAreaCodeLike;
    }
    @LikeDoubleField(column = "createAreaCode") //模糊条件-仅限字符型字段
    public String getCreateAreaCodeLike(){
    	    	    	        
        return this.createAreaCodeLike;
    }
    
	    //大于条件
    public void setCreateAreaCodeMore(String createAreaCodeMore){
        this.createAreaCodeMore = createAreaCodeMore;
    }
    @ValueField(column = "createAreaCode" ,equal=">=") //大于条件
    public String getCreateAreaCodeMore(){
        return this.createAreaCodeMore;
    }

	    //小于条件
    public void setCreateAreaCodeLess(String createAreaCodeLess){
        this.createAreaCodeLess = createAreaCodeLess;
    }
    @ValueField(column = "createAreaCode" ,equal="<=") //小于条件
    public String getCreateAreaCodeLess(){
        return this.createAreaCodeLess;
    }

       //等于条件
    public void setCreateAreaNameSch(String createAreaNameSch){
        this.createAreaNameSch = createAreaNameSch;
    }
    @ValueField(column = "createAreaName") //等于条件
    public String getCreateAreaNameSch(){
        return this.createAreaNameSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateAreaNameLike(String createAreaNameLike){
        this.createAreaNameLike = createAreaNameLike;
    }
    @LikeDoubleField(column = "createAreaName") //模糊条件-仅限字符型字段
    public String getCreateAreaNameLike(){
    	    	    	        
        return this.createAreaNameLike;
    }
    
	    //大于条件
    public void setCreateAreaNameMore(String createAreaNameMore){
        this.createAreaNameMore = createAreaNameMore;
    }
    @ValueField(column = "createAreaName" ,equal=">=") //大于条件
    public String getCreateAreaNameMore(){
        return this.createAreaNameMore;
    }

	    //小于条件
    public void setCreateAreaNameLess(String createAreaNameLess){
        this.createAreaNameLess = createAreaNameLess;
    }
    @ValueField(column = "createAreaName" ,equal="<=") //小于条件
    public String getCreateAreaNameLess(){
        return this.createAreaNameLess;
    }

       //等于条件
    public void setCreateUserIDSch(String createUserIDSch){
        this.createUserIDSch = createUserIDSch;
    }
    @ValueField(column = "createUserID") //等于条件
    public String getCreateUserIDSch(){
        return this.createUserIDSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateUserIDLike(String createUserIDLike){
        this.createUserIDLike = createUserIDLike;
    }
    @LikeDoubleField(column = "createUserID") //模糊条件-仅限字符型字段
    public String getCreateUserIDLike(){
    	    	    	        
        return this.createUserIDLike;
    }
    
	    //大于条件
    public void setCreateUserIDMore(String createUserIDMore){
        this.createUserIDMore = createUserIDMore;
    }
    @ValueField(column = "createUserID" ,equal=">=") //大于条件
    public String getCreateUserIDMore(){
        return this.createUserIDMore;
    }

	    //小于条件
    public void setCreateUserIDLess(String createUserIDLess){
        this.createUserIDLess = createUserIDLess;
    }
    @ValueField(column = "createUserID" ,equal="<=") //小于条件
    public String getCreateUserIDLess(){
        return this.createUserIDLess;
    }

       //等于条件
    public void setCreateUserCodeSch(String createUserCodeSch){
        this.createUserCodeSch = createUserCodeSch;
    }
    @ValueField(column = "createUserCode") //等于条件
    public String getCreateUserCodeSch(){
        return this.createUserCodeSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateUserCodeLike(String createUserCodeLike){
        this.createUserCodeLike = createUserCodeLike;
    }
    @LikeDoubleField(column = "createUserCode") //模糊条件-仅限字符型字段
    public String getCreateUserCodeLike(){
    	    	    	        
        return this.createUserCodeLike;
    }
    
	    //大于条件
    public void setCreateUserCodeMore(String createUserCodeMore){
        this.createUserCodeMore = createUserCodeMore;
    }
    @ValueField(column = "createUserCode" ,equal=">=") //大于条件
    public String getCreateUserCodeMore(){
        return this.createUserCodeMore;
    }

	    //小于条件
    public void setCreateUserCodeLess(String createUserCodeLess){
        this.createUserCodeLess = createUserCodeLess;
    }
    @ValueField(column = "createUserCode" ,equal="<=") //小于条件
    public String getCreateUserCodeLess(){
        return this.createUserCodeLess;
    }

       //等于条件
    public void setCreateUserNameSch(String createUserNameSch){
        this.createUserNameSch = createUserNameSch;
    }
    @ValueField(column = "createUserName") //等于条件
    public String getCreateUserNameSch(){
        return this.createUserNameSch;
    }

              //模糊条件-仅限字符型字段
    public void setCreateUserNameLike(String createUserNameLike){
        this.createUserNameLike = createUserNameLike;
    }
    @LikeDoubleField(column = "createUserName") //模糊条件-仅限字符型字段
    public String getCreateUserNameLike(){
    	    	    	        
        return this.createUserNameLike;
    }
    
	    //大于条件
    public void setCreateUserNameMore(String createUserNameMore){
        this.createUserNameMore = createUserNameMore;
    }
    @ValueField(column = "createUserName" ,equal=">=") //大于条件
    public String getCreateUserNameMore(){
        return this.createUserNameMore;
    }

	    //小于条件
    public void setCreateUserNameLess(String createUserNameLess){
        this.createUserNameLess = createUserNameLess;
    }
    @ValueField(column = "createUserName" ,equal="<=") //小于条件
    public String getCreateUserNameLess(){
        return this.createUserNameLess;
    }

       //等于条件
    public void setCreateDateSch(Date createDateSch){
        this.createDateSch = createDateSch;
    }
    @ValueField(column = "createDate") //等于条件
    public Date getCreateDateSch(){
        return this.createDateSch;
    }

               
	    //大于条件
    public void setCreateDateMore(Date createDateMore){
        this.createDateMore = createDateMore;
    }
    @ValueField(column = "createDate" ,equal=">=") //大于条件
    public Date getCreateDateMore(){
        return this.createDateMore;
    }

	    //小于条件
    public void setCreateDateLess(Date createDateLess){
        this.createDateLess = createDateLess;
    }
    @ValueField(column = "createDate" ,equal="<=") //小于条件
    public Date getCreateDateLess(){
        return this.createDateLess;
    }

       //等于条件
    public void setUpdateUserIDSch(String updateUserIDSch){
        this.updateUserIDSch = updateUserIDSch;
    }
    @ValueField(column = "updateUserID") //等于条件
    public String getUpdateUserIDSch(){
        return this.updateUserIDSch;
    }

              //模糊条件-仅限字符型字段
    public void setUpdateUserIDLike(String updateUserIDLike){
        this.updateUserIDLike = updateUserIDLike;
    }
    @LikeDoubleField(column = "updateUserID") //模糊条件-仅限字符型字段
    public String getUpdateUserIDLike(){
    	    	    	        
        return this.updateUserIDLike;
    }
    
	    //大于条件
    public void setUpdateUserIDMore(String updateUserIDMore){
        this.updateUserIDMore = updateUserIDMore;
    }
    @ValueField(column = "updateUserID" ,equal=">=") //大于条件
    public String getUpdateUserIDMore(){
        return this.updateUserIDMore;
    }

	    //小于条件
    public void setUpdateUserIDLess(String updateUserIDLess){
        this.updateUserIDLess = updateUserIDLess;
    }
    @ValueField(column = "updateUserID" ,equal="<=") //小于条件
    public String getUpdateUserIDLess(){
        return this.updateUserIDLess;
    }

       //等于条件
    public void setUpdateUserCodeSch(String updateUserCodeSch){
        this.updateUserCodeSch = updateUserCodeSch;
    }
    @ValueField(column = "updateUserCode") //等于条件
    public String getUpdateUserCodeSch(){
        return this.updateUserCodeSch;
    }

              //模糊条件-仅限字符型字段
    public void setUpdateUserCodeLike(String updateUserCodeLike){
        this.updateUserCodeLike = updateUserCodeLike;
    }
    @LikeDoubleField(column = "updateUserCode") //模糊条件-仅限字符型字段
    public String getUpdateUserCodeLike(){
    	    	    	        
        return this.updateUserCodeLike;
    }
    
	    //大于条件
    public void setUpdateUserCodeMore(String updateUserCodeMore){
        this.updateUserCodeMore = updateUserCodeMore;
    }
    @ValueField(column = "updateUserCode" ,equal=">=") //大于条件
    public String getUpdateUserCodeMore(){
        return this.updateUserCodeMore;
    }

	    //小于条件
    public void setUpdateUserCodeLess(String updateUserCodeLess){
        this.updateUserCodeLess = updateUserCodeLess;
    }
    @ValueField(column = "updateUserCode" ,equal="<=") //小于条件
    public String getUpdateUserCodeLess(){
        return this.updateUserCodeLess;
    }

       //等于条件
    public void setUpdateUserNameSch(String updateUserNameSch){
        this.updateUserNameSch = updateUserNameSch;
    }
    @ValueField(column = "updateUserName") //等于条件
    public String getUpdateUserNameSch(){
        return this.updateUserNameSch;
    }

              //模糊条件-仅限字符型字段
    public void setUpdateUserNameLike(String updateUserNameLike){
        this.updateUserNameLike = updateUserNameLike;
    }
    @LikeDoubleField(column = "updateUserName") //模糊条件-仅限字符型字段
    public String getUpdateUserNameLike(){
    	    	    	        
        return this.updateUserNameLike;
    }
    
	    //大于条件
    public void setUpdateUserNameMore(String updateUserNameMore){
        this.updateUserNameMore = updateUserNameMore;
    }
    @ValueField(column = "updateUserName" ,equal=">=") //大于条件
    public String getUpdateUserNameMore(){
        return this.updateUserNameMore;
    }

	    //小于条件
    public void setUpdateUserNameLess(String updateUserNameLess){
        this.updateUserNameLess = updateUserNameLess;
    }
    @ValueField(column = "updateUserName" ,equal="<=") //小于条件
    public String getUpdateUserNameLess(){
        return this.updateUserNameLess;
    }

       //等于条件
    public void setUpdateDateSch(Date updateDateSch){
        this.updateDateSch = updateDateSch;
    }
    @ValueField(column = "updateDate") //等于条件
    public Date getUpdateDateSch(){
        return this.updateDateSch;
    }

               
	    //大于条件
    public void setUpdateDateMore(Date updateDateMore){
        this.updateDateMore = updateDateMore;
    }
    @ValueField(column = "updateDate" ,equal=">=") //大于条件
    public Date getUpdateDateMore(){
        return this.updateDateMore;
    }

	    //小于条件
    public void setUpdateDateLess(Date updateDateLess){
        this.updateDateLess = updateDateLess;
    }
    @ValueField(column = "updateDate" ,equal="<=") //小于条件
    public Date getUpdateDateLess(){
        return this.updateDateLess;
    }

       //等于条件
    public void setRowOrderSch(Integer rowOrderSch){
        this.rowOrderSch = rowOrderSch;
    }
    @ValueField(column = "rowOrder") //等于条件
    public Integer getRowOrderSch(){
        return this.rowOrderSch;
    }

              //模糊条件-仅限字符型字段
    public void setRowOrderLike(Integer rowOrderLike){
        this.rowOrderLike = rowOrderLike;
    }
    @LikeDoubleField(column = "rowOrder") //模糊条件-仅限字符型字段
    public Integer getRowOrderLike(){
    	    	    	        
        return this.rowOrderLike;
    }
    
	    //大于条件
    public void setRowOrderMore(Integer rowOrderMore){
        this.rowOrderMore = rowOrderMore;
    }
    @ValueField(column = "rowOrder" ,equal=">=") //大于条件
    public Integer getRowOrderMore(){
        return this.rowOrderMore;
    }

	    //小于条件
    public void setRowOrderLess(Integer rowOrderLess){
        this.rowOrderLess = rowOrderLess;
    }
    @ValueField(column = "rowOrder" ,equal="<=") //小于条件
    public Integer getRowOrderLess(){
        return this.rowOrderLess;
    }

       //等于条件
    public void setRemarkSch(String remarkSch){
        this.remarkSch = remarkSch;
    }
    @ValueField(column = "remark") //等于条件
    public String getRemarkSch(){
        return this.remarkSch;
    }

              //模糊条件-仅限字符型字段
    public void setRemarkLike(String remarkLike){
        this.remarkLike = remarkLike;
    }
    @LikeDoubleField(column = "remark") //模糊条件-仅限字符型字段
    public String getRemarkLike(){
    	    	    	        
        return this.remarkLike;
    }
    
	    //大于条件
    public void setRemarkMore(String remarkMore){
        this.remarkMore = remarkMore;
    }
    @ValueField(column = "remark" ,equal=">=") //大于条件
    public String getRemarkMore(){
        return this.remarkMore;
    }

	    //小于条件
    public void setRemarkLess(String remarkLess){
        this.remarkLess = remarkLess;
    }
    @ValueField(column = "remark" ,equal="<=") //小于条件
    public String getRemarkLess(){
        return this.remarkLess;
    }

       //等于条件
    public void setStateSch(String stateSch){
        this.stateSch = stateSch;
    }
    @ValueField(column = "state") //等于条件
    public String getStateSch(){
        return this.stateSch;
    }

              //模糊条件-仅限字符型字段
    public void setStateLike(String stateLike){
        this.stateLike = stateLike;
    }
    @LikeDoubleField(column = "state") //模糊条件-仅限字符型字段
    public String getStateLike(){
    	    	    	        
        return this.stateLike;
    }
    
	    //大于条件
    public void setStateMore(String stateMore){
        this.stateMore = stateMore;
    }
    @ValueField(column = "state" ,equal=">=") //大于条件
    public String getStateMore(){
        return this.stateMore;
    }

	    //小于条件
    public void setStateLess(String stateLess){
        this.stateLess = stateLess;
    }
    @ValueField(column = "state" ,equal="<=") //小于条件
    public String getStateLess(){
        return this.stateLess;
    }

       //等于条件
    public void setTypecodeSch(String typecodeSch){
        this.typecodeSch = typecodeSch;
    }
    @ValueField(column = "typecode") //等于条件
    public String getTypecodeSch(){
        return this.typecodeSch;
    }

              //模糊条件-仅限字符型字段
    public void setTypecodeLike(String typecodeLike){
        this.typecodeLike = typecodeLike;
    }
    @LikeDoubleField(column = "typecode") //模糊条件-仅限字符型字段
    public String getTypecodeLike(){
    	    	    	        
        return this.typecodeLike;
    }
    
	    //大于条件
    public void setTypecodeMore(String typecodeMore){
        this.typecodeMore = typecodeMore;
    }
    @ValueField(column = "typecode" ,equal=">=") //大于条件
    public String getTypecodeMore(){
        return this.typecodeMore;
    }

	    //小于条件
    public void setTypecodeLess(String typecodeLess){
        this.typecodeLess = typecodeLess;
    }
    @ValueField(column = "typecode" ,equal="<=") //小于条件
    public String getTypecodeLess(){
        return this.typecodeLess;
    }

       //等于条件
    public void setTypenameSch(String typenameSch){
        this.typenameSch = typenameSch;
    }
    @ValueField(column = "typename") //等于条件
    public String getTypenameSch(){
        return this.typenameSch;
    }

              //模糊条件-仅限字符型字段
    public void setTypenameLike(String typenameLike){
        this.typenameLike = typenameLike;
    }
    @LikeDoubleField(column = "typename") //模糊条件-仅限字符型字段
    public String getTypenameLike(){
    	    	    	        
        return this.typenameLike;
    }
    
	    //大于条件
    public void setTypenameMore(String typenameMore){
        this.typenameMore = typenameMore;
    }
    @ValueField(column = "typename" ,equal=">=") //大于条件
    public String getTypenameMore(){
        return this.typenameMore;
    }

	    //小于条件
    public void setTypenameLess(String typenameLess){
        this.typenameLess = typenameLess;
    }
    @ValueField(column = "typename" ,equal="<=") //小于条件
    public String getTypenameLess(){
        return this.typenameLess;
    }

       //等于条件
    public void setParentIdSch(String parentIdSch){
        this.parentIdSch = parentIdSch;
    }
    @ValueField(column = "parentId") //等于条件
    public String getParentIdSch(){
        return this.parentIdSch;
    }

              //模糊条件-仅限字符型字段
    public void setParentIdLike(String parentIdLike){
        this.parentIdLike = parentIdLike;
    }
    @LikeDoubleField(column = "parentId") //模糊条件-仅限字符型字段
    public String getParentIdLike(){
    	    	    	        
        return this.parentIdLike;
    }
    
	    //大于条件
    public void setParentIdMore(String parentIdMore){
        this.parentIdMore = parentIdMore;
    }
    @ValueField(column = "parentId" ,equal=">=") //大于条件
    public String getParentIdMore(){
        return this.parentIdMore;
    }

	    //小于条件
    public void setParentIdLess(String parentIdLess){
        this.parentIdLess = parentIdLess;
    }
    @ValueField(column = "parentId" ,equal="<=") //小于条件
    public String getParentIdLess(){
        return this.parentIdLess;
    }

       //等于条件
    public void setParentCodeSch(String parentCodeSch){
        this.parentCodeSch = parentCodeSch;
    }
    @ValueField(column = "parentCode") //等于条件
    public String getParentCodeSch(){
        return this.parentCodeSch;
    }

              //模糊条件-仅限字符型字段
    public void setParentCodeLike(String parentCodeLike){
        this.parentCodeLike = parentCodeLike;
    }
    @LikeDoubleField(column = "parentCode") //模糊条件-仅限字符型字段
    public String getParentCodeLike(){
    	    	    	        
        return this.parentCodeLike;
    }
    
	    //大于条件
    public void setParentCodeMore(String parentCodeMore){
        this.parentCodeMore = parentCodeMore;
    }
    @ValueField(column = "parentCode" ,equal=">=") //大于条件
    public String getParentCodeMore(){
        return this.parentCodeMore;
    }

	    //小于条件
    public void setParentCodeLess(String parentCodeLess){
        this.parentCodeLess = parentCodeLess;
    }
    @ValueField(column = "parentCode" ,equal="<=") //小于条件
    public String getParentCodeLess(){
        return this.parentCodeLess;
    }

       //等于条件
    public void setParentNameSch(String parentNameSch){
        this.parentNameSch = parentNameSch;
    }
    @ValueField(column = "parentName") //等于条件
    public String getParentNameSch(){
        return this.parentNameSch;
    }

              //模糊条件-仅限字符型字段
    public void setParentNameLike(String parentNameLike){
        this.parentNameLike = parentNameLike;
    }
    @LikeDoubleField(column = "parentName") //模糊条件-仅限字符型字段
    public String getParentNameLike(){
    	    	    	        
        return this.parentNameLike;
    }
    
	    //大于条件
    public void setParentNameMore(String parentNameMore){
        this.parentNameMore = parentNameMore;
    }
    @ValueField(column = "parentName" ,equal=">=") //大于条件
    public String getParentNameMore(){
        return this.parentNameMore;
    }

	    //小于条件
    public void setParentNameLess(String parentNameLess){
        this.parentNameLess = parentNameLess;
    }
    @ValueField(column = "parentName" ,equal="<=") //小于条件
    public String getParentNameLess(){
        return this.parentNameLess;
    }

       //等于条件
    public void setTypevalueSch(String typevalueSch){
        this.typevalueSch = typevalueSch;
    }
    @ValueField(column = "typevalue") //等于条件
    public String getTypevalueSch(){
        return this.typevalueSch;
    }

              //模糊条件-仅限字符型字段
    public void setTypevalueLike(String typevalueLike){
        this.typevalueLike = typevalueLike;
    }
    @LikeDoubleField(column = "typevalue") //模糊条件-仅限字符型字段
    public String getTypevalueLike(){
    	    	    	        
        return this.typevalueLike;
    }
    
	    //大于条件
    public void setTypevalueMore(String typevalueMore){
        this.typevalueMore = typevalueMore;
    }
    @ValueField(column = "typevalue" ,equal=">=") //大于条件
    public String getTypevalueMore(){
        return this.typevalueMore;
    }

	    //小于条件
    public void setTypevalueLess(String typevalueLess){
        this.typevalueLess = typevalueLess;
    }
    @ValueField(column = "typevalue" ,equal="<=") //小于条件
    public String getTypevalueLess(){
        return this.typevalueLess;
    }

       //等于条件
    public void setTypedescSch(String typedescSch){
        this.typedescSch = typedescSch;
    }
    @ValueField(column = "typedesc") //等于条件
    public String getTypedescSch(){
        return this.typedescSch;
    }

              //模糊条件-仅限字符型字段
    public void setTypedescLike(String typedescLike){
        this.typedescLike = typedescLike;
    }
    @LikeDoubleField(column = "typedesc") //模糊条件-仅限字符型字段
    public String getTypedescLike(){
    	    	    	        
        return this.typedescLike;
    }
    
	    //大于条件
    public void setTypedescMore(String typedescMore){
        this.typedescMore = typedescMore;
    }
    @ValueField(column = "typedesc" ,equal=">=") //大于条件
    public String getTypedescMore(){
        return this.typedescMore;
    }

	    //小于条件
    public void setTypedescLess(String typedescLess){
        this.typedescLess = typedescLess;
    }
    @ValueField(column = "typedesc" ,equal="<=") //小于条件
    public String getTypedescLess(){
        return this.typedescLess;
    }

       //等于条件
    public void setLevelSch(Integer levelSch){
        this.levelSch = levelSch;
    }
    @ValueField(column = "level") //等于条件
    public Integer getLevelSch(){
        return this.levelSch;
    }

              //模糊条件-仅限字符型字段
    public void setLevelLike(Integer levelLike){
        this.levelLike = levelLike;
    }
    @LikeDoubleField(column = "level") //模糊条件-仅限字符型字段
    public Integer getLevelLike(){
    	    	    	        
        return this.levelLike;
    }
    
	    //大于条件
    public void setLevelMore(Integer levelMore){
        this.levelMore = levelMore;
    }
    @ValueField(column = "level" ,equal=">=") //大于条件
    public Integer getLevelMore(){
        return this.levelMore;
    }

	    //小于条件
    public void setLevelLess(Integer levelLess){
        this.levelLess = levelLess;
    }
    @ValueField(column = "level" ,equal="<=") //小于条件
    public Integer getLevelLess(){
        return this.levelLess;
    }

 	
}
