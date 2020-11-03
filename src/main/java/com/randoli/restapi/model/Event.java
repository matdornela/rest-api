package com.randoli.restapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(hidden=true, notes = "Event ID (UUID)")
    private UUID eventId;
    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;
    private int eventCnt;
}
