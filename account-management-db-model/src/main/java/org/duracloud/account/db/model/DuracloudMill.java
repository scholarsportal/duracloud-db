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
 * Contains basic configuration information for interacting with the Duracloud Mill.
 *
 * @author Daniel Bernstein
 * Date: 05/06/2015
 */
@Entity
public class DuracloudMill extends BaseEntity {
    @Column(nullable = false)
    private String dbName;
    @Column(nullable = false)
    private String dbHost;
    @Column(nullable = false)
    private Integer dbPort;
    @Column(nullable = false)
    private String dbUsername;
    @Column(nullable = false)
    private String dbPassword;
    @Column(nullable = false)
    private String auditQueue;
    @Column(nullable = false)
    private String auditLogSpaceId;
    @Column(nullable = false)
    private String auditQueueType;
    @Column(nullable = true)
    private String rabbitmqHost;
    @Column(nullable = true)
    private Integer rabbitmqPort;
    @Column(nullable = true)
    private String rabbitmqVhost;
    @Column(nullable = true)
    private String rabbitmqExchange;
    @Column(nullable = true)
    private String rabbitmqUsername;
    @Column(nullable = true)
    private String rabbitmqPassword;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getAuditQueue() {
        return auditQueue;
    }

    public void setAuditQueue(String auditQueue) {
        this.auditQueue = auditQueue;
    }

    public String getAuditLogSpaceId() {
        return auditLogSpaceId;
    }

    public void setAuditLogSpaceId(String auditLogSpaceId) {
        this.auditLogSpaceId = auditLogSpaceId;
    }

    public String getAuditQueueType() {
        return auditQueueType;
    }

    public void setAuditQueueType(String auditQueueType) {
        this.auditQueueType = auditQueueType;
    }

    public String getRabbitmqHost() {
        return rabbitmqHost;
    }

    public void setRabbitmqHost(String rabbitmqHost) {
        this.rabbitmqHost = rabbitmqHost;
    }

    public Integer getRabbitmqPort() {
        return rabbitmqPort;
    }

    public void setRabbitmqPort(Integer rabbitmqPort) {
        this.rabbitmqPort = rabbitmqPort;
    }

    public String getRabbitmqVhost() {
        return rabbitmqVhost;
    }

    public void setRabbitmqVhost(String rabbitmqVhost) {
        this.rabbitmqVhost = rabbitmqVhost;
    }

    public String getRabbitmqExchange() {
        return rabbitmqExchange;
    }

    public void setRabbitmqExchange(String rabbitmqExchange) {
        this.rabbitmqExchange = rabbitmqExchange;
    }

    public String getRabbitmqUsername() {
        return rabbitmqUsername;
    }

    public void setRabbitmqUsername(String rabbitmqUsername) {
        this.rabbitmqUsername = rabbitmqUsername;
    }

    public String getRabbitmqPassword() {
        return rabbitmqPassword;
    }

    public void setRabbitmqPassword(String rabbitmqPassword) {
        this.rabbitmqPassword = rabbitmqPassword;
    }
}
