/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.IDisconnectionReason;
import com.tvd12.ezyfox.core.command.DisconnectUser;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see DisconnectUser
 * 
 * @author tavandung12
 *
 */
public class DisconnectUserImpl extends BaseCommandImpl implements DisconnectUser {

    private String username;
    private byte reasonId;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public DisconnectUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.DisconnectUser#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public DisconnectUser user(ApiBaseUser userToDisconnect) {
        this.username = userToDisconnect.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.DisconnectUser#user(java.lang.String)
     */
    @Override
    public DisconnectUser user(String usernameToDisconnect) {
        this.username = usernameToDisconnect;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.DisconnectUser#reasonId(byte)
     */
   @Override
    public DisconnectUser reason(byte id) {
        this.reasonId = id;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User user = CommandUtil.getSfsUser(username, api);
        api.disconnectUser(user, new IDisconnectionReason() {
            
            @Override
            public int getValue() {
                return getByteValue();
            }
            
            @Override
            public byte getByteValue() {
                return reasonId;
            }
        });
        return Boolean.TRUE;
    }

}
