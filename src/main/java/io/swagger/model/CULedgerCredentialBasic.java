package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CULedgerCredentialBasic
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-14T21:44:13.544Z[GMT]")

public class CULedgerCredentialBasic   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("memberId")
  private String memberId = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("memberSince")
  private String memberSince = null;

  @JsonProperty("credDataPayload")
  private String credDataPayload = null;

  public CULedgerCredentialBasic id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier of this credential.
   * @return id
  **/
  @ApiModelProperty(example = "UUID-GOES-HERE", value = "The unique identifier of this credential.")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CULedgerCredentialBasic memberId(String memberId) {
    this.memberId = memberId;
    return this;
  }

  /**
   * MemberId in the FIs system (often called Member Number), stored as string for flexibility.
   * @return memberId
  **/
  @ApiModelProperty(example = "1234", required = true, value = "MemberId in the FIs system (often called Member Number), stored as string for flexibility.")
  @NotNull


  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public CULedgerCredentialBasic status(String status) {
    this.status = status;
    return this;
  }

  /**
   * dasdfasdf
   * @return status
  **/
  @ApiModelProperty(example = "current|revoked", value = "dasdfasdf")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CULedgerCredentialBasic memberSince(String memberSince) {
    this.memberSince = memberSince;
    return this;
  }

  /**
   * Get memberSince
   * @return memberSince
  **/
  @ApiModelProperty(example = "DEC 2012", value = "")


  public String getMemberSince() {
    return memberSince;
  }

  public void setMemberSince(String memberSince) {
    this.memberSince = memberSince;
  }

  public CULedgerCredentialBasic credDataPayload(String credDataPayload) {
    this.credDataPayload = credDataPayload;
    return this;
  }

  /**
   * Get credDataPayload
   * @return credDataPayload
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCredDataPayload() {
    return credDataPayload;
  }

  public void setCredDataPayload(String credDataPayload) {
    this.credDataPayload = credDataPayload;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerCredentialBasic cuLedgerCredentialBasic = (CULedgerCredentialBasic) o;
    return Objects.equals(this.id, cuLedgerCredentialBasic.id) &&
        Objects.equals(this.memberId, cuLedgerCredentialBasic.memberId) &&
        Objects.equals(this.status, cuLedgerCredentialBasic.status) &&
        Objects.equals(this.memberSince, cuLedgerCredentialBasic.memberSince) &&
        Objects.equals(this.credDataPayload, cuLedgerCredentialBasic.credDataPayload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, memberId, status, memberSince, credDataPayload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerCredentialBasic {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    memberSince: ").append(toIndentedString(memberSince)).append("\n");
    sb.append("    credDataPayload: ").append(toIndentedString(credDataPayload)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

