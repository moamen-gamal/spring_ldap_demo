package com.example.ldap_demo.models;

import lombok.Data;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

@Data
public class LdapUser {

    private String cn;
    private String sn;
    private String username;
    private String password;

    // attributes are to be set in the getAttributes method for adding user to ldap
    public Attributes getAttributes() {
        Attributes attributes = new BasicAttributes();
        attributes.put("objectClass", "inetOrgPerson");
        attributes.put("cn", cn);
        attributes.put("sn", sn);
        attributes.put("uid", username);
        attributes.put("userPassword", password);
        return attributes;
    }
}
