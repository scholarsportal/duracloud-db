/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duracloud.account.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A class of RabbitMQ configuration parameters.
 *
 * @author Andy Foster Date: 2021/05/06
 */
@Entity
@Table(name = "rabbitmq_config")
public class RabbitMQConfig extends BaseEntity {

    @Column(nullable = true)
    private String host;
    @Column(nullable = true)
    private Integer port;
    @Column(nullable = true)
    private String vhost;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String password;

    @OneToOne(fetch = FetchType.EAGER,
              cascade = CascadeType.ALL,
              mappedBy = "rabbitmqConfig")
    private GlobalProperties globalProperties;

    public RabbitMQConfig() {

    }

    public RabbitMQConfig(String host, Integer port,
                          String vhost, String username,
                          String password) {
        this.host = host;
        this.port = port;
        this.vhost = vhost;
        this.username  = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
