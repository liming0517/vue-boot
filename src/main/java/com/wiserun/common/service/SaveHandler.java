package com.wiserun.common.service;

public interface SaveHandler<Entity> {
	boolean canSave(Entity entity);
}
