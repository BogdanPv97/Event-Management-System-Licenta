package com.management.system.Security;

public enum UserPermissions {
    EVENT_READ("event:read"),
    EVENT_WRITE("event:write"),
    TICKET_READ("ticket:read"),
    TICKET_WRITE("ticket:write"),
    BILL_READ("bill:read"),
    BILL_WRITE("bill:write");

    private final String permission;

     UserPermissions(String permission){
        this.permission = permission;
    }

    public String getPermission(){
         return permission;
    }

}
