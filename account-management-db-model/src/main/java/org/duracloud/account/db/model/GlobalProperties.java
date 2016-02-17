/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.account.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A grab bag of global properties.
 * @author Daniel Bernstein
 *         Date: 01/05/2016
 */
@Entity
public class GlobalProperties extends BaseEntity {
    /*
     * an SNS topic arn for sending noticiations to the instances
     */
    @Column(nullable=false)
    private String instanceNotificationTopicArn;

    @Column(nullable=false)
    private String duracloudRootUsername;

    @Column(nullable=false)
    private String duracloudRootPassword;
    
    public String getDuracloudRootPassword() {
        return duracloudRootPassword;
    }

    public void setDuracloudRootPassword(String duracloudRootPassword) {
        this.duracloudRootPassword = duracloudRootPassword;
    }

    public String getDuracloudRootUsername() {
        return duracloudRootUsername;
    }

    public void setDuracloudRootUsername(String duracloudRootUsername) {
        this.duracloudRootUsername = duracloudRootUsername;
    }

    public String getInstanceNotificationTopicArn() {
        return instanceNotificationTopicArn;
    }

    public void setInstanceNotificationTopicArn(
            String instanceNotificationTopicArn) {
        this.instanceNotificationTopicArn = instanceNotificationTopicArn;
    }   
    
}
