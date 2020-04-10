package com.wiserun.common.dao;

import com.wiserun.common.Edit;

/**
 * DAO类,其它DAO类都要继承该类
 * @author tanghc
 * 
 * @param <Entity>
 */
public interface BaseDao<Entity> extends Edit<Entity> {
}