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
 * CULedgerCredentialMyCUIDBasic
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-02T19:50:39.116Z[GMT]")
public class CULedgerCredentialMyCUIDBasic   {
  @JsonProperty("CredentialId")
  private String credentialId = null;

  @JsonProperty("Institution")
  private String institution = null;

  @JsonProperty("Credential")
  private String credential = null;

  @JsonProperty("MemberNumber")
  private String memberNumber = null;

  @JsonProperty("MemberSince")
  private String memberSince = null;

  public CULedgerCredentialMyCUIDBasic credentialId(String credentialId) {
    this.credentialId = credentialId;
    return this;
  }

  /**
   * The unique identifier (e.g. a UUID, integer, or string) of this credential. This identifier is intended to be used by developers to map the individual credentials that a member may have. 
   * @return credentialId
  **/
  @ApiModelProperty(example = "UUID-GOES-HERE", required = true, value = "The unique identifier (e.g. a UUID, integer, or string) of this credential. This identifier is intended to be used by developers to map the individual credentials that a member may have. ")
  @NotNull

  public String getCredentialId() {
    return credentialId;
  }

  public void setCredentialId(String credentialId) {
    this.credentialId = credentialId;
  }

  public CULedgerCredentialMyCUIDBasic institution(String institution) {
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

  public CULedgerCredentialMyCUIDBasic credential(String credential) {
    this.credential = credential;
    return this;
  }

  /**
   * A text name that a user will see if they are browsing through their digital wallet. e.g. \"MyFI Secure Login\"
   * @return credential
  **/
  @ApiModelProperty(example = "CULedger Credit Union Authentication Credential", value = "A text name that a user will see if they are browsing through their digital wallet. e.g. \"MyFI Secure Login\"")

  public String getCredential() {
    return credential;
  }

  public void setCredential(String credential) {
    this.credential = credential;
  }

  public CULedgerCredentialMyCUIDBasic memberNumber(String memberNumber) {
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

  public CULedgerCredentialMyCUIDBasic memberSince(String memberSince) {
    this.memberSince = memberSince;
    return this;
  }

  /**
   * text indicating when a Member joined the credit union. Can be blank.
   * @return memberSince
  **/
  @ApiModelProperty(example = "DEC 2012", value = "text indicating when a Member joined the credit union. Can be blank.")

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
    CULedgerCredentialMyCUIDBasic cuLedgerCredentialMyCUIDBasic = (CULedgerCredentialMyCUIDBasic) o;
    return Objects.equals(this.credentialId, cuLedgerCredentialMyCUIDBasic.credentialId) &&
        Objects.equals(this.institution, cuLedgerCredentialMyCUIDBasic.institution) &&
        Objects.equals(this.credential, cuLedgerCredentialMyCUIDBasic.credential) &&
        Objects.equals(this.memberNumber, cuLedgerCredentialMyCUIDBasic.memberNumber) &&
        Objects.equals(this.memberSince, cuLedgerCredentialMyCUIDBasic.memberSince);
  }

  @Override
  public int hashCode() {
    return Objects.hash(credentialId, institution, credential, memberNumber, memberSince);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerCredentialMyCUIDBasic {\n");
    
    sb.append("    credentialId: ").append(toIndentedString(credentialId)).append("\n");
    sb.append("    institution: ").append(toIndentedString(institution)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
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
