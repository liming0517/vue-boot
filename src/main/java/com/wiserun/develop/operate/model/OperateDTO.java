package com.wiserun.develop.operate.model;

import com.temp.common.model.BaseDTO;

public class OperateDTO extends BaseDTO{
		//id
		private String operate_id;
		//创建时间
		private String operate_CreateTime;
		//创建人
		private String operate_CreateUser;
		//状态
		private String operate_State;
		//模块编号
		private String operate_Model;
		//模块对应操作
		private String operate_operate;
		//创建开始时间
		private String operate_CreateTimeStart;
		//创建结束时间
		private String operate_CreateTimeEnd;
		
		private String operate_Name;
		public String getOperate_id() {
			return operate_id;
		}
		public void setOperate_id(String operate_id) {
			this.operate_id = operate_id;
		}
		public String getOperate_CreateTime() {
			return operate_CreateTime;
		}
		public void setOperate_CreateTime(String operate_CreateTime) {
			this.operate_CreateTime = operate_CreateTime;
		}
		public String getOperate_CreateUser() {
			return operate_CreateUser;
		}
		public void setOperate_CreateUser(String operate_CreateUser) {
			this.operate_CreateUser = operate_CreateUser;
		}
		public String getOperate_State() {
			return operate_State;
		}
		public void setOperate_State(String operate_State) {
			this.operate_State = operate_State;
		}
		public String getOperate_Model() {
			return operate_Model;
		}
		public void setOperate_Model(String operate_Model) {
			this.operate_Model = operate_Model;
		}
		public String getOperate_operate() {
			return operate_operate;
		}
		public void setOperate_operate(String operate_operate) {
			this.operate_operate = operate_operate;
		}
		public String getOperate_CreateTimeStart() {
			return operate_CreateTimeStart;
		}
		public void setOperate_CreateTimeStart(String operate_CreateTimeStart) {
			this.operate_CreateTimeStart = operate_CreateTimeStart;
		}
		public String getOperate_CreateTimeEnd() {
			return operate_CreateTimeEnd;
		}
		public void setOperate_CreateTimeEnd(String operate_CreateTimeEnd) {
			this.operate_CreateTimeEnd = operate_CreateTimeEnd;
		}
		public String getOperate_Name() {
			return operate_Name;
		}
		public void setOperate_Name(String operate_Name) {
			this.operate_Name = operate_Name;
		}
		
}
