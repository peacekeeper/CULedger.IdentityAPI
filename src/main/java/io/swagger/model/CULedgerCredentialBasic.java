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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-30T11:45:41.226Z[GMT]")

public class CULedgerCredentialBasic   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("Institution")
  private String institution = null;

  @JsonProperty("MemberNumber")
  private String memberNumber = null;

  @JsonProperty("MemberSince")
  private String memberSince = null;

  public CULedgerCredentialBasic id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier of this credential.
   * @return id
  **/
  @ApiModelProperty(example = "UUID-GOES-HERE", required = true, value = "The unique identifier of this credential.")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CULedgerCredentialBasic institution(String institution) {
    this.institution = institution;
    return this;
  }

  /**
   * Name of the institution that issued the credential.
   * @return institution
  **/
  @ApiModelProperty(example = "CULedger Credit Union", required = true, value = "Name of the institution that issued the credential.")
  @NotNull


  public String getInstitution() {
    return institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public CULedgerCredentialBasic memberNumber(String memberNumber) {
    this.memberNumber = memberNumber;
    return this;
  }

  /**
   * MemberId in the FIs system (often called Member Number), stored as string for flexibility.
   * @return memberNumber
  **/
  @ApiModelProperty(example = "1234", value = "MemberId in the FIs system (often called Member Number), stored as string for flexibility.")


  public String getMemberNumber() {
    return memberNumber;
  }

  public void setMemberNumber(String memberNumber) {
    this.memberNumber = memberNumber;
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
        Objects.equals(this.institution, cuLedgerCredentialBasic.institution) &&
        Objects.equals(this.memberNumber, cuLedgerCredentialBasic.memberNumber) &&
        Objects.equals(this.memberSince, cuLedgerCredentialBasic.memberSince);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, institution, memberNumber, memberSince);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerCredentialBasic {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    institution: ").append(toIndentedString(institution)).append("\n");
    sb.append("    memberNumber: ").append(toIndentedString(memberNumber)).append("\n");
    sb.append("    memberSince: ").append(toIndentedString(memberSince)).append("\n");
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

