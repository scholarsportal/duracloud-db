/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.account.db.config;

import org.duracloud.common.util.SystemPropertiesVerifier;

/**
 * 
 * @author Daniel Bernstein
 *         Date: Sep 12, 2014
 */
public class MCJpaPropertiesVerifier extends SystemPropertiesVerifier {
    public MCJpaPropertiesVerifier() {
        super(new String[] {
                ConfigConstants.MC_DB_NAME,
                ConfigConstants.MC_DB_HOST, 
                ConfigConstants.MC_DB_PORT, 
                ConfigConstants.MC_DB_USER,
                ConfigConstants.MC_DB_PASS});
    }
}
