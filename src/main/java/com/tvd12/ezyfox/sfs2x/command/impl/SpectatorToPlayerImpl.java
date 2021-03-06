package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.SpectatorToPlayer;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see SpectatorToPlayer
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class SpectatorToPlayerImpl extends BaseCommandImpl implements SpectatorToPlayer {

    private String user;
    private String targetRoom;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public SpectatorToPlayerImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            api.spectatorToPlayer(CommandUtil.getSfsUser(user, api), 
                    CommandUtil.getSfsRoom(targetRoom, extension), 
                    fireClientEvent, fireServerEvent);
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SpectatorToPlayer#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SpectatorToPlayer user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SpectatorToPlayer#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public SpectatorToPlayer room(ApiRoom room) {
        this.targetRoom = room.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SpectatorToPlayer#fireClientEvent(boolean)
     */
    @Override
    public SpectatorToPlayer fireClientEvent(boolean value) {
        this.fireClientEvent = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SpectatorToPlayer#fireServerEvent(boolean)
     */
    @Override
    public SpectatorToPlayer fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }

}
