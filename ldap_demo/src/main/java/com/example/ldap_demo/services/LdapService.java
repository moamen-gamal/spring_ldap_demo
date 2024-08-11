package com.example.ldap_demo.services;

import com.example.ldap_demo.models.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service
public class LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;

    private final String baseDn = "ou=users,ou=system";

    // add user with his uid adn attributes
    public void addUser(LdapUser ldapUser) {
        ldapTemplate.bind("uid=" + ldapUser.getUsername() + "," + baseDn, null, ldapUser.getAttributes());
    }

    // get all users under base dn and have filter attributes and a mapper to map values to object
    public List<LdapUser> getAllUsers() {
        return ldapTemplate.search(
                baseDn,
                "(objectClass=inetOrgPerson)",
                (AttributesMapper<LdapUser>) attributes -> {
                    LdapUser ldapUser = new LdapUser();
                    ldapUser.setCn(attributes.get("cn").get().toString());
                    ldapUser.setSn(attributes.get("sn").get().toString());
                    ldapUser.setUsername(attributes.get("uid").get().toString());
                    ldapUser.setPassword(attributes.get("userPassword").get().toString());
                    return ldapUser;
                }
        );
    }

    public LdapUser getUser(String username) {
        return ldapTemplate.search(baseDn, "(uid=" + username + ")", (AttributesMapper<LdapUser>) attributes -> {
            LdapUser ldapUser = new LdapUser();
            ldapUser.setCn(attributes.get("cn").get().toString());
            ldapUser.setSn(attributes.get("sn").get().toString());
            ldapUser.setUsername(attributes.get("uid").get().toString());
            ldapUser.setPassword(attributes.get("userPassword").get().toString());
            return ldapUser;
        }).get(0);
    }

    // delete user by uid
    public void deleteUser(String username) {
        // dn name builder
        Name dn = LdapNameBuilder.newInstance(baseDn).add("uid", username).build();
        ldapTemplate.unbind(dn);
    }
}
